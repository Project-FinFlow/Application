package com.example.finflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.finflow.controllers.ReceitasController
import com.example.finflow.controllers.rememberReceitasController
import com.example.finflow.ui.fragments.MovementScreenFragment
import com.example.finflow.ui.theme.FinFLowTheme

@Composable
fun ReceitasScreen(
    controller: ReceitasController = rememberReceitasController()
) {
    MovementScreenFragment(
        state = controller.state,
        onAction = controller::refresh
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun ReceitasScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        ReceitasScreen(controller = rememberReceitasController())
    }
}
