package com.hanbang.main

import androidx.compose.runtime.Composable
import com.hanbang.designsystem.R
import com.hanbang.navigation.feature.fortune.RouteFortune
import com.hanbang.navigation.feature.history.RouteHistory
import com.hanbang.navigation.feature.home.RouteHome
import com.hanbang.navigation.feature.mypage.RouteMyPage
import com.hanbang.navigation.model.Route

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
internal enum class MainTab(
	val iconResId: Int,
	internal val contentDescription: String,
	val route: Route,
) {
	HOME(
		iconResId = R.drawable.ic_home,
		contentDescription = "홈",
		route = RouteHome,
	),
	FORTUNE(
		iconResId = R.drawable.ic_clover,
		contentDescription = "운세",
		route = RouteFortune,
	),
	HISTORY(
		iconResId = R.drawable.ic_receipt,
		contentDescription = "지난 로또",
		route = RouteHistory,
	),
	MY_PAGE(
		iconResId = R.drawable.ic_user,
		contentDescription = "마이",
		route = RouteMyPage,
	);

	companion object {
		@Composable
		fun find(predicate: @Composable (Route) -> Boolean): MainTab? {
			return entries.find { predicate(it.route) }
		}

		@Composable
		fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
			return entries.map { it.route }.any { predicate(it) }
		}
	}
}