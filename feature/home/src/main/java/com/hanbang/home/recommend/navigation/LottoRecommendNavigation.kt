package com.hanbang.home.recommend.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.home.recommend.LottoRecommendScreen
import com.hanbang.navigation.feature.lottorecommend.RouteLottoRecommend

fun NavController.navigateToLottoRecommend() {
    this.navigate(route = RouteLottoRecommend)
}

fun NavGraphBuilder.lottoRecommendNavGraph() {
    composable<RouteLottoRecommend> {
        LottoRecommendScreen()
    }
}