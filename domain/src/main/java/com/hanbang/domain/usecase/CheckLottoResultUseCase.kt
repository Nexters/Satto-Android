package com.hanbang.domain.usecase

import com.hanbang.domain.model.CheckLottoResult
import com.hanbang.domain.repository.SattoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckLottoResultUseCase @Inject constructor(
    private val sattoRepository: SattoRepository
) {
    operator fun invoke(round: Int): Flow<CheckLottoResult> = sattoRepository.checkLottoResult(round)
}