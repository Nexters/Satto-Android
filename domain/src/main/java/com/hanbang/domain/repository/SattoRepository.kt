package com.hanbang.domain.repository

import com.hanbang.domain.model.DailyFortuneDetail
import com.hanbang.domain.model.FourPillar
import com.hanbang.domain.model.DailyFortune
import com.hanbang.domain.model.GenderType
import com.hanbang.domain.model.LottoRecommendation
import com.hanbang.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
interface SattoRepository {

	suspend fun getUserId(): String

	fun createUser(
		name: String,
		birthDate: String,
		birthTime: List<String>,
		genderType: GenderType
	): Flow<User>

	fun getUser(): Flow<User>

	fun updateUser(
		name: String,
		birthDate: String,
		birthTime: List<String>,
		genderType: GenderType
	): Flow<User>

	fun getUserFourPillar(): Flow<FourPillar>

	fun getDailyFortuneDetail(
		fortuneDate: String
	): Flow<DailyFortuneDetail>

	fun getDailyFortunes(
		fortuneDate: String
	): Flow<DailyFortune>

	fun getLottoRecommendation(): Flow<LottoRecommendation>
}