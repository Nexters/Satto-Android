package com.hanbang.home.result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.home.result.LottoResultScreen
import com.hanbang.navigation.feature.lottoresult.RouteLottoResult
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultAd

fun NavController.navigateToLottoResult() {
    this.navigate(route = RouteLottoResult) {
        popUpTo<RouteLottoResultAd> { inclusive = true }
        launchSingleTop = true
        restoreState = false
    }
}

fun NavGraphBuilder.lottoResultNavGraph() {
    composable<RouteLottoResult> {
        LottoResultScreen()
    }
}