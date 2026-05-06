package com.example.finflow.ui.fragments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.components.SurfaceCard
import com.example.finflow.ui.theme.FinBlue
import com.example.finflow.ui.theme.FinInk
import com.example.finflow.ui.theme.FinMuted

@Composable
fun LoginHeaderFragment() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "FinFlow",
            color = FinInk,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Entre para acessar suas telas financeiras no aplicativo.",
            color = FinMuted,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun LoginFormFragment(
    email: String,
    password: String,
    canLogin: Boolean,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit
) {
    SurfaceCard {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = "Login",
                    color = FinInk,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Use suas credenciais para continuar.",
                    color = FinMuted,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = { Text("Senha") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Button(
                onClick = onLogin,
                enabled = canLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(containerColor = FinBlue)
            ) {
                Text(
                    text = "Entrar no app",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
