package com.hanbang.onboarding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.designsystem.toast.HbToastSnackbarHost
import com.hanbang.navigation.navigator.ActivityNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@AndroidEntryPoint
class OnboardingActivity : ComponentActivity() {

	@Inject lateinit var activityNavigator: ActivityNavigator

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()

		setContent {
			val context = LocalContext.current
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

			SattoTheme {
				Scaffold(
					modifier = Modifier.fillMaxSize(),
					snackbarHost = {
						HbToastSnackbarHost(
							hostState = snackBarHostState,
							snackBarType = snackBarType
						)
					},
					content = { innerPadding ->
						OnboardingRoute(
							paddingValues = innerPadding,
							onShowErrorSnackBar = onShowErrorSnackBar,
							navigateToHome = { activityNavigator.navigateToMain(context) },
							navigateUp = { finish() }
						)
					}
				)
			}
		}
	}
}