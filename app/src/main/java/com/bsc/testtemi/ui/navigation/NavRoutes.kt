package com.bsc.testtemi.ui.navigation

sealed class NavRoutes(val route: String) {
    object ListSetorRoute : NavRoutes("ListSetorScreen")
    object ListSubSetorScreen : NavRoutes("ListSubSetorScreen/{descSetor}")
    object ListProdutosScreen : NavRoutes("ListProdutosScreen")
}