package com.example.finflow.ui

enum class FinScreen(
    val label: String,
    val title: String,
    val subtitle: String
) {
    Despesas(
        label = "Despesas",
        title = "Despesas",
        subtitle = "Saidas e pagamentos cadastrados."
    ),
    Receitas(
        label = "Receitas",
        title = "Receitas",
        subtitle = "Entradas e valores recebidos."
    ),
    Categorias(
        label = "Categorias",
        title = "Categorias",
        subtitle = "Tipos usados para organizar movimentacoes."
    ),
    Metas(
        label = "Metas",
        title = "Metas",
        subtitle = "Objetivos financeiros e progresso."
    ),
    Logs(
        label = "Logs",
        title = "Logs",
        subtitle = "Eventos recentes e auditoria do app."
    ),
    Usuarios(
        label = "Usuarios",
        title = "Usuarios",
        subtitle = "Acessos, perfis e permissoes."
    )
}
