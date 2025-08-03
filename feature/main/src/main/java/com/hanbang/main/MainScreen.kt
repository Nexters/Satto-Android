package com.hanbang.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.hanbang.designsystem.R
import com.hanbang.main.component.MainBottomBar
import com.hanbang.main.component.MainNavHost
import kotlinx.coroutines.launch
import java.net.UnknownHostException

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
internal fun MainScreen(
	onTabSelected: (MainTab) -> Unit,
	navigator: MainNavigator,
) {
	val snackBarHostState = remember { SnackbarHostState() }

	val coroutineScope = rememberCoroutineScope()
	val localContextResource = LocalContext.current.resources
	val onShowErrorSnackBar: (throwable: Throwable?) -> Unit = { throwable ->
		coroutineScope.launch {
			snackBarHostState.showSnackbar(
				when (throwable) {
					is UnknownHostException -> localContextResource.getString(R.string.error_message_network)
					else -> localContextResource.getString(R.string.error_message_unknown)
				}
			)
		}
	}

	MainScreenContent(
		onTabSelected = onTabSelected,
		navigator = navigator,
		onShowErrorSnackBar = onShowErrorSnackBar,
		snackBarHostState = snackBarHostState
	)
}

@Composable
private fun MainScreenContent(
	navigator: MainNavigator,
	onTabSelected: (MainTab) -> Unit,
	onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
	snackBarHostState: SnackbarHostState,
	modifier: Modifier = Modifier,
) {
	Scaffold(
		modifier = modifier,
		content = { padding ->
			MainNavHost(
				navigator = navigator,
				padding = padding,
				onShowErrorSnackBar = onShowErrorSnackBar,
			)
		},
		bottomBar = {
			MainBottomBar(
				modifier = Modifier
					.navigationBarsPadding(),
				visible = navigator.shouldShowBottomBar(),
				tabs = MainTab.entries,
				currentTab = navigator.currentTab,
				onTabSelected = onTabSelected,
			)
		},
		snackbarHost = { SnackbarHost(snackBarHostState) }
	)
}