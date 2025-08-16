package com.hanbang.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.navigation.navigator.ActivityNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/17
 */
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

	@Inject lateinit var activityNavigator: ActivityNavigator

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			SattoTheme {
				Scaffold(
					content = { padding ->
						LoginRoute(
							paddingValues = padding,
							navigateToOnboarding = {
								activityNavigator.navigateToOnboarding(this@LoginActivity)
							}
						)
					}
				)
			}
		}
	}
}