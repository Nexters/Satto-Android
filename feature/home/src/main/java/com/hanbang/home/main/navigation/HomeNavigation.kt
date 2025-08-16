package com.hanbang.home.main.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.home.main.HomeRoute
import com.hanbang.navigation.feature.home.RouteHome

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
fun NavGraphBuilder.homeNavGraph(
	padding: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit
) {
	composable<RouteHome> {
		HomeRoute(
			paddingValues = padding,
			onShowErrorSnackBar = onShowErrorSnackBar
		)
	}
}