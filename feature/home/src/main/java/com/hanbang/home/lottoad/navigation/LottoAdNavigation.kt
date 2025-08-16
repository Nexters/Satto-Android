package com.hanbang.home.lottoad.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.home.lottoad.LottoAdScreen
import com.hanbang.navigation.feature.lottorecommend.RouteLottoRecommendAd

fun NavController.navigateToLottoAd() {
    this.navigate(route = RouteLottoRecommendAd)
}

fun NavGraphBuilder.lottoAdNavGraph(
    navigateToLottoRecommend: () -> Unit
) {
    composable<RouteLottoRecommendAd> {
        LottoAdScreen(
            navigateToLottoRecommend = navigateToLottoRecommend
        )
    }
}