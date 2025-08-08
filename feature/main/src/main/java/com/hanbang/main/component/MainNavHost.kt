package com.hanbang.main.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.editprofile.navigation.editProfileNavGraph
import com.hanbang.editprofile.navigation.navigateToEditProfile
import com.hanbang.fortune.navigation.fortuneNavGraph
import com.hanbang.history.navigation.historyNavGraph
import com.hanbang.home.navigation.homeNavGraph
import com.hanbang.main.MainNavigator
import com.hanbang.mypage.navigation.myPageNavGraph

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
internal fun MainNavHost(
	navigator: MainNavigator,
	padding: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
	modifier: Modifier = Modifier,
) {
	Box(
		modifier = modifier
			.fillMaxSize()
	) {
		NavHost(
			navController = navigator.navController,
			startDestination = navigator.startDestination,
		) {
			homeNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
			)
			fortuneNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
			)
			historyNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
			)
			myPageNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
				navigateToEditProfile = {
					navigator.navController.navigateToEditProfile(it)
				}
			)

			editProfileNavGraph(
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
				onNavigateUp = navigator.navController::navigateUp
			)
		}
	}
}