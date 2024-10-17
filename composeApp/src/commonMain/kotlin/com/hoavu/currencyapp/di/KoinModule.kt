package com.hoavu.currencyapp.di

import com.hoavu.currencyapp.data.local.MongoImpl
import com.hoavu.currencyapp.data.local.PreferencesImpl
import com.hoavu.currencyapp.data.remote.api.CurrencyApiServiceImpl
import com.hoavu.currencyapp.domain.CurrencyApiService
import com.hoavu.currencyapp.domain.MongoRepository
import com.hoavu.currencyapp.domain.PreferencesRepository
import com.hoavu.currencyapp.presentation.screen.HomeViewModel
import com.russhwolf.settings.Settings
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single { Settings() }
    single<PreferencesRepository> { PreferencesImpl(settings = get()) }
    single<MongoRepository> { MongoImpl() }
    single<CurrencyApiService> { CurrencyApiServiceImpl(preference = get()) }
}

val viewModelModule = module {
    factory {
        HomeViewModel(
            preferences = get(),
            mongoDb = get(),
            api = get(),
        )
    }
}

fun initializeKoin() {
    startKoin {
        modules(appModule, viewModelModule)
    }
}