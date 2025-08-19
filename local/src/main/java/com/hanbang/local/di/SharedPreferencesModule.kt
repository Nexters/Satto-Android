package com.hanbang.local.di

import android.content.Context
import android.content.SharedPreferences
import com.hanbang.local.di.qualifier.UserSharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    private const val SHARED_PREFS_NAME = "STORE_SHARED_PREFS"

    @Provides
    @Singleton
    @UserSharedPreferences
    fun provideUserSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }
}