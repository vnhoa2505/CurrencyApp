package com.hoavu.currencyapp.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import currencyapp.composeapp.generated.resources.Res
import currencyapp.composeapp.generated.resources.no_data
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String? = null,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = message ?: stringResource(Res.string.no_data),
            textAlign = TextAlign.Center
        )
    }
}