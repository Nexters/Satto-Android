package com.hanbang.data.datasource

import com.hanbang.data.model.DailyFortuneDetailDto
import com.hanbang.data.model.DailyFortuneDto
import com.hanbang.data.model.FourPillarDto
import com.hanbang.data.model.LottoRecommendationDto
import com.hanbang.data.model.UserDto

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
interface UserRemoteDataSource {

	suspend fun createUser(
		deviceId: String,
		name: String,
		birthDate: String,
		gender: String
	): UserDto

	suspend fun getUser(userId: String) : UserDto

	suspend fun updateUser(
		userId: String,
		name: String,
		birthDate: String,
		gender: String
	): UserDto

	suspend fun getUserFourPillar(
		userId: String
	): FourPillarDto

	suspend fun createLottoRecommendation(
		userId: String,
	): LottoRecommendationDto

	suspend fun getLottoRecommendation(
		 userId: String
	): LottoRecommendationDto

	suspend fun getDailyFortunes(
		userId: String,
		fortuneDate: String
	): List<DailyFortuneDto>

	suspend fun getDailyFortuneDetail(
		userId: String,
		fortuneDate: String
	): DailyFortuneDetailDto
}