package com.example.finflow.ui.fragments

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.FinScreen
import com.example.finflow.ui.components.AppBackgroundBrush
import com.example.finflow.ui.components.AppHeader
import com.example.finflow.ui.components.ScreenSwitcher
import com.example.finflow.ui.theme.FinBackground

@Composable
fun DashboardFragment(
    selectedScreen: FinScreen,
    onScreenSelected: (FinScreen) -> Unit,
    onLogout: () -> Unit,
    content: @Composable () -> Unit
) {
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
                onLogout = onLogout
            )

            ScreenSwitcher(
                selectedScreen = selectedScreen,
                onSelected = onScreenSelected
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                content()
            }
        }
    }
}
