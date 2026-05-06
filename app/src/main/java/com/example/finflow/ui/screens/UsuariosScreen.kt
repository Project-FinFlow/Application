package com.example.finflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.finflow.controllers.UsuariosController
import com.example.finflow.controllers.rememberUsuariosController
import com.example.finflow.ui.fragments.UserScreenFragment
import com.example.finflow.ui.theme.FinFLowTheme

@Composable
fun UsuariosScreen(
    controller: UsuariosController = rememberUsuariosController()
) {
    UserScreenFragment(
        state = controller.state,
        onAction = controller::createUser
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun UsuariosScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        UsuariosScreen(controller = rememberUsuariosController())
    }
}
