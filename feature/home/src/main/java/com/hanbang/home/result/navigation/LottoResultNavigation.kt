package com.hanbang.home.result.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.home.result.LottoResultScreen
import com.hanbang.navigation.feature.lottoresult.RouteLottoResult
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultAd
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultModel
import com.hanbang.navigation.feature.lottoresult.toJson

fun NavController.navigateToLottoResult(model: RouteLottoResultModel) {
    this.navigate(route = RouteLottoResult(model.toJson())) {
        popUpTo<RouteLottoResultAd> { inclusive = true }
        launchSingleTop = true
        restoreState = false
    }
}

fun NavGraphBuilder.lottoResultNavGraph(
    onClickBack: () -> Unit,
    onClickHome: () -> Unit,
) {
    composable<RouteLottoResult> {
        LottoResultScreen(
            onClickBack = onClickBack,
            onClickHome = onClickHome
        )
    }
}