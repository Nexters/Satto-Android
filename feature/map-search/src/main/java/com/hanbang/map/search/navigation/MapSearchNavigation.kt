package com.hanbang.map.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.map.search.MapSearchRoute
import com.hanbang.navigation.feature.mapsearch.RouteMapSearch

/**
 * @author   JGeun
 * @created  2026/01/04
 */
fun NavGraphBuilder.mapSearchNavGraph(
	padding: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit
) {
	composable<RouteMapSearch> {
		MapSearchRoute(
			paddingValues = padding,
			onShowErrorSnackBar = onShowErrorSnackBar
		)
	}
}