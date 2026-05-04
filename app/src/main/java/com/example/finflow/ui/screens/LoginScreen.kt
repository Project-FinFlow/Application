package com.example.finflow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finflow.ui.components.AppBackgroundBrush
import com.example.finflow.ui.components.InfoPill
import com.example.finflow.ui.components.MetricCard
import com.example.finflow.ui.components.SurfaceCard
import com.example.finflow.ui.data.Metric
import com.example.finflow.ui.theme.FinBlue
import com.example.finflow.ui.theme.FinFLowTheme
import com.example.finflow.ui.theme.FinInk
import com.example.finflow.ui.theme.FinMuted

@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("admin@finflow.com") }
    var password by rememberSaveable { mutableStateOf("123456") }

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
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        label = { Text("Senha") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Button(
                        onClick = onLogin,
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
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 920)
@Composable
private fun LoginScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        LoginScreen(onLogin = {})
    }
}
