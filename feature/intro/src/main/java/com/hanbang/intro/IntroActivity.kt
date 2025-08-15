package com.hanbang.intro

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.hanbang.navigation.navigator.ActivityNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@AndroidEntryPoint
class IntroActivity : ComponentActivity() {

	@Inject lateinit var activityNavigator: ActivityNavigator

	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen().setOnExitAnimationListener { splashScreenView ->
			val animation = ObjectAnimator.ofFloat(
				splashScreenView.view,
				"alpha",
				1f,
				0f
			)
			animation.interpolator = AnticipateInterpolator()
			val animationDuration = 1000L
			animation.duration = animationDuration

			animation.doOnEnd { splashScreenView.remove() }
			animation.start()
		}

		enableEdgeToEdge()
		super.onCreate(savedInstanceState)

		setContent {
			val context = LocalContext.current

			LaunchedEffect(Unit) {
				delay(SPLASH_TIME)
				this.launch(Dispatchers.Main) {
					activityNavigator.navigateToHome(context)
				}
			}

			SattoSplashScreen()
		}
	}

	companion object {
		private const val SPLASH_TIME = 3000L
	}
}