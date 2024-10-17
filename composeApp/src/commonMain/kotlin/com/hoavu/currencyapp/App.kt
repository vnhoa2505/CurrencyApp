package com.hoavu.currencyapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.hoavu.currencyapp.presentation.screen.HomeScreen
import com.hoavu.currencyapp.ui.theme.darkScheme
import com.hoavu.currencyapp.ui.theme.lightScheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val colors = if (!isSystemInDarkTheme()) lightScheme else darkScheme

    MaterialTheme(colorScheme = colors) {
        Navigator(HomeScreen())
    }
}