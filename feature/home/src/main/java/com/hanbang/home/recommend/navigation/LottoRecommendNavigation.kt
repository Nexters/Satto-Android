package com.hanbang.home.recommend.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.home.recommend.LottoRecommendScreen
import com.hanbang.navigation.feature.lottorecommend.RouteLottoRecommend
import com.hanbang.navigation.feature.lottorecommend.RouteLottoRecommendAd

fun NavController.navigateToLottoRecommendWithPopUp() {
    this.navigate(route = RouteLottoRecommend) {
        popUpTo<RouteLottoRecommendAd> { inclusive = true }
        launchSingleTop = true
        restoreState = false
    }
}

fun NavGraphBuilder.lottoRecommendNavGraph(
    onClickNewNumber: () -> Unit,
    onClickCheckResult: () -> Unit,
    navigateToBack: () -> Unit
) {
    composable<RouteLottoRecommend> {
        LottoRecommendScreen(
            onClickNewNumber = onClickNewNumber,
            onClickCheckResult = onClickCheckResult,
            navigateToBack = navigateToBack
        )
    }
}