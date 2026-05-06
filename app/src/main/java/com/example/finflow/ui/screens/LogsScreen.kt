package com.example.finflow.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.finflow.controllers.LogsController
import com.example.finflow.controllers.rememberLogsController
import com.example.finflow.ui.fragments.LogScreenFragment
import com.example.finflow.ui.theme.FinFLowTheme

@Composable
fun LogsScreen(
    controller: LogsController = rememberLogsController()
) {
    LogScreenFragment(
        state = controller.state,
        onAction = controller::refresh
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 720)
@Composable
private fun LogsScreenPreview() {
    FinFLowTheme(darkTheme = false) {
        LogsScreen(controller = rememberLogsController())
    }
}
