package com.hoavu.currencyapp.domain

import com.hoavu.currencyapp.domain.model.Currency
import com.hoavu.currencyapp.domain.model.RequestState

interface CurrencyApiService {
    suspend fun getLatestExchangeRates(): RequestState<List<Currency>>
}