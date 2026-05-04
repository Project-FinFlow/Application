package com.example.finflow.ui.data

enum class MovementKind {
    Receita,
    Despesa
}

data class Metric(
    val label: String,
    val value: String
)

data class Movement(
    val id: Int,
    val description: String,
    val category: String,
    val date: String,
    val value: String,
    val kind: MovementKind
)

data class CategoryItem(
    val id: Int,
    val name: String,
    val description: String,
    val kind: MovementKind
)

data class GoalItem(
    val id: Int,
    val name: String,
    val current: String,
    val target: String,
    val dueDate: String,
    val progress: Float
)

data class UserItem(
    val initials: String,
    val name: String,
    val email: String,
    val role: String
)

data class LogItem(
    val id: Int,
    val event: String,
    val actor: String,
    val date: String,
    val status: String
)

val expenseMetrics = listOf(
    Metric("Registros", "3"),
    Metric("Total", "R$ 400,00")
)

val incomeMetrics = listOf(
    Metric("Registros", "3"),
    Metric("Total", "R$ 7.450,00")
)

val categoryMetrics = listOf(
    Metric("Categorias", "5"),
    Metric("Tipos", "2")
)

val goalMetrics = listOf(
    Metric("Metas ativas", "3"),
    Metric("Planejado", "R$ 12.000,00")
)

val userMetrics = listOf(
    Metric("Usuarios", "4"),
    Metric("Perfis", "3")
)

val logMetrics = listOf(
    Metric("Eventos", "6"),
    Metric("Hoje", "4")
)

val expenseMovements = listOf(
    Movement(1, "Compra no mercado", "Alimentacao", "23/04/2026", "R$ 50,00", MovementKind.Despesa),
    Movement(2, "Gasolina do carro", "Transporte", "24/04/2026", "R$ 120,00", MovementKind.Despesa),
    Movement(3, "Conta de luz", "Moradia", "25/04/2026", "R$ 230,00", MovementKind.Despesa)
)

val incomeMovements = listOf(
    Movement(1, "Salario", "Salario", "23/04/2026", "R$ 5.000,00", MovementKind.Receita),
    Movement(2, "Projeto freelance", "Freelance", "25/04/2026", "R$ 1.800,00", MovementKind.Receita),
    Movement(3, "Venda online", "Freelance", "26/04/2026", "R$ 650,00", MovementKind.Receita)
)

val categoryItems = listOf(
    CategoryItem(1, "Alimentacao", "Mercado, refeicoes e compras recorrentes.", MovementKind.Despesa),
    CategoryItem(2, "Transporte", "Combustivel, aplicativos e manutencao.", MovementKind.Despesa),
    CategoryItem(3, "Moradia", "Casa, energia, agua e aluguel.", MovementKind.Despesa),
    CategoryItem(4, "Salario", "Entrada principal mensal.", MovementKind.Receita),
    CategoryItem(5, "Freelance", "Projetos e servicos avulsos.", MovementKind.Receita)
)

val goalItems = listOf(
    GoalItem(1, "Reserva emergencial", "R$ 4.800,00", "R$ 8.000,00", "Dez/2026", 0.60f),
    GoalItem(2, "Notebook novo", "R$ 2.200,00", "R$ 4.000,00", "Ago/2026", 0.55f),
    GoalItem(3, "Viagem", "R$ 1.100,00", "R$ 3.000,00", "Jan/2027", 0.37f)
)

val userItems = listOf(
    UserItem("GS", "Guilherme Santos", "guilherme@finflow.com", "ADMIN"),
    UserItem("AM", "Ana Martins", "ana@finflow.com", "GESTOR"),
    UserItem("RC", "Rafael Costa", "rafael@finflow.com", "OPERADOR"),
    UserItem("LS", "Laura Silva", "laura@finflow.com", "LEITURA")
)

val logItems = listOf(
    LogItem(1, "POST /despesas recebido", "Guilherme Santos", "04/05/2026 16:20", "OK"),
    LogItem(2, "Receita atualizada", "Ana Martins", "04/05/2026 15:48", "OK"),
    LogItem(3, "Tentativa de login recusada", "Sistema", "04/05/2026 14:12", "ALERTA"),
    LogItem(4, "Categoria criada", "Rafael Costa", "04/05/2026 13:34", "OK"),
    LogItem(5, "Meta revisada", "Guilherme Santos", "03/05/2026 18:05", "OK"),
    LogItem(6, "Usuario convidado", "Ana Martins", "03/05/2026 11:19", "OK")
)
