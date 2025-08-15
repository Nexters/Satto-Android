package com.hanbang.domain.usecase

import com.hanbang.domain.repository.DeviceRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
class StoreDeviceIdUseCase @Inject constructor(
	private val deviceRepository: DeviceRepository
) {

	suspend operator fun invoke(deviceId: String): String {
		return deviceRepository.storeDeviceId(deviceId)
	}
}