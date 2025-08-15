package com.hanbang.satto.di

import com.hanbang.navigation.navigator.ActivityNavigator
import com.hanbang.satto.navigator.ActivityNavigatorImpl
import dagger.Module
import dagger.Provides
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
object ActivityNavigatorModule {

	@Singleton
	@Provides
	fun provideActivityNavigator(): ActivityNavigator {
		return ActivityNavigatorImpl()
	}
}