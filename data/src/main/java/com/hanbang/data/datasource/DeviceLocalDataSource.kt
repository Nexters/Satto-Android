package com.hanbang.data.datasource

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
interface DeviceLocalDataSource {

	suspend fun storeDeviceId(deviceId: String): String

	suspend fun getDeviceId(): String
}