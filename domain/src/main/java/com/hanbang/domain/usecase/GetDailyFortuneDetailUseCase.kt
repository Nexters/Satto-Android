package com.hanbang.domain.usecase

import com.hanbang.domain.model.DailyFortuneDetail
import com.hanbang.domain.repository.SattoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
class GetDailyFortuneDetailUseCase @Inject constructor(
	private val sattoRepository: SattoRepository
) {
	operator fun invoke(fortuneDate: String): Flow<DailyFortuneDetail> =
		sattoRepository.getDailyFortuneDetail(fortuneDate)
}