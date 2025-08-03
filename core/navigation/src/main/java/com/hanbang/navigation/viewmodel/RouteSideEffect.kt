package com.hanbang.navigation.viewmodel

import com.hanbang.navigation.model.Route

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */

internal sealed interface RouteSideEffect {

	data class Navigate(
		val route: Route,
		val saveState: Boolean,
		val launchSingleTop: Boolean,
	) : RouteSideEffect

	data class NavigateWeb(val url: String) : RouteSideEffect

	data object NavigateBack : RouteSideEffect
}