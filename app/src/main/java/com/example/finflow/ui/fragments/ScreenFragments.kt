package com.example.finflow.ui.fragments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finflow.controllers.CategoryScreenState
import com.example.finflow.controllers.GoalScreenState
import com.example.finflow.controllers.LogScreenState
import com.example.finflow.controllers.MovementScreenState
import com.example.finflow.controllers.ScreenHeaderState
import com.example.finflow.controllers.UserScreenState
import com.example.finflow.ui.components.Avatar
import com.example.finflow.ui.components.Identifier
import com.example.finflow.ui.components.MetricGrid
import com.example.finflow.ui.components.MovementCard
import com.example.finflow.ui.components.ProgressCard
import com.example.finflow.ui.components.ScreenTitleCard
import com.example.finflow.ui.components.StatusBadge
import com.example.finflow.ui.data.CategoryItem
import com.example.finflow.ui.data.GoalItem
import com.example.finflow.ui.data.LogItem
import com.example.finflow.ui.data.Metric
import com.example.finflow.ui.data.Movement
import com.example.finflow.ui.data.MovementKind
import com.example.finflow.ui.data.UserItem
import com.example.finflow.ui.theme.FinBlue
import com.example.finflow.ui.theme.FinBorder
import com.example.finflow.ui.theme.FinExpense
import com.example.finflow.ui.theme.FinIncome
import com.example.finflow.ui.theme.FinInk
import com.example.finflow.ui.theme.FinMuted
import com.example.finflow.ui.theme.FinSurface
import com.example.finflow.ui.theme.FinTeal

@Composable
fun MovementScreenFragment(
    state: MovementScreenState,
    onAction: () -> Unit
) {
    ScreenContentFragment {
        ScreenHeaderFragment(
            state = state.header,
            onAction = onAction
        )
        MetricsFragment(metrics = state.metrics)
        MovementListFragment(movements = state.movements)
    }
}

@Composable
fun CategoryScreenFragment(
    state: CategoryScreenState,
    onAction: () -> Unit
) {
    ScreenContentFragment {
        ScreenHeaderFragment(
            state = state.header,
            onAction = onAction
        )
        MetricsFragment(metrics = state.metrics)
        CategoryListFragment(categories = state.categories)
    }
}

@Composable
fun GoalScreenFragment(
    state: GoalScreenState,
    onAction: () -> Unit
) {
    ScreenContentFragment {
        ScreenHeaderFragment(
            state = state.header,
            onAction = onAction
        )
        MetricsFragment(metrics = state.metrics)
        GoalListFragment(goals = state.goals)
    }
}

@Composable
fun LogScreenFragment(
    state: LogScreenState,
    onAction: () -> Unit
) {
    ScreenContentFragment {
        ScreenHeaderFragment(
            state = state.header,
            onAction = onAction
        )
        MetricsFragment(metrics = state.metrics)
        LogListFragment(logs = state.logs)
    }
}

@Composable
fun UserScreenFragment(
    state: UserScreenState,
    onAction: () -> Unit
) {
    ScreenContentFragment {
        ScreenHeaderFragment(
            state = state.header,
            onAction = onAction
        )
        MetricsFragment(metrics = state.metrics)
        UserListFragment(users = state.users)
    }
}

@Composable
private fun ScreenContentFragment(
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        content()
    }
}

@Composable
private fun ScreenHeaderFragment(
    state: ScreenHeaderState,
    onAction: () -> Unit
) {
    ScreenTitleCard(
        kicker = state.kicker,
        title = state.title,
        description = state.description,
        actionLabel = state.actionLabel,
        onAction = onAction
    )
}

@Composable
private fun MetricsFragment(metrics: List<Metric>) {
    MetricGrid(metrics = metrics)
}

@Composable
private fun MovementListFragment(movements: List<Movement>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        movements.forEach { movement ->
            MovementCard(movement = movement)
        }
    }
}

@Composable
private fun CategoryListFragment(categories: List<CategoryItem>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        categories.forEach { item ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = FinSurface,
                border = BorderStroke(1.dp, FinBorder)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Identifier(id = item.id)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = item.name,
                            color = FinInk,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = item.description,
                            color = FinMuted,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    StatusBadge(
                        label = item.kind.name.uppercase(),
                        color = if (item.kind == MovementKind.Receita) FinIncome else FinTeal
                    )
                }
            }
        }
    }
}

@Composable
private fun GoalListFragment(goals: List<GoalItem>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        goals.forEach { goal ->
            ProgressCard(
                id = goal.id,
                title = goal.name,
                subtitle = "${goal.current} de ${goal.target}",
                trailing = goal.dueDate,
                progress = goal.progress
            )
        }
    }
}

@Composable
private fun LogListFragment(logs: List<LogItem>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        logs.forEach { item ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = FinSurface,
                border = BorderStroke(1.dp, FinBorder)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Identifier(id = item.id)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = item.event,
                            color = FinInk,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "${item.actor} - ${item.date}",
                            color = FinMuted,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    StatusBadge(
                        label = item.status,
                        color = if (item.status == "OK") FinIncome else FinExpense
                    )
                }
            }
        }
    }
}

@Composable
private fun UserListFragment(users: List<UserItem>) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        users.forEach { user ->
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                color = FinSurface,
                border = BorderStroke(1.dp, FinBorder)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Avatar(initials = user.initials)
                    Spacer(modifier = Modifier.width(14.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = user.name,
                            color = FinInk,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = user.email,
                            color = FinMuted,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    StatusBadge(label = user.role, color = FinBlue)
                }
            }
        }
    }
}
