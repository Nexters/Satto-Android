package com.hanbang.remote.di

import android.util.Log
import com.hanbang.remote.BuildConfig
import com.hanbang.remote.service.LottoStoreService
import com.hanbang.remote.service.UserService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2025/08/09
 */
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

	@Provides
	@Singleton
	fun provideDefaultOkHttpClient(): OkHttpClient {
		return createDefaultOkHttpClient()
	}

	private fun createDefaultOkHttpClient(
		interceptor: Interceptor? = null
	): OkHttpClient {
		return OkHttpClient
			.Builder()
			.apply {
				interceptor?.let { addInterceptor(it) }
			}
			.addNetworkInterceptor(
				HttpLoggingInterceptor { message ->
					if (BuildConfig.DEBUG) {
						Log.w("Network Response", message)
					}
				}.apply {
					level = HttpLoggingInterceptor.Level.BODY
				}
			)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(
		client: OkHttpClient
	): Retrofit {
		val json = Json {
			ignoreUnknownKeys = true
			coerceInputValues = true
			isLenient = true
			prettyPrint = true
		}

		val converterFactory = json.asConverterFactory("application/json; charset=UTF8".toMediaType())

		return Retrofit.Builder()
			.baseUrl(BuildConfig.BASE_URL)
			.addConverterFactory(converterFactory)
			.client(client)
			.build()
	}

	@Provides
	@Singleton
	fun provideUserService(
		retrofit: Retrofit
	): UserService = retrofit.create(UserService::class.java)

	@Provides
	@Singleton
	fun provideLottoStoreService(
		retrofit: Retrofit
	): LottoStoreService = retrofit.create(LottoStoreService::class.java)
}