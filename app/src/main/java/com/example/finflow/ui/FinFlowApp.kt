package com.example.finflow.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.components.AppBackgroundBrush
import com.example.finflow.ui.components.AppHeader
import com.example.finflow.ui.components.ScreenSwitcher
import com.example.finflow.ui.screens.CategoriasScreen
import com.example.finflow.ui.screens.DespesasScreen
import com.example.finflow.ui.screens.LoginScreen
import com.example.finflow.ui.screens.LogsScreen
import com.example.finflow.ui.screens.MetasScreen
import com.example.finflow.ui.screens.ReceitasScreen
import com.example.finflow.ui.screens.UsuariosScreen
import com.example.finflow.ui.theme.FinBackground

@Composable
fun FinFlowApp() {
    var isLoggedIn by rememberSaveable { mutableStateOf(false) }
    var selectedScreenName by rememberSaveable { mutableStateOf(FinScreen.Despesas.name) }
    val selectedScreen = FinScreen.valueOf(selectedScreenName)

    if (!isLoggedIn) {
        LoginScreen(onLogin = { isLoggedIn = true })
        return
    }

    Scaffold(containerColor = FinBackground) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackgroundBrush)
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            AppHeader(
                screen = selectedScreen,
                onLogout = { isLoggedIn = false }
            )

            ScreenSwitcher(
                selectedScreen = selectedScreen,
                onSelected = { selectedScreenName = it.name }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
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
    }
}
