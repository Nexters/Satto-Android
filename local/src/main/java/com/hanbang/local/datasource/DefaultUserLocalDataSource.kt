package com.hanbang.local.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.hanbang.data.datasource.UserLocalDataSource
import com.hanbang.local.di.qualifier.UserDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
class DefaultUserLocalDataSource @Inject constructor(
	@UserDataStore private val userDataStore: DataStore<Preferences>
) : UserLocalDataSource {

	override suspend fun getUserId(): String {
		return userDataStore.data.map { it[PreferencesKey.userIdKey] }.first().orEmpty()
	}

	override suspend fun storeUserId(userId: String): String {
		val preferences = userDataStore.edit {
			it[PreferencesKey.userIdKey] = userId
		}

		return preferences[PreferencesKey.userIdKey].orEmpty()
	}

	private object PreferencesKey {
		val userIdKey = stringPreferencesKey("USER_ID_KEY")
	}
}