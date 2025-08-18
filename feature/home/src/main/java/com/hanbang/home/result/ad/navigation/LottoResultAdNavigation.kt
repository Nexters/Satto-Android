package com.hanbang.home.result.ad.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.home.result.ad.LottoResultAdScreen
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultAd

fun NavController.navigateToLottoResultAd() {
    this.navigate(route = RouteLottoResultAd)
}

fun NavGraphBuilder.lottoResultAdNavGraph(
    navigateToLottoResult: () -> Unit
) {
    composable<RouteLottoResultAd> {
        LottoResultAdScreen(
            navigateToLottoResult = navigateToLottoResult
        )
    }
}