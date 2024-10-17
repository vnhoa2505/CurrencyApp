package com.hoavu.currencyapp.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hoavu.currencyapp.util.DecimalFormatter
import com.hoavu.currencyapp.util.DecimalInputVisualTransformation
import currencyapp.composeapp.generated.resources.Res
import currencyapp.composeapp.generated.resources.amount_input_placeholder
import org.jetbrains.compose.resources.stringResource

@Composable
fun AmountInput(
    amount: String,
    modifier: Modifier = Modifier,
    decimalFormatter: DecimalFormatter = DecimalFormatter(),
    onAmountChange: (String) -> Unit,
) {
    val visualTransformation = DecimalInputVisualTransformation(decimalFormatter)

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = 8.dp))
            .animateContentSize()
            .height(54.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White.copy(alpha = 0.05f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.05f),
            disabledContainerColor = Color.White.copy(alpha = 0.05f),
            errorContainerColor = Color.White.copy(alpha = 0.05f),
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color.White,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
        ),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.amount_input_placeholder),
                style = TextStyle(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
        },
        value = amount,
        visualTransformation = visualTransformation,
        onValueChange = { newText ->
            if (newText.length <= 15) {
                val cleanedText = decimalFormatter.cleanup(newText)
                onAmountChange.invoke(cleanedText)
            }
        }
    )
}