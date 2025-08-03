package com.hanbang.navigation.di

import com.hanbang.navigation.Navigator
import com.hanbang.navigation.navigator.InternalNavigator
import com.hanbang.navigation.navigator.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class RouterModule {

	@Binds
	@ActivityRetainedScoped
	abstract fun provideNavigator(
		navigator: NavigatorImpl
	): Navigator

	@Binds
	@ActivityRetainedScoped
	abstract fun provideInternalNavigator(
		navigator: NavigatorImpl
	): InternalNavigator
}