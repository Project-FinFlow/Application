package com.example.finflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.finflow.controllers.MetasController
import com.example.finflow.controllers.rememberMetasController
import com.example.finflow.ui.fragments.GoalScreenFragment
import com.example.finflow.ui.theme.FinFLowTheme
import kotlinx.coroutines.launch

@Composable
fun MetasScreen(
    controller: MetasController = rememberMetasController()
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(controller) {
        runCatching { controller.refresh() }
    }

    GoalScreenFragment(
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
private fun MetasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        MetasScreen(controller = rememberMetasController())
    }
}
