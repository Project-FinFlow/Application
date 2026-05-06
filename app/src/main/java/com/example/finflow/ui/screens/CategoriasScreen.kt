package com.example.finflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.finflow.controllers.CategoriasController
import com.example.finflow.controllers.rememberCategoriasController
import com.example.finflow.ui.fragments.CategoryScreenFragment
import com.example.finflow.ui.theme.FinFLowTheme

@Composable
fun CategoriasScreen(
    controller: CategoriasController = rememberCategoriasController()
) {
    CategoryScreenFragment(
        state = controller.state,
        onAction = controller::createCategory
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun CategoriasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        CategoriasScreen(controller = rememberCategoriasController())
    }
}
