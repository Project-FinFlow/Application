package com.example.finflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.example.finflow.controllers.UsuariosController
import com.example.finflow.controllers.rememberUsuariosController
import com.example.finflow.ui.fragments.UserScreenFragment
import com.example.finflow.ui.theme.FinFLowTheme
import kotlinx.coroutines.launch

@Composable
fun UsuariosScreen(
    controller: UsuariosController = rememberUsuariosController()
) {
    val scope = rememberCoroutineScope()

    LaunchedEffect(controller) {
        runCatching { controller.refresh() }
    }

    UserScreenFragment(
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
private fun UsuariosScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        UsuariosScreen(controller = rememberUsuariosController())
    }
}
