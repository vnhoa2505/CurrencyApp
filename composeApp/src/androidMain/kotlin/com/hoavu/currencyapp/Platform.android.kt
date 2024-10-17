package com.hoavu.currencyapp

actual enum class PlatformType {
    ANDROID,
    IOS
}

actual fun getPlatformType(): PlatformType {
    return PlatformType.ANDROID
}