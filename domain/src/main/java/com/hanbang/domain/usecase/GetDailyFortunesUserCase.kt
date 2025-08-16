package com.hanbang.domain.usecase

import com.hanbang.domain.model.DailyFortune
import com.hanbang.domain.repository.SattoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyFortunesUserCase @Inject constructor(
    private val sattoRepository: SattoRepository
) {
    operator fun invoke(
        fortuneDate: String
    ): Flow<DailyFortune> = sattoRepository.getDailyFortunes(
        fortuneDate = fortuneDate
    )
}