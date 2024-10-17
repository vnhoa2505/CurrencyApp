package com.hoavu.currencyapp.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryLight = Color(0xFF0D6B58)
val onPrimaryLight = Color(0xFFFFFFFF)
val primaryContainerLight = Color(0xFFA1F2DA)
val onPrimaryContainerLight = Color(0xFF002019)
val secondaryLight = Color(0xFF4B635B)
val onSecondaryLight = Color(0xFFFFFFFF)
val secondaryContainerLight = Color(0xFFCDE9DE)
val onSecondaryContainerLight = Color(0xFF07201A)
val tertiaryLight = Color(0xFF426277)
val onTertiaryLight = Color(0xFFFFFFFF)
val tertiaryContainerLight = Color(0xFFC6E7FF)
val onTertiaryContainerLight = Color(0xFF001E2D)
val errorLight = Color(0xFFBA1A1A)
val onErrorLight = Color(0xFFFFFFFF)
val errorContainerLight = Color(0xFFFFDAD6)
val onErrorContainerLight = Color(0xFF410002)
val backgroundLight = Color(0xFFF5FBF7)
val onBackgroundLight = Color(0xFF171D1B)
val surfaceLight = Color(0xFFF5FBF7)
val onSurfaceLight = Color(0xFF171D1B)
val surfaceVariantLight = Color(0xFFDBE5E0)
val onSurfaceVariantLight = Color(0xFF3F4945)
val outlineLight = Color(0xFF6F7975)
val outlineVariantLight = Color(0xFFBFC9C4)
val scrimLight = Color(0xFF000000)
val inverseSurfaceLight = Color(0xFF2B322F)
val inverseOnSurfaceLight = Color(0xFFECF2EE)
val inversePrimaryLight = Color(0xFF86D6BF)
val surfaceDimLight = Color(0xFFD5DBD8)
val surfaceBrightLight = Color(0xFFF5FBF7)
val surfaceContainerLowestLight = Color(0xFFFFFFFF)
val surfaceContainerLowLight = Color(0xFFEFF5F1)
val surfaceContainerLight = Color(0xFFE9EFEB)
val surfaceContainerHighLight = Color(0xFFE3EAE6)
val surfaceContainerHighestLight = Color(0xFFDEE4E0)

val primaryDark = Color(0xFF86D6BF)
val onPrimaryDark = Color(0xFF00382D)
val primaryContainerDark = Color(0xFF005142)
val onPrimaryContainerDark = Color(0xFFA1F2DA)
val secondaryDark = Color(0xFFB2CCC3)
val onSecondaryDark = Color(0xFF1D352E)
val secondaryContainerDark = Color(0xFF334C44)
val onSecondaryContainerDark = Color(0xFFCDE9DE)
val tertiaryDark = Color(0xFFA9CBE3)
val onTertiaryDark = Color(0xFF0F3447)
val tertiaryContainerDark = Color(0xFF294A5E)
val onTertiaryContainerDark = Color(0xFFC6E7FF)
val errorDark = Color(0xFFFFB4AB)
val onErrorDark = Color(0xFF690005)
val errorContainerDark = Color(0xFF93000A)
val onErrorContainerDark = Color(0xFFFFDAD6)
val backgroundDark = Color(0xFF0F1513)
val onBackgroundDark = Color(0xFFDEE4E0)
val surfaceDark = Color(0xFF0F1513)
val onSurfaceDark = Color(0xFFDEE4E0)
val surfaceVariantDark = Color(0xFF3F4945)
val onSurfaceVariantDark = Color(0xFFBFC9C4)
val outlineDark = Color(0xFF89938F)
val outlineVariantDark = Color(0xFF3F4945)
val scrimDark = Color(0xFF000000)
val inverseSurfaceDark = Color(0xFFDEE4E0)
val inverseOnSurfaceDark = Color(0xFF2B322F)
val inversePrimaryDark = Color(0xFF0D6B58)
val surfaceDimDark = Color(0xFF0F1513)
val surfaceBrightDark = Color(0xFF343B38)
val surfaceContainerLowestDark = Color(0xFF090F0D)
val surfaceContainerLowDark = Color(0xFF171D1B)
val surfaceContainerDark = Color(0xFF1B211F)
val surfaceContainerHighDark = Color(0xFF252B29)
val surfaceContainerHighestDark = Color(0xFF303634)


val seed = Color(0xFF006B58)


val freshColor = Color(0xFF44FF78)
val staleColor = Color(0xFFFF9E44)

val primaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF86A8FC)
    else Color(0xFF283556)

val headerColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF0C0C0C)
    else Color(0xFF283556)

val surfaceColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF161616)
    else Color(0xFFFFFFFF)

val textColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFFFF)
    else Color(0xFF000000)