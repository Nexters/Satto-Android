package com.hanbang.satto

import android.app.Application
import com.hanbang.domain.manager.DeviceIdManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@HiltAndroidApp
class SattoApplication : Application() {

	@Inject lateinit var deviceIdManager: DeviceIdManager

	override fun onCreate() {
		super.onCreate()

		initData()
	}

	private fun initData() {
		deviceIdManager.storeDeviceId()
	}
}