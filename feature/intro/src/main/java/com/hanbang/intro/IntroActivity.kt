package com.hanbang.intro

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AnticipateInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hanbang.intro.model.NextScreenType
import com.hanbang.navigation.navigator.ActivityNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
	private val viewModel by viewModels<IntroViewModel>()

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

		viewModel.checkNextScreen()
		observeData()

		setContent {
			SattoSplashScreen()
		}
	}

	private fun observeData() {
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.nextScreenFlow.collectLatest { type: NextScreenType ->
					when (type) {
						NextScreenType.LOGIN -> {
							activityNavigator.navigateToLogin(this@IntroActivity)
							finish()
						}
						NextScreenType.HOME -> {
							activityNavigator.navigateToMain(this@IntroActivity)
							finish()
						}
						else -> {}
					}
				}
			}
		}
	}
}