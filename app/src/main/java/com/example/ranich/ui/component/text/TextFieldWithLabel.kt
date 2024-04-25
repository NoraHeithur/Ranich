package com.example.ranich.ui.component.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ranich.R
import com.example.ranich.ui.theme.Color_Primary

@Composable
fun TextFieldWithLabel(
    modifier: Modifier,
    label: String,
    isPassword: Boolean = false,
    fieldValue: String,
    onTextChanged: (String) -> Unit,
) {
    var textChange by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        RobotoText(
            modifier = Modifier,
            text = label,
            color = Color_Primary,
            size = dimensionResource(id = R.dimen.text_size_14).value.sp,
        )

        Spacer(modifier = Modifier.size(4.dp))

        OutlinedTextField(
            modifier = modifier,
            value = if (textChange.text.isEmpty()) TextFieldValue(fieldValue) else textChange,
            onValueChange = {
                textChange = it
                onTextChanged(it.text)
            },
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            minLines = dimensionResource(id = R.dimen.margin_padding_8).value.toInt(),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color_Primary,
                unfocusedBorderColor = Color_Primary,
                focusedTextColor = Color_Primary,
                unfocusedTextColor = Color_Primary,
            )
        )
    }
}