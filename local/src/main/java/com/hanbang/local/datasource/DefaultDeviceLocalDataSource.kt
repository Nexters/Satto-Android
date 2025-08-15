package com.hanbang.local.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hanbang.data.datasource.DeviceLocalDataSource
import com.hanbang.local.di.qualifier.DeviceDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
class DefaultDeviceLocalDataSource @Inject constructor(
	@DeviceDataStore private val deviceDataStore: DataStore<Preferences>
) : DeviceLocalDataSource {

	override suspend fun storeDeviceId(deviceId: String): String {
		val preferences = deviceDataStore.edit {
			it[PreferencesKey.deviceIdKey] = deviceId
		}

		return preferences[PreferencesKey.deviceIdKey].orEmpty()
	}

	override suspend fun getDeviceId(): String {
		return deviceDataStore.data.map { it[PreferencesKey.deviceIdKey] }.first().orEmpty()
	}

	private object PreferencesKey {
		 val deviceIdKey = stringPreferencesKey("DEVICE_ID_KEY")
	}
}