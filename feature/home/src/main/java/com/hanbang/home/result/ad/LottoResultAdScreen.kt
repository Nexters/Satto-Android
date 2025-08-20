package com.hanbang.home.result.ad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hanbang.designsystem.navigation.TopAppBar
import com.hanbang.designsystem.navigation.TopAppBarTitleType
import com.hanbang.designsystem.theme.Primary3
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.home.R
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun LottoResultAdScreen(
    viewModel: LottoResultAdViewModel = hiltViewModel(),
    navigateToLottoResult: (RouteLottoResultModel) -> Unit = {},
    navigateToBack: () -> Unit = {}
) {
    viewModel.collectSideEffect {
        when (it) {
            is LottoResultAdEvent.NavigateToResult -> navigateToLottoResult(it.args)
        }
    }

    LifecycleResumeEffect(Unit) {
        viewModel.onResume()
        onPauseOrDispose {
            viewModel.onPause()
        }
    }

    val state = viewModel.collectAsState().value

    LottoResultAdContent(
        state = state,
        navigateToBack = navigateToBack
    )
}

@Composable
private fun LottoResultAdContent(
    state: LottoResultAdUiState,
    navigateToBack: () -> Unit = {}
) {
    val isPlaying = remember(state.isPlaying) { mutableStateOf(state.isPlaying) }

    val pigLottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lotto_result_pig))
    val pigLottieProgress by animateLottieCompositionAsState(
        composition = pigLottieComposition,
        isPlaying = isPlaying.value,
        iterations = LottieConstants.IterateForever,
    )

    val textLottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lotto_result_text))
    val textLottieProgress by animateLottieCompositionAsState(
        composition = textLottieComposition,
        isPlaying = isPlaying.value,
        iterations = 1
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF581AAF),
                        Primary3
                    )
                )
            )
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = "당첨 결과",
            containerColor = Color.Transparent,
            contentColor = White,
            titleType = TopAppBarTitleType.CENTER,
            onNavigationClick = navigateToBack
        )
        Column(
            modifier = Modifier
                .weight(1f, fill = true),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition = pigLottieComposition,
                progress = { pigLottieProgress }
            )
            LottieAnimation(
                composition = textLottieComposition,
                progress = { textLottieProgress }
            )
        }
    }
}

@Preview
@Composable
private fun LottoResultAdContentPreview() {
    SattoTheme {
        LottoResultAdContent(LottoResultAdUiState())
    }

}