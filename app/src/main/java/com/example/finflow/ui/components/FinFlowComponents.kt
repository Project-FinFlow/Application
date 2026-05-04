package com.example.finflow.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.FinScreen
import com.example.finflow.ui.data.Metric
import com.example.finflow.ui.data.Movement
import com.example.finflow.ui.data.MovementKind
import com.example.finflow.ui.theme.FinBackground
import com.example.finflow.ui.theme.FinBlue
import com.example.finflow.ui.theme.FinBlueSoft
import com.example.finflow.ui.theme.FinBorder
import com.example.finflow.ui.theme.FinExpense
import com.example.finflow.ui.theme.FinIncome
import com.example.finflow.ui.theme.FinInk
import com.example.finflow.ui.theme.FinMuted
import com.example.finflow.ui.theme.FinSurface
import com.example.finflow.ui.theme.FinSurfaceSoft
import com.example.finflow.ui.theme.FinTeal
import com.example.finflow.ui.theme.FinTealSoft

val AppBackgroundBrush = Brush.linearGradient(
    colors = listOf(
        Color(0xFFFFFCF7),
        FinBackground,
        Color(0xFFEAF7F3)
    )
)

@Composable
fun SurfaceCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(26.dp),
        color = FinSurface,
        tonalElevation = 0.dp,
        shadowElevation = 0.dp,
        border = BorderStroke(1.dp, FinBorder)
    ) {
        Box(modifier = Modifier.padding(22.dp)) {
            content()
        }
    }
}

@Composable
fun InfoPill(text: String) {
    Surface(
        shape = RoundedCornerShape(50),
        color = FinTealSoft
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
            color = FinTeal,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun AppHeader(
    screen: FinScreen,
    onLogout: () -> Unit
) {
    SurfaceCard {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    InfoPill(text = "FINFLOW MOBILE")
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = screen.title,
                        color = FinInk,
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = screen.subtitle,
                        color = FinMuted,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                TextButton(onClick = onLogout) {
                    Text("Sair")
                }
            }
        }
    }
}

@Composable
fun ScreenSwitcher(
    selectedScreen: FinScreen,
    onSelected: (FinScreen) -> Unit
) {
    SurfaceCard {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            FinScreen.values().toList().chunked(3).forEach { rowScreens ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowScreens.forEach { screen ->
                        ScreenTab(
                            screen = screen,
                            selected = screen == selectedScreen,
                            onClick = { onSelected(screen) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ScreenTab(
    screen: FinScreen,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val background = if (selected) FinTealSoft else FinSurfaceSoft
    val color = if (selected) FinTeal else FinMuted

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        ScreenGlyph(screen = screen, tint = color)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = screen.label,
            color = color,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1
        )
    }
}

@Composable
private fun ScreenGlyph(screen: FinScreen, tint: Color) {
    Canvas(modifier = Modifier.size(16.dp)) {
        val stroke = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round)
        val w = size.width
        val h = size.height

        when (screen) {
            FinScreen.Despesas -> {
                drawLine(tint, Offset(w * 0.20f, h * 0.30f), Offset(w * 0.80f, h * 0.30f), stroke.width, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.20f, h * 0.50f), Offset(w * 0.70f, h * 0.50f), stroke.width, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.20f, h * 0.70f), Offset(w * 0.58f, h * 0.70f), stroke.width, cap = StrokeCap.Round)
            }

            FinScreen.Receitas -> {
                drawCircle(tint, radius = w * 0.34f, center = Offset(w * 0.50f, h * 0.50f), style = stroke)
                drawLine(tint, Offset(w * 0.50f, h * 0.26f), Offset(w * 0.50f, h * 0.74f), stroke.width, cap = StrokeCap.Round)
            }

            FinScreen.Categorias -> {
                drawCircle(tint, radius = w * 0.13f, center = Offset(w * 0.32f, h * 0.32f))
                drawCircle(tint, radius = w * 0.13f, center = Offset(w * 0.68f, h * 0.32f))
                drawCircle(tint, radius = w * 0.13f, center = Offset(w * 0.32f, h * 0.68f))
                drawCircle(tint, radius = w * 0.13f, center = Offset(w * 0.68f, h * 0.68f))
            }

            FinScreen.Metas -> {
                drawCircle(tint, radius = w * 0.36f, center = Offset(w * 0.50f, h * 0.50f), style = stroke)
                drawCircle(tint, radius = w * 0.12f, center = Offset(w * 0.50f, h * 0.50f))
            }

            FinScreen.Logs -> {
                drawLine(tint, Offset(w * 0.24f, h * 0.24f), Offset(w * 0.76f, h * 0.24f), stroke.width, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.24f, h * 0.50f), Offset(w * 0.76f, h * 0.50f), stroke.width, cap = StrokeCap.Round)
                drawLine(tint, Offset(w * 0.24f, h * 0.76f), Offset(w * 0.58f, h * 0.76f), stroke.width, cap = StrokeCap.Round)
            }

            FinScreen.Usuarios -> {
                drawCircle(tint, radius = w * 0.18f, center = Offset(w * 0.50f, h * 0.32f), style = stroke)
                drawArc(
                    color = tint,
                    startAngle = 205f,
                    sweepAngle = 130f,
                    useCenter = false,
                    topLeft = Offset(w * 0.20f, h * 0.44f),
                    size = androidx.compose.ui.geometry.Size(w * 0.60f, h * 0.46f),
                    style = stroke
                )
            }
        }
    }
}

@Composable
fun ScreenTitleCard(
    kicker: String,
    title: String,
    description: String,
    actionLabel: String,
    onAction: () -> Unit = {}
) {
    SurfaceCard {
        Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = kicker,
                    color = FinTeal,
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = title,
                    color = FinInk,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = description,
                    color = FinMuted,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Button(
                onClick = onAction,
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = FinBlue),
                contentPadding = PaddingValues(horizontal = 18.dp, vertical = 12.dp)
            ) {
                Text(
                    text = actionLabel,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun MetricGrid(metrics: List<Metric>) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        if (maxWidth > 560.dp) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                metrics.forEach { metric ->
                    MetricCard(metric = metric, modifier = Modifier.weight(1f))
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                metrics.forEach { metric ->
                    MetricCard(metric = metric, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun MetricCard(
    metric: Metric,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        color = FinSurfaceSoft,
        border = BorderStroke(1.dp, FinBorder)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = metric.label,
                color = FinMuted,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = metric.value,
                color = FinInk,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
fun MovementCard(
    movement: Movement,
    accent: Color = if (movement.kind == MovementKind.Receita) FinIncome else FinExpense
) {
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
            Identifier(id = movement.id)
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movement.description,
                    color = FinInk,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${movement.category} - ${movement.date}",
                    color = FinMuted,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Text(
                text = movement.value,
                color = accent,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun Identifier(id: Int) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = FinBlueSoft
    ) {
        Text(
            text = "#$id",
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            color = FinBlue,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun StatusBadge(
    label: String,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(50),
        color = color.copy(alpha = 0.12f)
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            color = color,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun ProgressCard(
    id: Int,
    title: String,
    subtitle: String,
    trailing: String,
    progress: Float
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = FinSurface,
        border = BorderStroke(1.dp, FinBorder)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Identifier(id = id)
                Spacer(modifier = Modifier.width(14.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = FinInk,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = subtitle,
                        color = FinMuted,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Text(
                    text = trailing,
                    color = FinMuted,
                    style = MaterialTheme.typography.labelLarge
                )
            }

            LinearProgressIndicator(
                progress = { progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(50)),
                color = FinTeal,
                trackColor = FinTealSoft
            )
        }
    }
}

@Composable
fun Avatar(initials: String) {
    Box(
        modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(FinTealSoft),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = FinTeal,
            style = MaterialTheme.typography.labelLarge
        )
    }
}
