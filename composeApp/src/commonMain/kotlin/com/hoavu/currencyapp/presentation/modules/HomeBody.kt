package com.hoavu.currencyapp.presentation.modules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hoavu.currencyapp.domain.model.Currency
import com.hoavu.currencyapp.domain.model.RequestState
import com.hoavu.currencyapp.ui.theme.headerColor
import com.hoavu.currencyapp.util.DoubleConverter
import com.hoavu.currencyapp.util.GetFontFamily
import com.hoavu.currencyapp.util.calculateExchangeRate
import com.hoavu.currencyapp.util.convert
import currencyapp.composeapp.generated.resources.Res
import currencyapp.composeapp.generated.resources.convert
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeBody(
    source: RequestState<Currency>,
    target: RequestState<Currency>,
    amount: Double,
    modifier: Modifier = Modifier
) {
    var exchangedAmount by rememberSaveable { mutableStateOf(0.0) }
    val animatedExchangedAmount by animateValueAsState(
        targetValue = exchangedAmount,
        animationSpec = tween(durationMillis = 500),
        typeConverter = DoubleConverter()
    )

    val controller = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .imePadding()
            .padding(bottom = 24.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                controller?.hide()
                focusManager.clearFocus()
            }
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(all = 12.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "$animatedExchangedAmount",
                    style = TextStyle(
                        fontSize = 60.sp,
                        lineHeight = (60 * 1.2).sp,
                        fontFamily = GetFontFamily(),
                    ),
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                    textAlign = TextAlign.Center,
                )
            }
            item {
                AnimatedVisibility(visible = source.isSuccess() && target.isSuccess()) {
                    Column {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "1 ${source.getSuccessData().code} = " +
                                    "${
                                        calculateExchangeRate(
                                            source = source.getSuccessData().value,
                                            target = target.getSuccessData().value
                                        )
                                    } " +
                                    target.getSuccessData().code,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f)
                            else Color.Black.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(20f, type = TextUnitType.Sp),
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "1 ${target.getSuccessData().code} = " +
                                    "${
                                        calculateExchangeRate(
                                            source = target.getSuccessData().value,
                                            target = source.getSuccessData().value
                                        )
                                    } " +
                                    source.getSuccessData().code,
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            color = if (isSystemInDarkTheme()) Color.White.copy(alpha = 0.5f)
                            else Color.Black.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(20f, type = TextUnitType.Sp),
                        )
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(horizontal = 24.dp)
                .background(
                    color = Color.Unspecified,
                    shape = RoundedCornerShape(99.dp)
                ),
            onClick = {
                if (source.isSuccess() && target.isSuccess()) {
                    val exchangeRate = calculateExchangeRate(
                        source = source.getSuccessData().value,
                        target = target.getSuccessData().value
                    )
                    exchangedAmount = convert(
                        amount = amount,
                        exchangeRate = exchangeRate
                    )
                    controller?.hide()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = headerColor,
                contentColor = Color.White,
            )
        ) {
            Text(text = stringResource(Res.string.convert))
        }
    }
}