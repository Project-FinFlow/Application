package com.example.finflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.finflow.ui.FinFlowApp
import com.example.finflow.ui.theme.FinFLowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinFLowTheme(darkTheme = false) {
                FinFlowApp()
            }
        }
    }
}
