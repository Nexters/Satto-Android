package com.hanbang.navigation.navigator

import com.hanbang.navigation.model.Route

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
internal sealed interface InternalRoute {

	data class Navigate(
		val route: Route,
		val saveState: Boolean,
		val launchSingleTop: Boolean,
	) : InternalRoute

	data class NavigateWeb(val url: String) : InternalRoute

	data object NavigateBack : InternalRoute
}