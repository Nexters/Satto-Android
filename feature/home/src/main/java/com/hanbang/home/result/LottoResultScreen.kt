package com.hanbang.home.result

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.navigation.TopAppBar
import com.hanbang.designsystem.navigation.TopAppBarTitleType
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Primary7
import com.hanbang.designsystem.theme.Primary9
import com.hanbang.designsystem.theme.SattoTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
internal fun LottoResultScreen(
    viewModel: LottoResultViewModel = hiltViewModel(),
    onClickBack: () -> Unit,
    onClickHome: () -> Unit
) {
    val state = viewModel.collectAsState().value
    LottoResultBackground(
        state = state,
        onClickBack = onClickBack,
        onClickHome = onClickHome
    )
}

@Composable
private fun LottoResultBackground(
    state: LottoResultUiState,
    onClickBack: () -> Unit = {},
    onClickHome: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Primary7, Primary9)
                )
            )
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        TopAppBar(
            title = "당첨 결과",
            titleType = TopAppBarTitleType.CENTER,
            contentColor = Gray1,
            containerColor = Color.Transparent,
            onNavigationClick = onClickBack
        )
        LottoResultContent(
            modifier = Modifier
                .weight(1f, fill = true)
        )
        HbBoxButton(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            text = "메인으로 가기",
            colors = HbButtonColorType.primary,
            styles = HbButtonStyles.large,
            onClick = onClickHome
        )
    }
}

@Composable
private fun LottoResultContent(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Column(
        modifier = modifier
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )
    ) {

    }
}

@Preview
@Composable
private fun LottoResultBackgroundPreview() {
    SattoTheme {
        LottoResultBackground(
            state = LottoResultUiState()
        )
    }
}
