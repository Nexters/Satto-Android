package com.hanbang.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.navigation.Navigator
import com.hanbang.navigation.feature.fortune.RouteFortune
import com.hanbang.navigation.feature.history.RouteHistory
import com.hanbang.navigation.feature.home.RouteHome
import com.hanbang.navigation.feature.mypage.RouteMyPage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@HiltViewModel
class MainViewModel @Inject constructor(
	private val navigator: Navigator,
) : ViewModel() {

	fun navigateHome() = viewModelScope.launch {
		navigator.navigate(
			route = RouteHome,
			saveState = true,
			launchSingleTop = true,
		)
	}

	fun navigateHistory() = viewModelScope.launch {
		navigator.navigate(
			route = RouteHistory,
			saveState = true,
			launchSingleTop = true,
		)
	}

	fun navigateFortune() = viewModelScope.launch {
		navigator.navigate(
			route = RouteFortune,
			saveState = true,
			launchSingleTop = true,
		)
	}

	fun navigateMyPage() = viewModelScope.launch {
		navigator.navigate(
			route = RouteMyPage,
			saveState = true,
			launchSingleTop = true,
		)
	}
}