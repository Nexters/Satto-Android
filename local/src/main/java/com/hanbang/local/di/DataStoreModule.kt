package com.hanbang.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.hanbang.local.di.qualifier.DeviceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

	private const val STORE_DATASTORE_NAME = "STORE_PREFERENCES"
	private val Context.storeDataSource by preferencesDataStore(STORE_DATASTORE_NAME)

	@Provides
	@Singleton
	@DeviceDataStore
	fun provideDeviceDataStore(
		@ApplicationContext context: Context
	): DataStore<Preferences> {
		return context.storeDataSource
	}
}