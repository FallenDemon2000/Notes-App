package com.example.notesapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notesapp.presentation.screens.HOME_SCREEN_ROUTE
import com.example.notesapp.presentation.screens.HomeScreen

@Composable
fun NotesAppScaffold() {
    Scaffold {
        AppNavHost(
            navController = rememberNavController(),
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        )
    }
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HOME_SCREEN_ROUTE,
        modifier = modifier,
    ) {
        composable(HOME_SCREEN_ROUTE) {
            HomeScreen()
        }
    }
}
