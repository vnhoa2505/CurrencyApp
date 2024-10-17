package com.hoavu.currencyapp.presentation.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.hoavu.currencyapp.PlatformType
import com.hoavu.currencyapp.domain.model.Currency
import com.hoavu.currencyapp.domain.model.CurrencyType
import com.hoavu.currencyapp.domain.model.RateStatus
import com.hoavu.currencyapp.domain.model.RequestState
import com.hoavu.currencyapp.getPlatformType
import com.hoavu.currencyapp.presentation.component.AmountInput
import com.hoavu.currencyapp.presentation.component.CurrencyInputs
import com.hoavu.currencyapp.presentation.component.RatesStatus
import com.hoavu.currencyapp.ui.theme.headerColor

@Composable
fun HomeHeader(
    status: RateStatus,
    source: RequestState<Currency>,
    target: RequestState<Currency>,
    amount: String,
    modifier: Modifier = Modifier,
    onRatesRefresh: () -> Unit,
    onSwitchClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onCurrencyTypeSelect: (CurrencyType) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 12.dp,
                    bottomEnd = 12.dp,
                )
            )
            .background(headerColor)
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = if (getPlatformType() == PlatformType.ANDROID) 24.dp else 48.dp,
                bottom = 24.dp
            )
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        RatesStatus(
            status = status,
            onRatesRefresh = onRatesRefresh,
        )
        Spacer(modifier = Modifier.height(24.dp))
        CurrencyInputs(
            source = source,
            target = target,
            onSwitchClick = onSwitchClick,
            onCurrencyTypeSelect = onCurrencyTypeSelect,
        )
        Spacer(modifier = Modifier.height(24.dp))
        AmountInput(
            amount = amount,
            onAmountChange = onAmountChange,
        )
    }
}