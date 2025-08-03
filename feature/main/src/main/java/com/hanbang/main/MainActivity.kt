package com.hanbang.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.navigation.LaunchedRouter
import com.hanbang.navigation.feature.fortune.RouteFortune
import com.hanbang.navigation.feature.history.RouteHistory
import com.hanbang.navigation.feature.home.RouteHome
import com.hanbang.navigation.feature.mypage.RouteMyPage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private val viewModel: MainViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			val navigator: MainNavigator = rememberMainNavigator()
			LaunchedRouter(navigator.navController)

			SattoTheme {
				Scaffold(
					modifier = Modifier.fillMaxSize(),
					content = { padding ->
						MainScreen(
							navigator = navigator,
							onTabSelected = {
								when (it.route) {
									is RouteHome -> { viewModel.navigateHome() }
									is RouteFortune -> { viewModel.navigateFortune() }
									is RouteHistory -> { viewModel.navigateHistory() }
									is RouteMyPage -> { viewModel.navigateMyPage() }
								}
							},
						)
					}
				)
			}
		}
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	Greeting("Android")
}