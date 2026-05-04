package com.example.finflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.components.MetricGrid
import com.example.finflow.ui.components.MovementCard
import com.example.finflow.ui.components.ScreenTitleCard
import com.example.finflow.ui.data.incomeMetrics
import com.example.finflow.ui.data.incomeMovements
import com.example.finflow.ui.theme.FinFLowTheme

@Composable
fun ReceitasScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        ScreenTitleCard(
            kicker = "ENTRADAS",
            title = "Receitas",
            description = "Acompanhe apenas os valores recebidos e novas entradas.",
            actionLabel = "Atualizar receitas"
        )

        MetricGrid(metrics = incomeMetrics)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            incomeMovements.forEach { movement ->
                MovementCard(movement = movement)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun ReceitasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        ReceitasScreen()
    }
}
