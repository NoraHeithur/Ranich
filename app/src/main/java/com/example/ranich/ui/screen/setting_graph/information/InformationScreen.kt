package com.example.ranich.ui.screen.setting_graph.information

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ranich.R
import com.example.ranich.ui.component.button.CustomButton
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.component.text.TextFieldWithLabel
import com.example.ranich.ui.screen.setting_graph.SettingViewModel
import com.example.ranich.ui.theme.Color_Primary
import org.koin.androidx.compose.koinViewModel

@Composable
fun InformationScreen(
    modifier: Modifier,
    viewModel: SettingViewModel = koinViewModel(),
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(dimensionResource(id = R.dimen.margin_padding_16)),
        color = Color.White
    ) {
        val info by viewModel.userInfoState.collectAsStateWithLifecycle()

        var fullName by remember { mutableStateOf(info.fullName) }
        var phoneNumber by remember { mutableStateOf(info.phoneNumber) }
        var age by remember { mutableStateOf(info.age) }
        var gender by remember { mutableStateOf(info.gender) }
        var treatmentHistory by remember { mutableStateOf(info.treatmentHistory) }

        Column {
            TextFieldWithLabel(
                modifier = modifier.fillMaxWidth(),
                label = "Name",
                fieldValue = info.fullName,
                onTextChanged = {
                    fullName = it
                }
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            Row {
                Row(
                    modifier = modifier.weight(0.6f)
                ) {
                    TextFieldWithLabel(
                        modifier = modifier.fillMaxWidth(),
                        label = "Phone number",
                        fieldValue = info.phoneNumber,
                        onTextChanged = {
                            phoneNumber = it
                        }
                    )
                }


                Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

                Row(
                    modifier = modifier.weight(0.4f)
                ) {
                    TextFieldWithLabel(
                        modifier = modifier.fillMaxWidth(),
                        label = "Age",
                        fieldValue = info.age,
                        onTextChanged = {
                            age = it
                        }
                    )
                }
            }

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            TextFieldWithLabel(
                modifier = modifier.fillMaxWidth(),
                label = "Gender",
                fieldValue = info.gender,
                onTextChanged = {
                    gender = it
                }
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            RobotoText(
                modifier = Modifier,
                text = "Treatment History",
                color = Color_Primary,
                size = dimensionResource(id = R.dimen.text_size_14).value.sp,
            )

            Spacer(modifier = Modifier.size(4.dp))

            OutlinedTextField(
                modifier = modifier.fillMaxWidth(),
                value = info.treatmentHistory,
                onValueChange = {
                    treatmentHistory = it
                },
                singleLine = false,
                minLines = dimensionResource(id = R.dimen.margin_padding_8).value.toInt(),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_16)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color_Primary,
                    unfocusedBorderColor = Color_Primary,
                    focusedTextColor = Color_Primary,
                    unfocusedTextColor = Color_Primary,
                )
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            CustomButton(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                label = "Confirm",
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
            ) {

            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun InformationScreenPreview() {
    InformationScreen(Modifier)
}