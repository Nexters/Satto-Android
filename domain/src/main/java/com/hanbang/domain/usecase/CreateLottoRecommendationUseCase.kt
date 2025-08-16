package com.hanbang.domain.usecase

import com.hanbang.domain.repository.SattoRepository
import javax.inject.Inject

class CreateLottoRecommendationUseCase @Inject constructor(
    private val sattoRepository: SattoRepository
) {
    operator fun invoke() = sattoRepository.createLottoRecommendation()
}