package com.hanbang.domain.usecase

import com.hanbang.domain.repository.SattoRepository
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
class GetUserIdUseCase @Inject constructor(
	private val sattoRepository: SattoRepository
) {

	suspend operator fun invoke(): String {
		return sattoRepository.getUserId()
	}
}