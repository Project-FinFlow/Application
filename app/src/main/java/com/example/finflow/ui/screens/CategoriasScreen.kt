package com.example.finflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.finflow.controllers.CategoriasController
import com.example.finflow.controllers.rememberCategoriasController
import com.example.finflow.ui.fragments.CategoryScreenFragment
import com.example.finflow.ui.theme.FinFLowTheme
import kotlinx.coroutines.launch

@Composable
fun CategoriasScreen(
    controller: CategoriasController = rememberCategoriasController()
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(controller) {
        runCatching { controller.refresh() }
    }

    CategoryScreenFragment(
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
private fun CategoriasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        CategoriasScreen(controller = rememberCategoriasController())
    }
}
