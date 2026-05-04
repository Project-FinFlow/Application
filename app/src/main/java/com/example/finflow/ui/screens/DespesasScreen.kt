package com.example.finflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.components.MetricGrid
import com.example.finflow.ui.components.MovementCard
import com.example.finflow.ui.components.ScreenTitleCard
import com.example.finflow.ui.data.expenseMetrics
import com.example.finflow.ui.data.expenseMovements
import com.example.finflow.ui.theme.FinFLowTheme

@Composable
fun DespesasScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        ScreenTitleCard(
            kicker = "MOVIMENTACOES",
            title = "Despesas",
            description = "Visualize apenas as saidas, compras e pagamentos cadastrados.",
            actionLabel = "Atualizar despesas"
        )

        MetricGrid(metrics = expenseMetrics)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            expenseMovements.forEach { movement ->
                MovementCard(movement = movement)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun DespesasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        DespesasScreen()
    }
}
