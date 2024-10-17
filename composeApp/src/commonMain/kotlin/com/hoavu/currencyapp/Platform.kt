package com.hoavu.currencyapp

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect enum class PlatformType {
    ANDROID,
    IOS
}

expect fun getPlatformType(): PlatformType