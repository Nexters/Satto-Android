package com.hanbang.home.recommend

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanbang.designsystem.R
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.chip.HbChip
import com.hanbang.designsystem.chip.HbChipStyles
import com.hanbang.designsystem.chip.model.HbChipColors
import com.hanbang.designsystem.lotto.LottoBall
import com.hanbang.designsystem.navigation.TopAppBar
import com.hanbang.designsystem.navigation.TopAppBarTitleType
import com.hanbang.designsystem.theme.Black
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Gray2
import com.hanbang.designsystem.theme.Gray3
import com.hanbang.designsystem.theme.Gray6
import com.hanbang.designsystem.theme.Gray7
import com.hanbang.designsystem.theme.Gray9
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.Primary7
import com.hanbang.designsystem.theme.Primary9
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import org.orbitmvi.orbit.compose.collectAsState

@Composable
internal fun LottoRecommendScreen(
    viewModel: LottoRecommendViewModel = hiltViewModel(),
    onClickNewNumber: () -> Unit,
    onClickCheckResult: () -> Unit,
    navigateToBack: () -> Unit
) {
    val state = viewModel.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary9)
            .systemBarsPadding()
    ) {
        when (state) {
            LottoRecommendUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center)
                )
            }

            is LottoRecommendUiState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    TopAppBar(
                        containerColor = Primary9,
                        contentColor = Gray1,
                        title = "${state.userName}님의 로또 번호",
                        titleType = TopAppBarTitleType.CENTER,
                        onNavigationClick = navigateToBack
                    )
                    LottoRecommendContent(
                        modifier = Modifier.weight(1f),
                        state = state
                    )
                }
                BottomBar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    type = state.ctaType,
                    onClickNewNumber = onClickNewNumber,
                    onClickCheckResult = onClickCheckResult
                )
            }
        }
    }
}

@Composable
private fun LottoRecommendContent(
    modifier: Modifier = Modifier,
    state: LottoRecommendUiState.Success
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 64.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        item {
            LottoRecommendCard(
                round = state.round,
                userName = state.userName,
                lottoNumbers = state.sortedLottoNumbers,
                remainTime = state.remainTime
            )
        }

        recommendContent(title = "AI 분석 결과, 이 번호가 뽑힌 까닭이오…") {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(color = White, shape = RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = Gray1)) {
                            append(
                                "${state.userName}님은 ${state.strongElement}기운이 강하여\n"
                            )
                        }
                        withStyle(SpanStyle(color = Primary2)) {
                            append(state.reason)
                        }
                        withStyle(SpanStyle(color = Gray1)) {
                            append("이오")
                        }
                    },
                    style = SattoTheme.typography.body14Bold,
                    lineHeight = TextUnit(1.5f, TextUnitType.Em)
                )
                Spacer(modifier = Modifier.height(16.dp))
                NumberRecommendItem(
                    description = "${state.strongElement} 기운과 잘 맞는 숫자",
                    number = state.lottoNumbers[0]
                )
                Spacer(modifier = Modifier.height(8.dp))
                NumberRecommendItem(
                    description = "재물운 좋을 때 잘 나오는 숫자",
                    number = state.lottoNumbers[1]
                )
                Spacer(modifier = Modifier.height(8.dp))
                NumberRecommendItem(
                    description = "최근 자주 나온 번호",
                    number = state.lottoNumbers[2]
                )
            }
        }

        recommendContent(title = "이 번호가 길한 까닭이오") {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(color = White, shape = RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                ReasonListItem(text = "요즘 많이 나오는 번호가 들어 있소")
                ReasonListItem(text = "연속 숫자 3개 이상 없이 안정적인 조합이오")
                ReasonListItem(text = "홀짝이 고르게 섞였소이다")
                ReasonListItem(text = "끝자리가 같은 수가 한 쌍 있소")
            }
        }

        recommendContent(title = "제외할 번호도 전해주겠소") {
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(color = White, shape = RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = "혹여 다른 번호를 고르려거든\n" +
                            "이 번호는 피하는 것이 좋을 것이오",
                    style = SattoTheme.typography.body14Bold,
                    color = Gray2
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    NotRecommendItem(
                        title = "${state.weakElement} 기운과\n상충하는 숫자",
                        numbers = state.weakNumbers
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    NotRecommendItem(
                        title = "최근 100회 동안\n거의 안 나온 숫자",
                        numbers = state.infrequentNumbers
                    )
                }
            }
        }
    }
}

@Composable
private fun LottoRecommendCard(
    round: Int,
    userName: String,
    lottoNumbers: List<Int?>,
    remainTime: Long
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White, shape = RoundedCornerShape(12.dp))
            .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HbChip(
            text = "${round}회",
            styles = HbChipStyles.square,
            colors = HbChipColors(
                containerColor = White,
                contentColor = Primary2,
                strokeColor = Primary7
            ),
            onClick = {}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${userName}을 위한 로또 번호 추천",
            style = SattoTheme.typography.body18Bold,
            color = Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            lottoNumbers.map {
                LottoBall(it)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(color = Gray6, thickness = 1.dp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "추첨까지 남은 시간",
                style = SattoTheme.typography.body14Medium,
                color = Gray3
            )
            Text(
                text = remainTime.toRemainTimeFormat(),
                style = SattoTheme.typography.body14Semibold,
                color = Gray1
            )
        }
    }
}

private fun LazyListScope.recommendContent(
    title: String,
    content: @Composable LazyItemScope.() -> Unit
) {
    item {
        ContentTitle(title)
        Spacer(modifier = Modifier.height(8.dp))
        content()
    }
}

@Composable
private fun LazyItemScope.ContentTitle(title: String) {
    Text(
        text = title,
        style = SattoTheme.typography.body14Bold,
        color = Gray2,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.fillParentMaxWidth()
    )
}

@Composable
private fun NumberRecommendItem(
    description: String,
    number: Pair<Int?, Int?>
) {
    Box(
        modifier = Modifier
            .background(color = White, shape = RoundedCornerShape(8.dp))
            .border(
                border = BorderStroke(width = 1.dp, color = Gray7),
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = description,
                color = Gray1,
                style = SattoTheme.typography.body14Semibold
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LottoBall(number.first)
                LottoBall(number.second)
            }
        }
    }
}

@Composable
private fun ReasonListItem(
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.icon_check_mark_20),
            contentDescription = null,
            tint = Primary2
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Gray3,
            style = SattoTheme.typography.body14Semibold
        )
    }
}

@Composable
private fun RowScope.NotRecommendItem(
    title: String,
    numbers: List<Int>
) {
    Column(
        modifier = Modifier
            .weight(1f, fill = true)
            .background(color = Gray9, shape = RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = SattoTheme.typography.body14Semibold,
            color = Gray1
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Horizontal
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = numbers,
                key = { it }
            ) {
                LottoBall(it)
            }
        }
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    type: LottoRecommendUiState.CtaType,
    onClickNewNumber: () -> Unit = {},
    onClickCheckResult: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Primary9)
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp)
    ) {
        when (type) {
            LottoRecommendUiState.CtaType.CREATE_NUMBER -> {
                HbBoxButton(
                    text = "번호 새로 받기",
                    onClick = onClickNewNumber,
                    colors = HbButtonColorType.border,
                    styles = HbButtonStyles.large,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            LottoRecommendUiState.CtaType.CHECK_RESULT -> {
                HbBoxButton(
                    text = "당첨 알림 받기",
                    onClick = onClickCheckResult,
                    colors = HbButtonColorType.primary,
                    styles = HbButtonStyles.large,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

private fun Long.toRemainTimeFormat(): String {
    val days = this / (24 * 60 * 60)
    val hours = (this % (24 * 60 * 60)) / (60 * 60)
    val minutes = (this % (60 * 60)) / 60
    val seconds = this % 60

    val parts = mutableListOf<String>().apply {
        if (days > 0) add("${days}일")
        if (hours > 0 || days > 0) add("${hours}시간")
        if (minutes > 0 || hours > 0 || days > 0) add("${minutes}분")
        add("${seconds}초")
    }

    return parts.joinToString(" ")
}

@Preview
@Composable
private fun LottoRecommendCardPreview() {
    SattoTheme {
        LottoRecommendCard(
            round = 1188,
            userName = "userName",
            lottoNumbers = listOf(1, 2, 18, 47, 10, 23),
            remainTime = 124_321
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LottoRecommendContentPreview() {
    SattoTheme {
        LottoRecommendContent(
            state = LottoRecommendUiState.Success(
                userName = "userName",
                round = 1000,
                lottoNumbers = listOf(Pair(1, 2), Pair(18, 45), Pair(37, 7)),
                remainTime = 124_321,
                strongElement = "화(火)",
                weakElement = "목(木)",
                reason = "reason",
                weakNumbers = listOf(4, 10, 28),
                infrequentNumbers = listOf(45, 12)
            )
        )
    }
}

@Preview
@Composable
private fun BottomBarPreview() {
    SattoTheme {
        BottomBar(
            type = LottoRecommendUiState.CtaType.CREATE_NUMBER
        )
    }
}