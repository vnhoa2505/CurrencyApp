package com.hoavu.currencyapp.util

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.TwoWayConverter

class DoubleConverter: TwoWayConverter<Double, AnimationVector1D> {
    override val convertFromVector: (AnimationVector1D) -> Double
        get() = { vector ->
            vector.value.toDouble().roundToDecimalPlaces()
        }
    override val convertToVector: (Double) -> AnimationVector1D
        get() = { value ->
            AnimationVector1D(value.roundToDecimalPlaces().toFloat())
        }
}