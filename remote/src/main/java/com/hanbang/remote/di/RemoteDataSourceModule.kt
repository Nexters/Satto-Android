package com.hanbang.remote.di

import com.hanbang.data.datasource.UserRemoteDataSource
import com.hanbang.remote.datasource.DefaultUserRemoteDataSource
import com.hanbang.remote.service.UserService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
@Module
@InstallIn(SingletonComponent::class)
internal abstract class RemoteDataSourceModule {

	@Singleton
	@Binds
	abstract fun bindUserRemoteDataSource(
		userRemoteDataSource: DefaultUserRemoteDataSource
	): UserRemoteDataSource
}