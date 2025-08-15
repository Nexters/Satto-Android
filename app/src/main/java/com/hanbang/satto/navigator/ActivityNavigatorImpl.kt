package com.hanbang.satto.navigator

import android.content.Context
import android.content.Intent
import com.hanbang.main.MainActivity
import com.hanbang.navigation.navigator.ActivityNavigator
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
class ActivityNavigatorImpl @Inject constructor() : ActivityNavigator {

	override fun navigateToHome(context: Context) {
		val intent = Intent(context, MainActivity::class.java)
		context.startActivity(intent)
	}
}