package com.hanbang.data.repository

import com.hanbang.data.datasource.DeviceLocalDataSource
import com.hanbang.data.datasource.UserLocalDataSource
import com.hanbang.data.datasource.UserRemoteDataSource
import com.hanbang.data.model.toDomain
import com.hanbang.domain.model.CheckLottoResult
import com.hanbang.domain.model.DailyFortuneDetail
import com.hanbang.domain.model.FourPillar
import com.hanbang.domain.model.DailyFortune
import com.hanbang.domain.model.GenderType
import com.hanbang.domain.model.LottoRecommendation
import com.hanbang.domain.model.User
import com.hanbang.domain.repository.SattoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
class DefaultSattoRepository @Inject constructor(
	private val userRemoteDataSource: UserRemoteDataSource,
	private val userLocalDataSource: UserLocalDataSource,
	private val deviceLocalDataSource: DeviceLocalDataSource,
) : SattoRepository {

	override suspend fun getUserId(): String {
		return withContext(Dispatchers.IO) {
			userLocalDataSource.getUserId()
		}
	}

	override fun createUser(
		name: String,
		birthDate: String,
		birthTime: List<String>,
		genderType: GenderType
	): Flow<User> = flow {
		val deviceId = deviceLocalDataSource.getDeviceId()

		val userDto = userRemoteDataSource.createUser(
			deviceId = deviceId,
			name = name,
			birthDate = birthDate,
			birthTime = birthTime,
			gender = genderType.value
		).also {
			userLocalDataSource.storeUserId(it.id)
		}

		emit(userDto.toDomain())
	}.flowOn(Dispatchers.IO)

	override fun getUser(): Flow<User> = flow {
		val userId = userLocalDataSource.getUserId()
		val userDto = userRemoteDataSource.getUser(userId)
		emit(userDto.toDomain())
	}.flowOn(Dispatchers.IO)

	override fun updateUser(
		name: String,
		birthDate: String,
		birthTime: List<String>,
		genderType: GenderType
	): Flow<User> = flow<User> {
		val userId = userLocalDataSource.getUserId()

		val userDto = userRemoteDataSource.updateUser(
			userId = userId,
			name = name,
			birthDate = birthDate,
			birthTime = birthTime,
			gender = genderType.value
		)

		emit(userDto.toDomain())
	}.flowOn(Dispatchers.IO)

	override fun getUserFourPillar(): Flow<FourPillar> = flow {
		val userId = userLocalDataSource.getUserId()
		val fourPillarDto = userRemoteDataSource.getUserFourPillar(userId)
		emit(fourPillarDto.toDomain())
	}.flowOn(Dispatchers.IO)


	override fun getDailyFortuneDetail(fortuneDate: String): Flow<DailyFortuneDetail> = flow {
		val userId = userLocalDataSource.getUserId()
		val dailyFortuneDetailDto = userRemoteDataSource.getDailyFortuneDetail(userId, fortuneDate)
		emit(dailyFortuneDetailDto.toDomain())
	}.flowOn(Dispatchers.IO)

	override fun getDailyFortunes(fortuneDate: String): Flow<DailyFortune> = flow {
		val userId = userLocalDataSource.getUserId()
		val dailyFortunesDto = userRemoteDataSource.getDailyFortunes(userId, fortuneDate)
		emit(dailyFortunesDto.toDomain())
	}.flowOn(Dispatchers.IO)

	override fun getLottoRecommendation(): Flow<LottoRecommendation> = flow {
		val userId = userLocalDataSource.getUserId()
		val lottoRecommendationDto = userRemoteDataSource.getLottoRecommendation(userId)
		emit(lottoRecommendationDto.toDomain())
	}.flowOn(Dispatchers.IO)

	override fun createLottoRecommendation(): Flow<LottoRecommendation> = flow {
		val userId = userLocalDataSource.getUserId()
		val lottoRecommendationDto = userRemoteDataSource.createLottoRecommendation(userId)
		emit(lottoRecommendationDto.toDomain())
	}.flowOn(Dispatchers.IO)

	private fun formatBirthDateTime(year: Int, month: Int, day: Int, hour: Int, minute: Int): String {
		val dateTime = LocalDateTime.of(year, month, day, hour, minute)
		val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
		return dateTime.format(formatter)
	}

	override fun checkLottoResult(round: Int): Flow<CheckLottoResult> = flow {
		val userId = userLocalDataSource.getUserId()
		val checkLottoResultDto = userRemoteDataSource.checkLottoResult(userId, round)
		emit(checkLottoResultDto.toDomain())
	}.flowOn(Dispatchers.IO)
}