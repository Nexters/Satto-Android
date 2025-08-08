package com.hanbang.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.hanbang.designsystem.R
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.designsystem.toast.HbToastSnackbarHost
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
	var snackBarType by remember { mutableStateOf<HbSnackBarType>(HbSnackBarType.NOTICE()) }

	val coroutineScope = rememberCoroutineScope()
	val onShowErrorSnackBar: (HbSnackBarType) -> Unit = { type ->
		snackBarType = type
		coroutineScope.launch {
			snackBarHostState.currentSnackbarData?.dismiss()
			snackBarHostState.showSnackbar(snackBarType.message)
		}
	}

	MainScreenContent(
		onTabSelected = onTabSelected,
		navigator = navigator,
		onShowErrorSnackBar = onShowErrorSnackBar,
		snackBarHostState = snackBarHostState,
		snackBarType = snackBarType
	)
}

@Composable
private fun MainScreenContent(
	navigator: MainNavigator,
	onTabSelected: (MainTab) -> Unit,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
	snackBarHostState: SnackbarHostState,
	modifier: Modifier = Modifier,
	snackBarType: HbSnackBarType = HbSnackBarType.NORMAL
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
		snackbarHost = {
			HbToastSnackbarHost(
				hostState = snackBarHostState,
				snackBarType = snackBarType
			) }
	)
}