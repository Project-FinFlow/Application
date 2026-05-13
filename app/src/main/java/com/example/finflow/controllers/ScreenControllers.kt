package com.example.finflow.controllers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.finflow.api.FinFlowRepository
import com.example.finflow.api.categoryMetrics as apiCategoryMetrics
import com.example.finflow.api.goalMetrics as apiGoalMetrics
import com.example.finflow.api.logMetrics as apiLogMetrics
import com.example.finflow.api.movementMetrics
import com.example.finflow.api.userMetrics as apiUserMetrics
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
    var state by mutableStateOf(
        MovementScreenState(
            header = ScreenHeaderState(
                kicker = "MOVIMENTACOES",
                title = "Despesas",
                description = "Visualize apenas as saidas, compras e pagamentos cadastrados.",
                actionLabel = "Atualizar despesas"
            ),
            metrics = expenseMetrics,
            movements = expenseMovements
        )
    )
        private set

    suspend fun refresh() {
        val movements = FinFlowRepository.despesas()
        state = state.copy(
            metrics = movementMetrics("Total", movements),
            movements = movements
        )
    }
}

@Stable
class ReceitasController {
    var state by mutableStateOf(
        MovementScreenState(
            header = ScreenHeaderState(
                kicker = "ENTRADAS",
                title = "Receitas",
                description = "Acompanhe apenas os valores recebidos e novas entradas.",
                actionLabel = "Atualizar receitas"
            ),
            metrics = incomeMetrics,
            movements = incomeMovements
        )
    )
        private set

    suspend fun refresh() {
        val movements = FinFlowRepository.receitas()
        state = state.copy(
            metrics = movementMetrics("Total", movements),
            movements = movements
        )
    }
}

@Stable
class CategoriasController {
    var state by mutableStateOf(
        CategoryScreenState(
            header = ScreenHeaderState(
                kicker = "ORGANIZACAO",
                title = "Categorias",
                description = "Gerencie somente os tipos usados para separar receitas e despesas.",
                actionLabel = "Atualizar categorias"
            ),
            metrics = categoryMetrics,
            categories = categoryItems
        )
    )
        private set

    suspend fun refresh() {
        val categories = FinFlowRepository.categorias()
        state = state.copy(
            metrics = apiCategoryMetrics(categories),
            categories = categories
        )
    }
}

@Stable
class MetasController {
    var state by mutableStateOf(
        GoalScreenState(
            header = ScreenHeaderState(
                kicker = "PLANEJAMENTO",
                title = "Metas",
                description = "Acompanhe somente os objetivos financeiros e o progresso de cada um.",
                actionLabel = "Atualizar metas"
            ),
            metrics = goalMetrics,
            goals = goalItems
        )
    )
        private set

    suspend fun refresh() {
        val goals = FinFlowRepository.metas()
        state = state.copy(
            metrics = apiGoalMetrics(goals),
            goals = goals
        )
    }
}

@Stable
class LogsController {
    var state by mutableStateOf(
        LogScreenState(
            header = ScreenHeaderState(
                kicker = "AUDITORIA",
                title = "Logs",
                description = "Consulte somente eventos recentes, chamadas da API e alertas.",
                actionLabel = "Atualizar logs"
            ),
            metrics = logMetrics,
            logs = logItems
        )
    )
        private set

    suspend fun refresh() {
        val logs = FinFlowRepository.logs()
        state = state.copy(
            metrics = apiLogMetrics(logs),
            logs = logs
        )
    }
}

@Stable
class UsuariosController {
    var state by mutableStateOf(
        UserScreenState(
            header = ScreenHeaderState(
                kicker = "ACESSOS",
                title = "Usuarios",
                description = "Gerencie somente contas, cargos e permissoes do aplicativo.",
                actionLabel = "Atualizar usuarios"
            ),
            metrics = userMetrics,
            users = userItems
        )
    )
        private set

    suspend fun refresh() {
        val users = FinFlowRepository.usuarios()
        state = state.copy(
            metrics = apiUserMetrics(users),
            users = users
        )
    }
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
