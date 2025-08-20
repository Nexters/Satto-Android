package com.hanbang.home.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanbang.designsystem.R
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.chip.HbChip
import com.hanbang.designsystem.chip.HbChipColorType
import com.hanbang.designsystem.chip.HbChipStyles
import com.hanbang.designsystem.lotto.LottoBall
import com.hanbang.designsystem.navigation.TopAppBar
import com.hanbang.designsystem.navigation.TopAppBarTitleType
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Gray2
import com.hanbang.designsystem.theme.Gray3
import com.hanbang.designsystem.theme.Gray7
import com.hanbang.designsystem.theme.Gray8
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.Primary7
import com.hanbang.designsystem.theme.Primary8
import com.hanbang.designsystem.theme.Primary9
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import org.orbitmvi.orbit.compose.collectAsState
import java.util.Locale

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
                .weight(1f, fill = true),
            state = state
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
    scrollState: ScrollState = rememberScrollState(),
    state: LottoResultUiState
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        HbChip(
            text = "${state.round}회",
            colors = HbChipColorType.TintedPrimary.copy(containerColor = Primary7, contentColor = Primary2),
            styles = HbChipStyles.square,
            modifier = Modifier.padding(2.dp),
            onClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = if (state.isRanked) {
                "${state.rank}등 당첨!"
            } else {
                "도전 실패!"
            },
            style = SattoTheme.typography.headline24Bold,
            color = Primary2
        )
        Text(
            text = if (state.isRanked) {
                String.format(Locale.getDefault(), "%,d", state.prizeAmount) + "원"
            } else {
                "다음 기회에..."
            },
            style = SattoTheme.typography.body16Semibold,
            color = Gray2
        )
        Spacer(modifier = Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .background(color = White, shape = CircleShape)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.resultNumbers.map {
                LottoBall(it)
            }
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_plus),
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = Gray3
            )
            LottoBall(state.bonusNumber)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .background(color = White, shape = CircleShape)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RankBall(state.rank.takeIf { state.isRanked })
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.icon_vertical_divider),
                contentDescription = null,
                tint = Gray7,
                modifier = Modifier.size(14.dp)
            )
            state.recommendedNumbers.map { (number, isMatched) ->
                if (isMatched) {
                    LottoBall(number)
                } else {
                    NotMatchedBall(number)
                }
            }
        }
        Spacer(modifier = Modifier.height(80.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .background(color = Primary8, shape = RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Text(
                text = "사또의 한마디...",
                style = SattoTheme.typography.body16Bold,
                color = Gray2
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_satto_result),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.width(7.dp))
                Box(
                    modifier = Modifier
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_result_bubble),
                        contentDescription = null
                    )
                    Text(
                        text = if (state.isRanked) {
                            "축하드리네!\n이번 행운의 주인공은 그대라네"
                        } else {
                            "아쉽지만 오늘은 여기까지.\n다음 절호의 기회는 꼭 잡아보세."
                        },
                        style = SattoTheme.typography.body14Bold,
                        color = Gray2,
                        modifier = Modifier.align(Alignment.CenterStart).padding(start = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun RankBall(rank: Int?) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(color = Primary2, shape = CircleShape)
    ) {
        Text(
            text = if (rank != null) {
                "${rank}등"
            } else {
                "꽝"
            },
            style = SattoTheme.typography.body14Bold,
            color = White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun NotMatchedBall(number: Int) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(color = Gray8, shape = CircleShape)
    ) {
        Text(
            text = number.toString(),
            style = SattoTheme.typography.body14Bold,
            color = White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun LottoResultBackgroundPreview() {
    SattoTheme {
        LottoResultBackground(
            state = LottoResultUiState(
                round = 1186,
                rank = 60,
                prizeAmount = 123123123123L,
                resultNumbers = listOf(1, 24, 3, 41, 5, 7),
                recommendedNumbers = mapOf(
                    1 to true,
                    10 to false,
                    20 to false,
                    3 to true,
                    4 to true,
                    7 to true
                ),
                bonusNumber = 6
            )
        )
    }
}
