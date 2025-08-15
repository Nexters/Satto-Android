package com.hanbang.domain.repository

import com.hanbang.domain.model.GenderType
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
		dateOfBirth: String,
		birthYear: Int,
		birthMonth: Int,
		birthDay: Int,
		birthHour: Int,
		birthMinute: Int,
		genderType: GenderType
	): Flow<User>
}