package com.example.finflow.api

import com.example.finflow.ui.data.CategoryItem
import com.example.finflow.ui.data.GoalItem
import com.example.finflow.ui.data.LogItem
import com.example.finflow.ui.data.Metric
import com.example.finflow.ui.data.Movement
import com.example.finflow.ui.data.MovementKind
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.NumberFormat
import java.util.Locale

private const val BASE_URL = "https://finflow-ggu3.onrender.com"

class FinFlowApiClient(
    private val baseUrl: String = BASE_URL
) {
    suspend fun getDespesas(): List<Movement> = getArray("despesas").mapIndexed { index, item ->
        item.toMovement(index, MovementKind.Despesa)
    }

    suspend fun getReceitas(): List<Movement> = getArray("receitas").mapIndexed { index, item ->
        item.toMovement(index, MovementKind.Receita)
    }

    suspend fun getCategorias(): List<CategoryItem> = getArray("categorias").mapIndexed { index, item ->
        CategoryItem(
            id = item.intValue(index, "id", "codigo"),
            name = item.stringValue("name", "nome", "titulo", fallback = "Categoria ${index + 1}"),
            description = item.stringValue("description", "descricao", "detalhes", fallback = "Sem descricao cadastrada."),
            kind = item.kindValue(default = MovementKind.Despesa)
        )
    }

    suspend fun getMetas(): List<GoalItem> = getArray("metas").mapIndexed { index, item ->
        val current = item.moneyText("current", "atual", "valorAtual", "valor_atual", fallback = "R$ 0,00")
        val target = item.moneyText("target", "meta", "valorMeta", "valor_meta", "objetivo", fallback = "R$ 0,00")

        GoalItem(
            id = item.intValue(index, "id", "codigo"),
            name = item.stringValue("name", "nome", "titulo", fallback = "Meta ${index + 1}"),
            current = current,
            target = target,
            dueDate = item.stringValue("dueDate", "prazo", "dataLimite", "data_limite", "vencimento", fallback = "Sem prazo"),
            progress = item.progressValue()
        )
    }

    suspend fun getLogs(): List<LogItem> = getArray("logs").mapIndexed { index, item ->
        LogItem(
            id = item.intValue(index, "id", "codigo"),
            event = item.stringValue("event", "evento", "action", "acao", "mensagem", fallback = "Evento ${index + 1}"),
            actor = item.stringValue("actor", "ator", "user", "usuario", "responsavel", fallback = "Sistema"),
            date = item.stringValue("date", "data", "createdAt", "criadoEm", "created_at", fallback = "Sem data"),
            status = item.stringValue("status", "situacao", fallback = "OK").uppercase()
        )
    }

    suspend fun getUsuarios(): List<com.example.finflow.ui.data.UserItem> = getArray("usuarios").mapIndexed { index, item ->
        val name = item.stringValue("name", "nome", "usuario", fallback = "Usuario ${index + 1}")
        com.example.finflow.ui.data.UserItem(
            initials = name.initials(),
            name = name,
            email = item.stringValue("email", "mail", fallback = "sem-email@finflow.com"),
            role = item.stringValue("role", "perfil", "cargo", "tipo", fallback = "USUARIO").uppercase()
        )
    }

    private suspend fun getArray(endpoint: String): List<JSONObject> = withContext(Dispatchers.IO) {
        val errors = mutableListOf<Exception>()
        listOf(endpoint, "api/$endpoint").forEach { path ->
            runCatching {
                return@withContext parseArray(get(path)).toJsonObjects()
            }.onFailure { error ->
                if (error is Exception) {
                    errors += error
                }
            }
        }
        throw errors.lastOrNull() ?: IllegalStateException("Nao foi possivel carregar /$endpoint")
    }

    private fun get(path: String): String {
        val connection = URL("${baseUrl.trimEnd('/')}/${path.trimStart('/')}").openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 20000
        connection.readTimeout = 30000
        connection.setRequestProperty("Accept", "application/json")

        return try {
            val code = connection.responseCode
            val stream = if (code in 200..299) connection.inputStream else connection.errorStream
            val body = BufferedReader(InputStreamReader(stream)).use { it.readText() }

            if (code !in 200..299) {
                throw IllegalStateException("HTTP $code em $path: $body")
            }

            body
        } finally {
            connection.disconnect()
        }
    }
}

object FinFlowRepository {
    private val client = FinFlowApiClient()

    suspend fun despesas() = client.getDespesas()
    suspend fun receitas() = client.getReceitas()
    suspend fun categorias() = client.getCategorias()
    suspend fun metas() = client.getMetas()
    suspend fun logs() = client.getLogs()
    suspend fun usuarios() = client.getUsuarios()
}

fun movementMetrics(label: String, movements: List<Movement>): List<Metric> {
    val total = movements.sumOf { it.value.moneyNumber() }
    return listOf(
        Metric("Registros", movements.size.toString()),
        Metric(label, total.currencyText())
    )
}

fun categoryMetrics(categories: List<CategoryItem>) = listOf(
    Metric("Categorias", categories.size.toString()),
    Metric("Tipos", categories.map { it.kind }.distinct().size.toString())
)

fun goalMetrics(goals: List<GoalItem>) = listOf(
    Metric("Metas ativas", goals.size.toString()),
    Metric("Planejado", goals.sumOf { it.target.moneyNumber() }.currencyText())
)

fun logMetrics(logs: List<LogItem>) = listOf(
    Metric("Eventos", logs.size.toString()),
    Metric("Alertas", logs.count { it.status != "OK" }.toString())
)

fun userMetrics(users: List<com.example.finflow.ui.data.UserItem>) = listOf(
    Metric("Usuarios", users.size.toString()),
    Metric("Perfis", users.map { it.role }.distinct().size.toString())
)

private fun JSONObject.toMovement(index: Int, kind: MovementKind): Movement {
    return Movement(
        id = intValue(index, "id", "codigo"),
        description = stringValue("description", "descricao", "name", "nome", "titulo", fallback = "Movimentacao ${index + 1}"),
        category = stringValue("category", "categoria", "tipo", fallback = if (kind == MovementKind.Receita) "Receita" else "Despesa"),
        date = stringValue("date", "data", "createdAt", "criadoEm", "created_at", fallback = "Sem data"),
        value = moneyText("value", "valor", "amount", "preco", fallback = "R$ 0,00"),
        kind = kind
    )
}

private fun parseArray(raw: String): JSONArray {
    val trimmed = raw.trim()
    if (trimmed.startsWith("[")) {
        return JSONArray(trimmed)
    }

    val root = JSONObject(trimmed)
    val arrayKeys = listOf("data", "items", "results", "result", "rows", "content")
    arrayKeys.forEach { key ->
        root.optJSONArray(key)?.let { return it }
    }

    root.keys().forEach { key ->
        root.optJSONArray(key)?.let { return it }
    }

    return JSONArray().put(root)
}

private fun JSONArray.toJsonObjects(): List<JSONObject> {
    return List(length()) { index -> optJSONObject(index) ?: JSONObject() }
}

private fun JSONObject.intValue(index: Int, vararg keys: String): Int {
    keys.forEach { key ->
        val value = opt(key)
        when (value) {
            is Number -> return value.toInt()
            is String -> value.toIntOrNull()?.let { return it }
        }
    }
    return index + 1
}

private fun JSONObject.stringValue(vararg keys: String, fallback: String): String {
    keys.forEach { key ->
        val value = optString(key, "").trim()
        if (value.isNotEmpty() && value != "null") {
            return value
        }
    }
    return fallback
}

private fun JSONObject.moneyText(vararg keys: String, fallback: String): String {
    keys.forEach { key ->
        val value = opt(key)
        when (value) {
            is Number -> return value.toDouble().currencyText()
            is String -> {
                val clean = value.trim()
                if (clean.isNotEmpty() && clean != "null") {
                    return if (clean.contains("R$")) clean else clean.moneyNumber().currencyText()
                }
            }
        }
    }
    return fallback
}

private fun JSONObject.kindValue(default: MovementKind): MovementKind {
    val raw = stringValue("kind", "tipo", "type", "categoriaTipo", fallback = default.name).lowercase()
    return if (raw.contains("receita") || raw.contains("entrada") || raw == "income") {
        MovementKind.Receita
    } else {
        MovementKind.Despesa
    }
}

private fun JSONObject.progressValue(): Float {
    val direct = listOf("progress", "progresso", "percentual").firstNotNullOfOrNull { key ->
        optDoubleOrNull(key)
    }
    if (direct != null) {
        return if (direct > 1.0) (direct / 100.0).toFloat().coerceIn(0f, 1f) else direct.toFloat().coerceIn(0f, 1f)
    }

    val current = listOf("current", "atual", "valorAtual", "valor_atual").firstNotNullOfOrNull { key ->
        optMoneyOrNull(key)
    } ?: 0.0
    val target = listOf("target", "meta", "valorMeta", "valor_meta", "objetivo").firstNotNullOfOrNull { key ->
        optMoneyOrNull(key)
    } ?: 0.0

    return if (target > 0) (current / target).toFloat().coerceIn(0f, 1f) else 0f
}

private fun JSONObject.optDoubleOrNull(key: String): Double? {
    val value = opt(key)
    return when (value) {
        is Number -> value.toDouble()
        is String -> value.moneyNumber().takeIf { it > 0.0 }
        else -> null
    }
}

private fun JSONObject.optMoneyOrNull(key: String): Double? {
    val value = opt(key)
    return when (value) {
        is Number -> value.toDouble()
        is String -> value.moneyNumber()
        else -> null
    }
}

private fun String.initials(): String {
    return trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString("") { it.first().uppercaseChar().toString() }
        .ifBlank { "US" }
}

private fun String.moneyNumber(): Double {
    val clean = replace("R$", "")
        .replace(".", "")
        .replace(",", ".")
        .filter { it.isDigit() || it == '.' || it == '-' }

    return clean.toDoubleOrNull() ?: 0.0
}

private fun Double.currencyText(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
}
