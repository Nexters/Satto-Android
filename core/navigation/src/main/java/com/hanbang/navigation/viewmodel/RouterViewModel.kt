package com.hanbang.navigation.viewmodel

import androidx.lifecycle.ViewModel
import com.hanbang.navigation.navigator.InternalNavigator
import com.hanbang.navigation.navigator.InternalRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@HiltViewModel
internal class RouterViewModel @Inject constructor(
	navigator: InternalNavigator,
) : ViewModel() {

	val sideEffect by lazy(LazyThreadSafetyMode.NONE) {
		navigator.channel.receiveAsFlow()
			.map { router ->
				when (router) {
					is InternalRoute.Navigate -> RouteSideEffect.Navigate(
						router.route,
						router.saveState,
						router.launchSingleTop,
					)

					is InternalRoute.NavigateWeb -> RouteSideEffect.NavigateWeb(router.url)

					is InternalRoute.NavigateBack -> RouteSideEffect.NavigateBack
				}
			}
	}
}