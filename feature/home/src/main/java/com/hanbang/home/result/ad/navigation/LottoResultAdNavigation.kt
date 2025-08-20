package com.hanbang.home.result.ad.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.home.result.ad.LottoResultAdScreen
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultAd
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultModel

fun NavController.navigateToLottoResultAd(round: Int) {
    this.navigate(route = RouteLottoResultAd(round))
}

fun NavGraphBuilder.lottoResultAdNavGraph(
    navigateToLottoResult: (RouteLottoResultModel) -> Unit,
    navigateToBack: () -> Unit
) {
    composable<RouteLottoResultAd> {
        LottoResultAdScreen(
            navigateToLottoResult = navigateToLottoResult,
            navigateToBack = navigateToBack
        )
    }
}