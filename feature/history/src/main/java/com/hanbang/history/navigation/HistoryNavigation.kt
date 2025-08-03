package com.hanbang.history.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.history.HistoryRoute
import com.hanbang.navigation.feature.history.RouteHistory

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
fun NavGraphBuilder.historyNavGraph(
	padding: PaddingValues,
	onShowErrorSnackBar: (throwable: Throwable?) -> Unit
) {
	composable<RouteHistory> {
		HistoryRoute(
			paddingValues = padding,
			onShowErrorSnackBar = onShowErrorSnackBar
		)
	}
}