package com.hanbang.remote.datasource

import android.util.Log
import com.hanbang.data.datasource.UserRemoteDataSource
import com.hanbang.data.model.DailyFortuneDetailDto
import com.hanbang.data.model.DailyFortuneDto
import com.hanbang.data.model.FourPillarDto
import com.hanbang.data.model.LottoRecommendationDto
import com.hanbang.data.model.UserDto
import com.hanbang.remote.model.CreateUserParam
import com.hanbang.remote.model.UpdateUserParam
import com.hanbang.remote.model.toDto
import com.hanbang.remote.service.UserService
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
class DefaultUserRemoteDataSource @Inject constructor(
		private val userService: UserService
) : UserRemoteDataSource {

	override suspend fun createUser(
		deviceId: String,
		name: String,
		birthDate: String,
		birthTime: List<String>,
		gender: String
	): UserDto {
		val createUserParam = CreateUserParam(
			deviceId = deviceId,
			name = name,
			birthDate = birthDate,
			birthTime = birthTime,
			gender = gender
		).toRequestBody()

		 return userService.createUser(createUserParam).toDto()
	}

	override suspend fun getUser(userId: String): UserDto {
		return userService.getUser(userId).toDto()
	}

	override suspend fun updateUser(
		userId: String,
		name: String,
		birthDate: String,
		birthTime: List<String>,
		gender: String
	): UserDto {
		Log.w("Test@@@", "updateUser: $name $")

		val updateUserParam = UpdateUserParam(
			name = name,
			birthDate = birthDate,
			birthTime = birthTime,
			gender = gender
		)

		return userService.updateUser(
			userId = userId,
			updateUserParam.toRequestBody()
		).toDto()
	}

	override suspend fun getUserFourPillar(userId: String): FourPillarDto {
		return userService.getUserFourPillar(userId).toDto()
	}

	override suspend fun createLottoRecommendation(userId: String): LottoRecommendationDto {
		return userService.createLottoRecommendation(userId).toDto()
	}

	override suspend fun getLottoRecommendation(userId: String): LottoRecommendationDto {
		return userService.getLottoRecommendation(userId).toDto()
	}

	override suspend fun getDailyFortunes(userId: String, fortuneDate: String): DailyFortuneDto {
		return userService.getDailyFortunes(
			userId = userId,
			fortuneDate = fortuneDate
		).toDto()
	}

	override suspend fun getDailyFortuneDetail(userId: String, fortuneDate: String): DailyFortuneDetailDto {
		return userService.getDailyFortuneDetail(
			userId = userId,
			fortuneDate = fortuneDate
		).toDto()
	}
}