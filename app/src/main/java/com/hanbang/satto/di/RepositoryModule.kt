package com.hanbang.satto.di

import com.hanbang.data.repository.DefaultDeviceRepository
import com.hanbang.data.repository.DefaultSattoRepository
import com.hanbang.domain.repository.DeviceRepository
import com.hanbang.domain.repository.SattoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

	@Singleton
	@Binds
	abstract fun bindDeviceRepository(
		deviceRepository: DefaultDeviceRepository
	) : DeviceRepository

	@Singleton
	@Binds
	abstract fun bindSattoRepository(
		sattoRepository: DefaultSattoRepository
	) : SattoRepository
}