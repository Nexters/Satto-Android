package com.hanbang.home.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.hanbang.designsystem.R
import com.hanbang.designsystem.chip.HbChip
import com.hanbang.designsystem.chip.model.HbChipColors
import com.hanbang.designsystem.chip.model.HbChipStyleProperties
import com.hanbang.designsystem.lotto.LottoBall
import com.hanbang.designsystem.theme.Black
import com.hanbang.designsystem.theme.Gray3
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Gray8
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.Primary7
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.toast.HbSnackBarType
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onShowErrorSnackBar: (HbSnackBarType) -> Unit,
    onClickRecommend: () -> Unit,
    onClickViewMore: () -> Unit,
    onClickCheckResult: () -> Unit
) {
    val uiState = viewModel.collectAsState().value

    viewModel.collectSideEffect {

    }

    HomeScreen {
        when {
            uiState.isLoading -> LoadingProgress()
            else -> {
                HomeContent(
                    padding = paddingValues,
                    data = uiState.content,
                    onClickRecommend = onClickRecommend,
                    onClickViewMore = onClickViewMore,
                    onClickCheckResult = onClickCheckResult
                )
            }
        }
    }
}

@Composable
private fun HomeScreen(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFECE2FF),
                        Color(0xFFF4F3F5)
                    )
                )
            )
    ) {
        Image(
            painter = painterResource(R.drawable.img_home_cloud_left),
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Image(
            painter = painterResource(R.drawable.img_home_cloud_right),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 110.dp)
        )
        content()
    }
}

@Composable
private fun HomeContent(
    padding: PaddingValues = PaddingValues(),
    data: HomeUiState.Content,
    onClickRecommend: () -> Unit,
    onClickViewMore: () -> Unit,
    onClickCheckResult: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(bottom = padding.calculateBottomPadding()),
        contentPadding = PaddingValues(start = 20.dp, top = 28.dp + padding.calculateTopPadding(), end = 20.dp, bottom = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            RoundNumberChip(data.round)
        }
        item {
            HomeTitle(data.title)
        }
        item {
            HomeImage()
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
            LottoRecommendCardItem(
                date = data.date,
                userName = data.userName,
                numbers = data.lottoNumbers,
                openType = data.openType,
                onClickRecommend = onClickRecommend,
                onClickViewMore = onClickViewMore,
                onClickCheckResult = onClickCheckResult
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        todayFortuneList(
            categories = data.fortuneCategories
        )
    }
}

@Composable
private fun RoundNumberChip(
    round: Int
) {
    HbChip(
        text = "${round}회",
        colors = HbChipColors(
            containerColor = Primary7,
            contentColor = Primary2
        ),
        styles = HbChipStyleProperties(
            height = 28.dp,
            shape = CircleShape,
            contentPadding = PaddingValues(horizontal = 10.dp),
            textStyle = SattoTheme.typography.body14Semibold,
            spacing = 0.dp
        ),
        onClick = {}
    )
}

@Composable
private fun HomeTitle(
    title: String
) {
    Text(
        text = title,
        style = SattoTheme.typography.headline24Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, bottom = 28.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun HomeImage() {
    AsyncImage(
        model = R.drawable.img_satto_tiger,
        contentDescription = null,
        modifier = Modifier.size(120.dp)
    )
}

@Composable
private fun LottoRecommendCardItem(
    date: LocalDate,
    userName: String,
    numbers: List<Int?>,
    openType: HomeUiState.Content.OpenType,
    onClickRecommend: () -> Unit = {},
    onClickViewMore: () -> Unit = {},
    onClickCheckResult: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(color = White, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${date.year}년 ${date.monthValue.toString().padStart(2, '0')}월 ${date.dayOfMonth.toString().padStart(2, '0')}일 기준",
                style = SattoTheme.typography.caption12Medium,
                color = Gray3
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${userName}을 위한 로또 번호 추천",
                style = SattoTheme.typography.body18Bold,
                color = Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                numbers.forEach { number ->
                    LottoBall(number)
                }
            }
            when (openType) {
                HomeUiState.Content.OpenType.RECOMMEND -> {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "번호를 받아보십시오",
                        style = SattoTheme.typography.body14Regular,
                        color = Gray5
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 24.dp)
                            .background(color = Primary2, shape = RoundedCornerShape(8.dp))
                            .padding(vertical = 12.dp)
                            .clickable { onClickRecommend() }
                    ) {
                        Text(
                            text = "번호 추천받기",
                            style = SattoTheme.typography.body16Bold,
                            color = White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                HomeUiState.Content.OpenType.MORE -> {
                    Spacer(modifier = Modifier.height(24.dp))
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(color = Gray8)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .clip(RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
                            .clickable { onClickViewMore() }
                            .padding(vertical = 12.dp)
                    ) {
                        Text(
                            text = "자세히 보기",
                            style = SattoTheme.typography.caption12Regular,
                            color = Gray3,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }

                HomeUiState.Content.OpenType.OPENED -> {
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 24.dp)
                            .background(color = Primary2, shape = RoundedCornerShape(8.dp))
                            .padding(vertical = 12.dp)
                            .clickable { onClickCheckResult() }
                    ) {
                        Text(
                            text = "결과 확인하기",
                            style = SattoTheme.typography.body16Bold,
                            color = White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                HomeUiState.Content.OpenType.FALLBACK -> Unit
            }
        }
    }
}

private fun LazyListScope.todayFortuneList(
    categories: List<HomeUiState.FortuneCategory>
) {
    item {
        TodayFortuneHeader()
    }
    items(
        items = categories.chunked(2),
        key = { chunk -> chunk.joinToString("-") { it.id.toString() } }
    ) {
        TodayFortuneRow(it)
    }
    item {
        TodayFortuneFooter()
    }
}

@Composable
private fun LazyItemScope.TodayFortuneHeader() {
    Box(
        modifier = Modifier
            .fillParentMaxWidth()
            .background(color = White, shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            .padding(top = 24.dp, bottom = 20.dp)
    ) {
        Text(
            text = "🍀 오늘의 운세 🍀",
            style = SattoTheme.typography.body18Bold,
            color = Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun LazyItemScope.TodayFortuneRow(
    categoryPair: List<HomeUiState.FortuneCategory>
) {
    Row(
        modifier = Modifier
            .fillParentMaxWidth()
            .background(color = White)
            .padding(all = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            FortuneCategoryItem(category = categoryPair[0])
        }
        if (categoryPair.size > 1) {
            Box(modifier = Modifier.weight(1f)) {
                FortuneCategoryItem(category = categoryPair[1])
            }
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun FortuneCategoryItem(
    category: HomeUiState.FortuneCategory
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HbChip(
            text = category.label,
            colors = HbChipColors(
                contentColor = Primary2,
                containerColor = White,
                strokeColor = Primary2
            ),
            styles = HbChipStyleProperties(
                shape = RoundedCornerShape(6.dp),
                height = 28.dp,
                contentPadding = PaddingValues(horizontal = 10.dp),
                textStyle = SattoTheme.typography.body14Semibold,
                spacing = 0.dp
            ),
            onClick = {}
        )

        AsyncImage(
            model = category.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
                .height(106.dp)
                .background(color = Color(0xFFF6F7F9), shape = RoundedCornerShape(8.dp))
        )

        Text(
            text = category.description,
            style = SattoTheme.typography.body14Bold,
            color = Color.Black
        )
    }
}

@Composable
private fun LazyItemScope.TodayFortuneFooter() {
    Box(
        modifier = Modifier
            .fillParentMaxWidth()
            .height(20.dp)
            .background(color = White, shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
    )
}

@Composable
private fun LoadingProgress() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    SattoTheme {
        HomeScreen {
            HomeContent(
                data = HomeUiState.Content(
                    round = 1185,
                    title = "title",
                    date = LocalDate.now(),
                    userName = "user name",
                    lottoNumbers = List(6) { null },
                    fortuneCategories = listOf(
                        HomeUiState.FortuneCategory(
                            id = 1,
                            label = "label",
                            imageUrl = "",
                            description = "description"
                        )
                    ),
                    openType = HomeUiState.Content.OpenType.RECOMMEND
                ),
                onClickRecommend = {},
                onClickViewMore = {},
                onClickCheckResult = {}
            )
        }
    }
}