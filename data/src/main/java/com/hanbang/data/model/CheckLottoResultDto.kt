package com.hanbang.data.model

import com.hanbang.domain.model.CheckLottoResult

data class CheckLottoResultDto(
    val userId: String,
    val round: Int,
    val drawNumbers: List<Int>,
    val bonusNumber: Int,
    val recommendedNumbers: List<Int>,
    val matchedCount: Int,
    val matchedNumbers: List<Int>,
    val hasBonus: Boolean,
    val rank: Int,
    val prizeAmount: Long
)

internal fun CheckLottoResultDto.toDomain() = CheckLottoResult(
    userId = userId,
    round = round,
    drawNumbers = drawNumbers,
    bonusNumber = bonusNumber,
    recommendedNumbers = recommendedNumbers,
    matchedCount = matchedCount,
    matchedNumbers = matchedNumbers,
    hasBonus = hasBonus,
    rank = rank,
    prizeAmount = prizeAmount
)
