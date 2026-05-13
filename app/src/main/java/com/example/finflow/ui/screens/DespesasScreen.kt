package com.example.finflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.finflow.controllers.DespesasController
import com.example.finflow.controllers.rememberDespesasController
import com.example.finflow.ui.fragments.MovementScreenFragment
import com.example.finflow.ui.theme.FinFLowTheme
import kotlinx.coroutines.launch

@Composable
fun DespesasScreen(
    controller: DespesasController = rememberDespesasController()
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(controller) {
        runCatching { controller.refresh() }
    }

    MovementScreenFragment(
        state = controller.state,
        onAction = {
            scope.launch {
                runCatching { controller.refresh() }
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun DespesasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        DespesasScreen(controller = rememberDespesasController())
    }
}
