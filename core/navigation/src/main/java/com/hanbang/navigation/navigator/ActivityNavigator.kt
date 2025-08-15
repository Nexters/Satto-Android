package com.hanbang.navigation.navigator

import android.content.Context
import com.hanbang.navigation.feature.home.RouteHome

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
interface ActivityNavigator {

	fun navigateToMain(context: Context, routeName: String = RouteHome.toString())

	fun navigateToOnboarding(context: Context)

	companion object {
		const val ROUTE_KEY  = "ROUTE_KEY"
	}
}