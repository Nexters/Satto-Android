package com.hanbang.navigation

import com.hanbang.navigation.model.Route

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
interface Navigator {

	suspend fun navigate(route: Route, saveState: Boolean = false, launchSingleTop: Boolean = false)

	suspend fun navigateWeb(url: String)

	suspend fun navigateBack()
}