package com.hanbang.domain.usecase

import com.hanbang.domain.model.GenderType
import com.hanbang.domain.model.User
import com.hanbang.domain.repository.SattoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
class CreateUserUseCase @Inject constructor(
	private val sattoRepository: SattoRepository
) {

	operator fun invoke(
		name: String,
		birthYear: Int,
		birthMonth: Int,
		birthDay: Int,
		birthHour: Int,
		birthMinute: Int,
		genderType: GenderType
	): Flow<User> = sattoRepository.createUser(
		name = name,
		dateOfBirth = "",
		birthYear = birthYear,
		birthMonth = birthMonth,
		birthDay = birthDay,
		birthHour = birthHour,
		birthMinute = birthMinute,
		genderType = genderType
	)
}