package com.example.finflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finflow.controllers.AuthController
import com.example.finflow.controllers.rememberAuthController
import com.example.finflow.ui.components.AppBackgroundBrush
import com.example.finflow.ui.fragments.LoginFormFragment
import com.example.finflow.ui.fragments.LoginHeaderFragment
import com.example.finflow.ui.theme.FinFLowTheme

@Composable
fun LoginScreen(
    controller: AuthController = rememberAuthController(),
    onLogin: () -> Unit = controller::login
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackgroundBrush)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 520.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            LoginHeaderFragment()
            LoginFormFragment(
                email = controller.email,
                password = controller.password,
                canLogin = controller.canLogin,
                onEmailChange = controller::onEmailChange,
                onPasswordChange = controller::onPasswordChange,
                onLogin = onLogin
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 920)
@Composable
private fun LoginScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        LoginScreen(
            controller = rememberAuthController(),
            onLogin = {}
        )
    }
}
