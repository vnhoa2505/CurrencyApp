package com.hoavu.currencyapp.util

import com.hoavu.currencyapp.PlatformType
import com.hoavu.currencyapp.getPlatformType

class DecimalFormatter(
    private val thousandsSeparator: Char = ',',
    private val decimalSeparator: Char = '.'
) {
    fun cleanup(input: String): String {

        val adjustedInput = when (getPlatformType()) {
            PlatformType.IOS -> input.replace(",", ".")
            else -> input
        }

        if (adjustedInput.matches("\\D".toRegex())) return ""
        if (adjustedInput.matches("0+".toRegex())) return "0"

        val sb = StringBuilder()

        var hasDecimalSep = false

        for (char in adjustedInput) {
            if (char.isDigit()) {
                sb.append(char)
                continue
            }
            if (char == decimalSeparator && !hasDecimalSep && sb.isNotEmpty()) {
                sb.append(char)
                hasDecimalSep = true
            }
        }

        var result = sb.toString()

        if (result.contains(decimalSeparator)) {
            val parts = result.split(decimalSeparator)
            val integerPart = parts[0]
            var decimalPart = parts.getOrNull(1) ?: ""

            if (decimalPart != "0") {
                decimalPart = decimalPart.trimEnd('0')
            }

            result = if (decimalPart.isEmpty()) {
                "$integerPart$decimalSeparator"
            } else {
                "$integerPart$decimalSeparator$decimalPart"
            }
        }

        return result
    }

    fun formatForVisual(input: String): String {

        val split = input.split(decimalSeparator)

        val intPart = split[0]
            .reversed()
            .chunked(3)
            .joinToString(separator = thousandsSeparator.toString())
            .reversed()

        val fractionPart = split.getOrNull(1)

        return if (fractionPart == null) intPart else intPart + decimalSeparator + fractionPart
    }
}