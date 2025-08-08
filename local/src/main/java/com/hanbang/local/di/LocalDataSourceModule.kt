package com.hanbang.local.di

import com.hanbang.data.datasource.UserLocalDataSource
import com.hanbang.local.datasource.DefaultUserLocalDataSource
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
internal abstract class LocalDataSourceModule {

	@Singleton
	@Binds
	abstract fun bindUserLocalDataSource(
		userLocalDataSource: DefaultUserLocalDataSource
	): UserLocalDataSource
}