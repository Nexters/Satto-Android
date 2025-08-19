package com.hanbang.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.editprofile.navigation.editProfileNavGraph
import com.hanbang.editprofile.navigation.navigateToEditProfile
import com.hanbang.fortune.navigation.fortuneNavGraph
import com.hanbang.history.navigation.historyNavGraph
import com.hanbang.home.lottoad.navigation.lottoAdNavGraph
import com.hanbang.home.lottoad.navigation.navigateToLottoAd
import com.hanbang.home.main.navigation.homeNavGraph
import com.hanbang.home.recommend.navigation.lottoRecommendNavGraph
import com.hanbang.home.recommend.navigation.navigateToLottoRecommendWithPopUp
import com.hanbang.home.result.ad.navigation.lottoResultAdNavGraph
import com.hanbang.home.result.ad.navigation.navigateToLottoResultAd
import com.hanbang.home.result.navigation.lottoResultNavGraph
import com.hanbang.home.result.navigation.navigateToLottoResult
import com.hanbang.main.MainNavigator
import com.hanbang.mypage.navigation.myPageNavGraph

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
internal fun MainNavHost(
	navigator: MainNavigator,
	padding: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
	modifier: Modifier = Modifier,
) {
	Box(
		modifier = modifier
			.fillMaxSize()
	) {
		NavHost(
			navController = navigator.navController,
			startDestination = navigator.startDestination,
		) {
			homeNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
				onClickRecommend = navigator.navController::navigateToLottoAd,
				onClickViewMore = navigator.navController::navigateToLottoRecommendWithPopUp,
				onClickCheckResult = navigator.navController::navigateToLottoResultAd
			)
			fortuneNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
			)
			historyNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
			)
			myPageNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
				navigateToEditProfile = {
					navigator.navController.navigateToEditProfile(it)
				}
			)

			editProfileNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
				onNavigateUp = navigator.navController::navigateUp
			)

			lottoRecommendNavGraph(
				onClickNewNumber = navigator.navController::navigateToLottoAd,
				onClickCheckResult = navigator.navController::navigateToLottoResultAd
			)

			lottoAdNavGraph(
				navigateToLottoRecommend = navigator.navController::navigateToLottoRecommendWithPopUp
			)

			lottoResultAdNavGraph(
				navigateToLottoResult = navigator.navController::navigateToLottoResult
			)

			lottoResultNavGraph()
		}
	}
}