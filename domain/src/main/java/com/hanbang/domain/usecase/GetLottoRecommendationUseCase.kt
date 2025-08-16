package com.hanbang.domain.usecase

import com.hanbang.domain.model.LottoRecommendation
import com.hanbang.domain.repository.SattoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLottoRecommendationUseCase @Inject constructor(
    private val sattoRepository: SattoRepository
) {
    operator fun invoke(): Flow<LottoRecommendation> = sattoRepository.getLottoRecommendation()
}