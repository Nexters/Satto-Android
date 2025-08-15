package com.hanbang.data.repository

import com.hanbang.data.datasource.DeviceLocalDataSource
import com.hanbang.domain.repository.DeviceRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
class DefaultDeviceRepository @Inject constructor(
	private val deviceLocalDataSource: DeviceLocalDataSource
) : DeviceRepository {

	override suspend fun storeDeviceId(deviceId: String): String {
		return deviceLocalDataSource.storeDeviceId(deviceId)
	}

	override suspend fun getDeviceId(): String {
		return deviceLocalDataSource.getDeviceId()
	}
}