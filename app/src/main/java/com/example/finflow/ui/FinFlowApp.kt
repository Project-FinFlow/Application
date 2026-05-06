package com.example.finflow.ui

import androidx.compose.runtime.Composable
import com.example.finflow.controllers.rememberFinFlowController
import com.example.finflow.ui.fragments.DashboardFragment
import com.example.finflow.ui.screens.CategoriasScreen
import com.example.finflow.ui.screens.DespesasScreen
import com.example.finflow.ui.screens.LoginScreen
import com.example.finflow.ui.screens.LogsScreen
import com.example.finflow.ui.screens.MetasScreen
import com.example.finflow.ui.screens.ReceitasScreen
import com.example.finflow.ui.screens.UsuariosScreen

@Composable
fun FinFlowApp() {
    val controller = rememberFinFlowController()
    val authController = controller.authController
    val navigationController = controller.navigationController
    val selectedScreen = navigationController.selectedScreen

    if (!authController.isLoggedIn) {
        LoginScreen(
            controller = authController,
            onLogin = controller::login
        )
        return
    }

    DashboardFragment(
        selectedScreen = selectedScreen,
        onScreenSelected = navigationController::select,
        onLogout = controller::logout
    ) {
        when (selectedScreen) {
            FinScreen.Despesas -> DespesasScreen()
            FinScreen.Receitas -> ReceitasScreen()
            FinScreen.Categorias -> CategoriasScreen()
            FinScreen.Metas -> MetasScreen()
            FinScreen.Logs -> LogsScreen()
            FinScreen.Usuarios -> UsuariosScreen()
        }
    }
}
