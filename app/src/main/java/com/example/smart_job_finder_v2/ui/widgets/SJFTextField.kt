package com.example.smart_job_finder_v2.ui.widgets

import android.widget.Button
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun SJFTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: Painter? = null,
    trailingIconContentDescription: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    maxLines: Int = 1
) {
    OutlinedTextField(
        isError = isError,
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        modifier = modifier,
        singleLine = singleLine,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        trailingIcon = {
            trailingIcon?.let {
                Icon(
                    painter = it,
                    contentDescription = trailingIconContentDescription
                )
            }
        }
    )
}