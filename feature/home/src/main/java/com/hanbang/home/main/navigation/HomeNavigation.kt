package com.hanbang.home.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.home.main.HomeRoute
import com.hanbang.navigation.feature.home.RouteHome

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
fun NavController.navigateToHome(navOptions: NavOptions) {
	this.navigate(route = RouteHome, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
	padding: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
	onClickRecommend: () -> Unit,
	onClickViewMore: () -> Unit,
	onClickCheckResult: (Int) -> Unit
) {
	composable<RouteHome> {
		HomeRoute(
			paddingValues = padding,
			onShowErrorSnackBar = onShowErrorSnackBar,
			onClickRecommend = onClickRecommend,
			onClickViewMore = onClickViewMore,
			onClickCheckResult = onClickCheckResult
		)
	}
}