package com.example.ranich.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ranich.R
import com.example.ranich.ui.component.button.CustomButton
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.component.text.TextFieldWithLabel
import com.example.ranich.ui.navigation.MainTopLevelRoute
import com.example.ranich.ui.theme.Color_Primary
import org.koin.androidx.compose.koinViewModel

@Composable
fun LogInScreen(
    navController: NavController,
) {
    LogInScreenContainer(modifier = Modifier) {
        LogInScreenContent(
            modifier = Modifier,
            navController = navController
        )
    }
}

@Composable
fun LogInScreenContainer(
    modifier: Modifier,
    content: @Composable (Modifier) -> Unit,
) {
    Surface(
        modifier = modifier
            .background(Color.White)
            .padding(dimensionResource(id = R.dimen.margin_padding_32))
            .fillMaxSize(),
        color = Color.White
    ) {
        content(modifier)
    }
}

@Composable
fun LogInScreenContent(
    modifier: Modifier,
    navController: NavController,
    viewModel: LogInViewModel = koinViewModel(),
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        var userNameTextField by remember { mutableStateOf("") }
        var passwordTextField by remember { mutableStateOf("") }
        var onLogInButtonClick by remember { mutableStateOf(false) }

        RobotoText(
            modifier = modifier.fillMaxWidth(),
            text = "Welcome to ${stringResource(id = R.string.app_name)}",
            color = Color_Primary,
            size = dimensionResource(id = R.dimen.text_size_24).value.sp,
            align = TextAlign.Center
        )

        RobotoText(
            modifier = modifier
                .fillMaxWidth(),
            text = "The Fall Detection",
            color = Color_Primary,
            size = dimensionResource(id = R.dimen.text_size_32).value.sp,
            weight = FontWeight.Bold,
            align = TextAlign.Center
        )

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_32)))

        TextFieldWithLabel(
            modifier = modifier.fillMaxWidth(),
            label = "Username",
            fieldValue = userNameTextField,
            onTextChanged = {
                userNameTextField = it
            }
        )

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_24)))

        TextFieldWithLabel(
            modifier = modifier.fillMaxWidth(),
            label = "Password",
            fieldValue = passwordTextField,
            isPassword = true,
            onTextChanged = {
                passwordTextField = it
            }
        )

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_24)))

        CustomButton(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            label = "Sign In",
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
            isEnabled = userNameTextField.isNotEmpty() && passwordTextField.isNotEmpty()
        ) {
            onLogInButtonClick = true
            viewModel.logIn(userNameTextField, passwordTextField)
            navController.navigate(MainTopLevelRoute.CENTER)
        }

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_8)))

        TextButton(
            modifier = modifier
                .height(56.dp)
                .fillMaxWidth(),
            onClick = {
                // do nothing
            },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.margin_padding_8)),
        ) {
            RobotoText(
                modifier = modifier.fillMaxWidth(),
                text = "Forgot Password",
                color = Color_Primary,
                weight = FontWeight.SemiBold,
                align = TextAlign.Center,
                size = dimensionResource(id = R.dimen.text_size_16).value.sp,
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun LogInScreenPreview() {
    val mainNavController = rememberNavController()
    LogInScreen(mainNavController)
}