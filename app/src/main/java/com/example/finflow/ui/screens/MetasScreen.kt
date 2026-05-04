package com.example.finflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.components.MetricGrid
import com.example.finflow.ui.components.ProgressCard
import com.example.finflow.ui.components.ScreenTitleCard
import com.example.finflow.ui.data.goalItems
import com.example.finflow.ui.data.goalMetrics
import com.example.finflow.ui.theme.FinFLowTheme

@Composable
fun MetasScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        ScreenTitleCard(
            kicker = "PLANEJAMENTO",
            title = "Metas",
            description = "Acompanhe somente os objetivos financeiros e o progresso de cada um.",
            actionLabel = "Nova meta"
        )

        MetricGrid(metrics = goalMetrics)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            goalItems.forEach { goal ->
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
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun MetasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        MetasScreen()
    }
}
