package com.hanbang.home.lottoad

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import coil3.compose.AsyncImage
import com.hanbang.designsystem.navigation.TopAppBar
import com.hanbang.designsystem.theme.Primary3
import com.hanbang.designsystem.theme.Primary5
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.home.R
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun LottoAdScreen(
    viewModel: LottoAdViewModel = hiltViewModel(),
    navigateToLottoRecommend: () -> Unit
) {
    viewModel.collectSideEffect {
        when (it) {
            LottoAdEvent.NavigateToLottoRecommend -> {
                navigateToLottoRecommend()
            }
        }
    }

    LifecycleResumeEffect(Unit) {
        viewModel.onResume()
        onPauseOrDispose {
            viewModel.onPause()
        }
    }

    val state = viewModel.collectAsState().value
    LottoAdContent(state = state)
}

@Composable
private fun LottoAdContent(
    state: LottoAdUiState
) {
    val alphaAnim = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        while (true) {
            alphaAnim.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
            alphaAnim.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 500, easing = LinearEasing)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF312354),
                        Primary3
                    )
                )
            )
            .systemBarsPadding()
    ) {
        TopAppBar(
            containerColor = Color.Transparent,
            contentColor = Color.White,
        )
        Text(
            text = "${state.userName}의 사주 분석 완료",
            style = SattoTheme.typography.body14Medium,
            color = Primary5,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "그대에게 딱 맞는\n번호를 추천 중이라네...",
            style = SattoTheme.typography.headline24Bold,
            color = White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        AsyncImage(
            model = R.drawable.img_lotto_ad,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .alpha(alphaAnim.value)
        )
    }
}

@Preview
@Composable
private fun LottoAdScreenPreview() {
    SattoTheme {
        LottoAdContent(
            state = LottoAdUiState(
                userName = "user name"
            )
        )
    }
}