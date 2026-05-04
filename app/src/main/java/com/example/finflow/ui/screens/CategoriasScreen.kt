package com.example.finflow.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.components.Identifier
import com.example.finflow.ui.components.MetricGrid
import com.example.finflow.ui.components.ScreenTitleCard
import com.example.finflow.ui.components.StatusBadge
import com.example.finflow.ui.data.MovementKind
import com.example.finflow.ui.data.categoryItems
import com.example.finflow.ui.data.categoryMetrics
import com.example.finflow.ui.theme.FinBorder
import com.example.finflow.ui.theme.FinFLowTheme
import com.example.finflow.ui.theme.FinIncome
import com.example.finflow.ui.theme.FinInk
import com.example.finflow.ui.theme.FinMuted
import com.example.finflow.ui.theme.FinSurface
import com.example.finflow.ui.theme.FinTeal

@Composable
fun CategoriasScreen() {
    Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
        ScreenTitleCard(
            kicker = "ORGANIZACAO",
            title = "Categorias",
            description = "Gerencie somente os tipos usados para separar receitas e despesas.",
            actionLabel = "Nova categoria"
        )

        MetricGrid(metrics = categoryMetrics)

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            categoryItems.forEach { item ->
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
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun CategoriasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        CategoriasScreen()
    }
}
