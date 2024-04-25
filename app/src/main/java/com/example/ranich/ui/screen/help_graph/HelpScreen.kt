package com.example.ranich.ui.screen.help_graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ranich.R
import com.example.ranich.ui.component.button.CustomButton
import com.example.ranich.ui.component.button.IconPosition
import com.example.ranich.ui.component.ripple.CircleCustomRippleEffect
import com.example.ranich.ui.component.text.RobotoText
import com.example.ranich.ui.theme.Color_Primary
import com.example.ranich.ui.theme.Color_Secondary
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun HelpScreen(
    modifier: Modifier,
) {
    HelpScreenContainer(
        modifier = modifier
    ) {
        HelpScreenContent(
            modifier = Modifier
        )
    }
}

@Composable
fun HelpScreenContainer(
    modifier: Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(all = dimensionResource(id = R.dimen.margin_padding_16))
    ) {
        content()
    }
}

@Composable
fun HelpScreenContent(
    modifier: Modifier,
    viewModel: HelpViewModel = koinViewModel(),
) {
    val isGyroscopeDetect = viewModel.sensorGyroDetect.collectAsStateWithLifecycle()
    val isAccelDetect = viewModel.sensorAccelDetect.collectAsStateWithLifecycle()
    var callingForHelpState by remember { mutableStateOf(false) }
    var finishedCountingState by remember { mutableStateOf(false) }
    val showSystemDetectByGyroscope by remember { isGyroscopeDetect }
    val showSystemDetectByAccelerometer by remember { isAccelDetect }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        when {
            !callingForHelpState && !showSystemDetectByGyroscope && !showSystemDetectByAccelerometer  -> {
                viewModel.restartSensor()
                CallingForHelpRippleButton(
                    modifier = modifier,
                    callingForHelpState = callingForHelpState,
                    boxScope = this,
                    onButtonClicked = {
                        callingForHelpState = it
                    }
                )
            }

            callingForHelpState && !showSystemDetectByGyroscope && !showSystemDetectByAccelerometer -> {
                CountDownTimerForCallingHelp(
                    modifier = modifier,
                    finishedCountingState = finishedCountingState,
                    onFinishedCounting = {
                        finishedCountingState = it
                        viewModel.manualRequestForHelp()
                    },
                    onButtonClicked = {
                        callingForHelpState = it
                    }
                )
            }

            finishedCountingState -> {
                viewModel.stopSensor()
                SystemDetectButtons(
                    modifier = modifier,
                    onSosButtonClicked = {
                        viewModel.manualRequestForHelp()
                    },
                    onCancelButtonClicked = {
                        callingForHelpState = it
                        finishedCountingState = it
                        viewModel.cancelRequest()
                    }
                )
            }

            showSystemDetectByGyroscope -> {
                viewModel.requestForHelpByGyroscope()
                viewModel.stopSensor()
                SystemDetectButtons(
                    modifier = modifier,
                    onSosButtonClicked = {
                        viewModel.manualRequestForHelp()
                    },
                    onCancelButtonClicked = {
                        callingForHelpState = it
                        finishedCountingState = it
                        viewModel.cancelRequest()
                    }
                )
            }

            showSystemDetectByAccelerometer-> {
                viewModel.requestForHelpByAccelerometer()
                viewModel.stopSensor()
                SystemDetectButtons(
                    modifier = modifier,
                    onSosButtonClicked = {
                        viewModel.manualRequestForHelp()
                    },
                    onCancelButtonClicked = {
                        callingForHelpState = it
                        finishedCountingState = it
                        viewModel.cancelRequest()
                    }
                )
            }
        }
    }
}

@Composable
fun CallingForHelpRippleButton(
    modifier: Modifier,
    callingForHelpState: Boolean,
    boxScope: BoxScope,
    onButtonClicked: (Boolean) -> Unit,
) {
    boxScope.apply {
        Box(
            modifier = modifier
                .align(Alignment.Center),
        ) {

            CircleCustomRippleEffect(modifier = modifier)

            Button(
                modifier = modifier
                    .size(250.dp)
                    .align(Alignment.Center),
                onClick = { onButtonClicked(!callingForHelpState) },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color_Secondary
                )
            ) {
                RobotoText(
                    modifier = modifier,
                    text = stringResource(R.string.label_help_alert).uppercase(),
                    size = dimensionResource(id = R.dimen.text_size_40).value.sp,
                    weight = FontWeight.Bold,
                    align = TextAlign.Center,
                    maxLines = Int.MAX_VALUE,
                    lineHeight = dimensionResource(id = R.dimen.text_size_40).value.sp
                )
            }
        }
    }
}

@Composable
fun CountDownTimerForCallingHelp(
    modifier: Modifier,
    finishedCountingState: Boolean,
    onFinishedCounting: (Boolean) -> Unit,
    onButtonClicked: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        var timeLeft by remember { mutableIntStateOf(5) }

        LaunchedEffect(key1 = timeLeft, key2 = finishedCountingState) {

            while (timeLeft > 0 && !finishedCountingState) {
                delay(1000)
                timeLeft--
            }

            if (timeLeft == 0) {
                onFinishedCounting(true)
            }
        }

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_24)))

        RobotoText(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.5f),
            text = stringResource(R.string.lable_calling_for_help_in),
            size = dimensionResource(id = R.dimen.text_size_32).value.sp,
            weight = FontWeight.Bold,
            align = TextAlign.Center,
        )

        RobotoText(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            text = "$timeLeft",
            size = dimensionResource(id = R.dimen.text_size_200).value.sp,
            color = Color_Secondary,
            weight = FontWeight.Bold,
            align = TextAlign.Center,
            maxLines = 2
        )

        CustomButton(
            modifier = modifier
                .width(160.dp)
                .align(Alignment.CenterHorizontally),
            label = "Cancel I'm OK",
            isIconButton = true,
            iconPosition = IconPosition.END,
            onButtonClicked = {
                onButtonClicked(false)
            }
        )

        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_24)))
    }
}

@Composable
fun SystemDetectButtons(
    modifier: Modifier,
    onSosButtonClicked: () -> Unit,
    onCancelButtonClicked: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

        Column(
            modifier = modifier.fillMaxWidth()
        ) {
            Icon(
                modifier = modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                imageVector = ImageVector.vectorResource(R.drawable.ic_accident),
                contentDescription = null,
                tint = Color_Primary
            )

            Spacer(modifier = modifier.size(dimensionResource(id = R.dimen.margin_padding_16)))

            RobotoText(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(R.string.label_system_detects_you_ve_taken_a_fall),
                size = dimensionResource(id = R.dimen.text_size_32).value.sp,
                weight = FontWeight.Bold,
                align = TextAlign.Center,
                maxLines = 2,
                lineHeight = dimensionResource(id = R.dimen.text_size_32).value.sp,
            )
        }

        Column(
            modifier = modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomButton(
                modifier = modifier
                    .width(160.dp),
                label = stringResource(R.string.label_sos),
                onButtonClicked = {
                    onSosButtonClicked()
                }
            )

            CustomButton(
                modifier = modifier
                    .width(160.dp),
                label = stringResource(R.string.label_cancel_i_m_ok),
                isPrimaryIcon = false,
                isIconButton = true,
                iconPosition = IconPosition.END,
                onButtonClicked = {
                    onCancelButtonClicked(false)
                }
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
private fun HelpScreenPreview() {
    HelpScreen(modifier = Modifier)
}