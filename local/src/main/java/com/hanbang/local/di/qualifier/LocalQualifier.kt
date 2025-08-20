package com.hanbang.local.di.qualifier

import javax.inject.Qualifier

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DeviceDataStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserDataStore

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserSharedPreferences