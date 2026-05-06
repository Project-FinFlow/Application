package com.example.finflow.controllers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.finflow.ui.FinScreen

@Stable
class AuthController(
    initialEmail: String = "admin@finflow.com",
    initialPassword: String = "123456"
) {
    var email by mutableStateOf(initialEmail)
        private set

    var password by mutableStateOf(initialPassword)
        private set

    var isLoggedIn by mutableStateOf(false)
        private set

    val canLogin: Boolean
        get() = email.isNotBlank() && password.isNotBlank()

    fun onEmailChange(value: String) {
        email = value
    }

    fun onPasswordChange(value: String) {
        password = value
    }

    fun login() {
        if (canLogin) {
            isLoggedIn = true
        }
    }

    fun logout() {
        isLoggedIn = false
    }
}

@Stable
class NavigationController(
    initialScreen: FinScreen = FinScreen.Despesas
) {
    var selectedScreen by mutableStateOf(initialScreen)
        private set

    fun select(screen: FinScreen) {
        selectedScreen = screen
    }

    fun reset() {
        selectedScreen = FinScreen.Despesas
    }
}

@Stable
class FinFlowController(
    val authController: AuthController = AuthController(),
    val navigationController: NavigationController = NavigationController()
) {
    fun login() {
        authController.login()
    }

    fun logout() {
        authController.logout()
        navigationController.reset()
    }
}

@Composable
fun rememberAuthController(): AuthController = rememberSaveable(saver = AuthControllerSaver) {
    AuthController()
}

@Composable
fun rememberNavigationController(): NavigationController = rememberSaveable(saver = NavigationControllerSaver) {
    NavigationController()
}

@Composable
fun rememberFinFlowController(): FinFlowController {
    val authController = rememberAuthController()
    val navigationController = rememberNavigationController()

    return remember(authController, navigationController) {
        FinFlowController(
            authController = authController,
            navigationController = navigationController
        )
    }
}

private val AuthControllerSaver = Saver<AuthController, List<String>>(
    save = { controller ->
        listOf(
            controller.email,
            controller.password,
            controller.isLoggedIn.toString()
        )
    },
    restore = { saved ->
        AuthController(
            initialEmail = saved.getOrElse(0) { "" },
            initialPassword = saved.getOrElse(1) { "" }
        ).apply {
            if (saved.getOrElse(2) { "false" }.toBoolean()) {
                login()
            }
        }
    }
)

private val NavigationControllerSaver = Saver<NavigationController, String>(
    save = { controller -> controller.selectedScreen.name },
    restore = { savedScreenName ->
        NavigationController(
            initialScreen = runCatching {
                FinScreen.valueOf(savedScreenName)
            }.getOrDefault(FinScreen.Despesas)
        )
    }
)
