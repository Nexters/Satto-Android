package com.hanbang.satto.navigator

import android.content.Context
import android.content.Intent
import com.hanbang.login.LoginActivity
import com.hanbang.main.MainActivity
import com.hanbang.navigation.navigator.ActivityNavigator
import com.hanbang.navigation.navigator.ActivityNavigator.Companion.ROUTE_KEY
import com.hanbang.onboarding.OnboardingActivity
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
class ActivityNavigatorImpl @Inject constructor() : ActivityNavigator {

	override fun navigateToMain(context: Context, routeName: String) {
		val intent = Intent(context, MainActivity::class.java).apply {
			flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
			putExtra(ROUTE_KEY, routeName)
		}

		context.startActivity(intent)
	}

	override fun navigateToLogin(context: Context) {
		val intent = Intent(context, LoginActivity::class.java)
		context.startActivity(intent)
	}

	override fun navigateToOnboarding(context: Context) {
		val intent = Intent(context, OnboardingActivity::class.java)
		context.startActivity(intent)
	}
}