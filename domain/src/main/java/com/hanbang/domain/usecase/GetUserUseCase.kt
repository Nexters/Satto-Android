package com.hanbang.domain.usecase

import com.hanbang.domain.model.User
import com.hanbang.domain.repository.SattoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
class GetUserUseCase @Inject constructor(
	private val sattoRepository: SattoRepository
) {

	operator fun invoke(): Flow<User> = sattoRepository.getUser()
}