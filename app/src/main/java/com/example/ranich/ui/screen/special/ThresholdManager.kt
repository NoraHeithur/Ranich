package com.example.ranich.ui.screen.special

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import com.example.ranich.R
import com.example.ranich.common.SessionManager
import com.example.ranich.domain.common.SensorObserver
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.theme.Color_Primary
import org.koin.compose.koinInject

@Composable
fun ThresholdManager(
    modifier: Modifier,
) {

    val sensorManager: SensorObserver = koinInject()
    val sessionManager: SessionManager = koinInject()

    Column(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.margin_padding_16))
            .fillMaxSize()
    ) {

        var acceleroThresholds by remember { mutableStateOf(TextFieldValue("")) }
        var acceleroAlpha by remember { mutableStateOf(TextFieldValue("")) }
        var gyroThresholds by remember { mutableStateOf(TextFieldValue("")) }
        var gyroAlpha by remember { mutableStateOf(TextFieldValue("")) }
        val token = sessionManager.getCloudMessageUUID()

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = acceleroThresholds,
            onValueChange = {
                acceleroThresholds = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            placeholder = {
                RobotoText(modifier = Modifier, text = "Accelerometer thresholds")
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_16)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color_Primary,
                unfocusedBorderColor = Color_Primary,
                focusedTextColor = Color_Primary,
                unfocusedTextColor = Color_Primary,
            )
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = acceleroAlpha,
            onValueChange = {
                acceleroAlpha = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            placeholder = {
                RobotoText(modifier = Modifier, text = "Accelerometer alpha")
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_16)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color_Primary,
                unfocusedBorderColor = Color_Primary,
                focusedTextColor = Color_Primary,
                unfocusedTextColor = Color_Primary,
            )
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = gyroThresholds,
            onValueChange = {
                gyroThresholds = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            placeholder = {
                RobotoText(modifier = Modifier, text = "Gyroscope thresholds")
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_16)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color_Primary,
                unfocusedBorderColor = Color_Primary,
                focusedTextColor = Color_Primary,
                unfocusedTextColor = Color_Primary,
            )
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = gyroAlpha,
            onValueChange = {
                gyroAlpha = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            placeholder = {
                RobotoText(modifier = Modifier, text = "Gyroscope low-pass filter")
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_16)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color_Primary,
                unfocusedBorderColor = Color_Primary,
                focusedTextColor = Color_Primary,
                unfocusedTextColor = Color_Primary,
            )
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = token,
            onValueChange = {},
            readOnly = true,
            singleLine = false,
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_16)),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color_Primary,
                unfocusedBorderColor = Color_Primary,
                focusedTextColor = Color_Primary,
                unfocusedTextColor = Color_Primary,
            )
        )

        Spacer(modifier = Modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                sensorManager.setThreshold(
                    accelerometer = if (acceleroThresholds.text.isNotEmpty()) acceleroThresholds.text.toFloat() else 15.0f,
                    accelerometerAlpha = if (acceleroAlpha.text.isNotEmpty()) acceleroAlpha.text.toFloat() else 0.8f,
                    gyroscope = if (gyroThresholds.text.isNotEmpty()) gyroThresholds.text.toFloat() else 3.0f,
                    gyroscopeAlpha = if (gyroAlpha.text.isNotEmpty()) gyroAlpha.text.toFloat() else 0.2f,
                )
            }
        ) {
            RobotoText(
                modifier = Modifier,
                text = "SET",
                color = Color.White
            )
        }
    }
}