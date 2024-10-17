package com.hoavu.currencyapp.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.hoavu.currencyapp.domain.model.Currency
import com.hoavu.currencyapp.domain.model.CurrencyCode
import com.hoavu.currencyapp.domain.model.CurrencyType
import com.hoavu.currencyapp.ui.theme.primaryColor
import com.hoavu.currencyapp.ui.theme.surfaceColor
import com.hoavu.currencyapp.ui.theme.textColor
import currencyapp.composeapp.generated.resources.Res
import currencyapp.composeapp.generated.resources.cancel
import currencyapp.composeapp.generated.resources.confirm
import currencyapp.composeapp.generated.resources.search_here
import currencyapp.composeapp.generated.resources.select_a_currency
import org.jetbrains.compose.resources.stringResource

@Composable
fun CurrencyPickerDialog(
    currencies: List<Currency>,
    currencyType: CurrencyType,
    modifier: Modifier = Modifier,
    onConfirmClick: (CurrencyCode) -> Unit,
    onDismiss: () -> Unit,
) {
    val allCurrencies = remember(key1 = currencies) {
        mutableStateListOf<Currency>()
    }.apply { addAll(currencies) }

    var searchQuery by remember { mutableStateOf("") }
    var selectedCurrencyCode by remember(currencyType) {
        mutableStateOf(currencyType.code)
    }

    val listState = rememberLazyListState()

    LaunchedEffect(currencyType) {
        val scrollToIndex = currencies.indexOfFirst { it.code == currencyType.code.name }
        listState.scrollToItem(scrollToIndex)
    }

    AlertDialog(
        modifier = modifier,
        containerColor = surfaceColor,
        title = {
            Text(
                text = stringResource(Res.string.select_a_currency),
                color = textColor
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth()
                        .clip(RoundedCornerShape(size = 99.dp)),
                    value = searchQuery,
                    onValueChange = { query ->
                        searchQuery = query.uppercase()

                        val filteredCurrencies = if (query.isNotEmpty()) {
                            currencies.filter {
                                it.code.contains(query, ignoreCase = true)
                            }
                        } else {
                            currencies
                        }

                        allCurrencies.clear()
                        allCurrencies.addAll(filteredCurrencies)
                    },
                    placeholder = {
                        Text(
                            text = stringResource(Res.string.search_here),
                            color = textColor.copy(alpha = 0.38f),
                            fontSize = MaterialTheme.typography.bodySmall.fontSize
                        )
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = textColor,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = textColor.copy(alpha = 0.05f),
                        unfocusedContainerColor = textColor.copy(alpha = 0.05f),
                        disabledContainerColor = textColor.copy(alpha = 0.05f),
                        errorContainerColor = textColor.copy(alpha = 0.05f),
                        focusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = textColor
                    ),
                )

                Spacer(modifier = Modifier.height(20.dp))

                AnimatedContent(
                    targetState = allCurrencies
                ) { availableCurrencies ->
                    if (availableCurrencies.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            state = listState,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                items = availableCurrencies,
                                key = { it._id.toHexString() }
                            ) { currency ->
                                CurrencyCodePickerView(
                                    code = CurrencyCode.valueOf(currency.code),
                                    isSelected = selectedCurrencyCode.name == currency.code,
                                    onSelect = { selectedCurrencyCode = it }
                                )
                            }
                        }
                    } else {
                        ErrorScreen(modifier = Modifier.height(250.dp))
                    }
                }
            }
        },
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = stringResource(Res.string.cancel),
                    color = MaterialTheme.colorScheme.outline
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirmClick.invoke(selectedCurrencyCode) }) {
                Text(
                    text = stringResource(Res.string.confirm),
                    color = primaryColor
                )
            }
        }
    )
}