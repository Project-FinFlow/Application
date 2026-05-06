package com.example.finflow.controllers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.example.finflow.ui.data.CategoryItem
import com.example.finflow.ui.data.GoalItem
import com.example.finflow.ui.data.LogItem
import com.example.finflow.ui.data.Metric
import com.example.finflow.ui.data.Movement
import com.example.finflow.ui.data.UserItem
import com.example.finflow.ui.data.categoryItems
import com.example.finflow.ui.data.categoryMetrics
import com.example.finflow.ui.data.expenseMetrics
import com.example.finflow.ui.data.expenseMovements
import com.example.finflow.ui.data.goalItems
import com.example.finflow.ui.data.goalMetrics
import com.example.finflow.ui.data.incomeMetrics
import com.example.finflow.ui.data.incomeMovements
import com.example.finflow.ui.data.logItems
import com.example.finflow.ui.data.logMetrics
import com.example.finflow.ui.data.userItems
import com.example.finflow.ui.data.userMetrics

data class ScreenHeaderState(
    val kicker: String,
    val title: String,
    val description: String,
    val actionLabel: String
)

data class MovementScreenState(
    val header: ScreenHeaderState,
    val metrics: List<Metric>,
    val movements: List<Movement>
)

data class CategoryScreenState(
    val header: ScreenHeaderState,
    val metrics: List<Metric>,
    val categories: List<CategoryItem>
)

data class GoalScreenState(
    val header: ScreenHeaderState,
    val metrics: List<Metric>,
    val goals: List<GoalItem>
)

data class LogScreenState(
    val header: ScreenHeaderState,
    val metrics: List<Metric>,
    val logs: List<LogItem>
)

data class UserScreenState(
    val header: ScreenHeaderState,
    val metrics: List<Metric>,
    val users: List<UserItem>
)

@Stable
class DespesasController {
    val state = MovementScreenState(
        header = ScreenHeaderState(
            kicker = "MOVIMENTACOES",
            title = "Despesas",
            description = "Visualize apenas as saidas, compras e pagamentos cadastrados.",
            actionLabel = "Atualizar despesas"
        ),
        metrics = expenseMetrics,
        movements = expenseMovements
    )

    fun refresh() = Unit
}

@Stable
class ReceitasController {
    val state = MovementScreenState(
        header = ScreenHeaderState(
            kicker = "ENTRADAS",
            title = "Receitas",
            description = "Acompanhe apenas os valores recebidos e novas entradas.",
            actionLabel = "Atualizar receitas"
        ),
        metrics = incomeMetrics,
        movements = incomeMovements
    )

    fun refresh() = Unit
}

@Stable
class CategoriasController {
    val state = CategoryScreenState(
        header = ScreenHeaderState(
            kicker = "ORGANIZACAO",
            title = "Categorias",
            description = "Gerencie somente os tipos usados para separar receitas e despesas.",
            actionLabel = "Nova categoria"
        ),
        metrics = categoryMetrics,
        categories = categoryItems
    )

    fun createCategory() = Unit
}

@Stable
class MetasController {
    val state = GoalScreenState(
        header = ScreenHeaderState(
            kicker = "PLANEJAMENTO",
            title = "Metas",
            description = "Acompanhe somente os objetivos financeiros e o progresso de cada um.",
            actionLabel = "Nova meta"
        ),
        metrics = goalMetrics,
        goals = goalItems
    )

    fun createGoal() = Unit
}

@Stable
class LogsController {
    val state = LogScreenState(
        header = ScreenHeaderState(
            kicker = "AUDITORIA",
            title = "Logs",
            description = "Consulte somente eventos recentes, chamadas da API e alertas.",
            actionLabel = "Atualizar logs"
        ),
        metrics = logMetrics,
        logs = logItems
    )

    fun refresh() = Unit
}

@Stable
class UsuariosController {
    val state = UserScreenState(
        header = ScreenHeaderState(
            kicker = "ACESSOS",
            title = "Usuarios",
            description = "Gerencie somente contas, cargos e permissoes do aplicativo.",
            actionLabel = "Novo usuario"
        ),
        metrics = userMetrics,
        users = userItems
    )

    fun createUser() = Unit
}

@Composable
fun rememberDespesasController(): DespesasController = remember {
    DespesasController()
}

@Composable
fun rememberReceitasController(): ReceitasController = remember {
    ReceitasController()
}

@Composable
fun rememberCategoriasController(): CategoriasController = remember {
    CategoriasController()
}

@Composable
fun rememberMetasController(): MetasController = remember {
    MetasController()
}

@Composable
fun rememberLogsController(): LogsController = remember {
    LogsController()
}

@Composable
fun rememberUsuariosController(): UsuariosController = remember {
    UsuariosController()
}
