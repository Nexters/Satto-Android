package com.hanbang.fortune.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.fortune.FortuneRoute
import com.hanbang.navigation.feature.fortune.RouteFortune

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
fun NavGraphBuilder.fortuneNavGraph(
	padding: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit
) {
	composable<RouteFortune> {
		FortuneRoute(
			paddingValues = padding,
			onShowErrorSnackBar = onShowErrorSnackBar
		)
	}
}