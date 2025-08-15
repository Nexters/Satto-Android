package com.hanbang.domain.repository

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
interface DeviceRepository {

	suspend fun storeDeviceId(deviceId: String): String

	suspend fun getDeviceId(): String
}