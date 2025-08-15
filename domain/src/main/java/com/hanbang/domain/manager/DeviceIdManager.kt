package com.hanbang.domain.manager

import com.hanbang.domain.usecase.GetDeviceIdUseCase
import com.hanbang.domain.usecase.StoreDeviceIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Singleton
class DeviceIdManager @Inject constructor(
	private val getDeviceIdUseCase: GetDeviceIdUseCase,
	private val storeDeviceIdUseCase: StoreDeviceIdUseCase
){
	private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	fun storeDeviceId() {
		scope.launch {
			val deviceId = getDeviceIdUseCase()
			if (deviceId.isNotEmpty()) return@launch

			val uniqueID = UUID.randomUUID().toString()
			storeDeviceIdUseCase(uniqueID)
		}
	}
}