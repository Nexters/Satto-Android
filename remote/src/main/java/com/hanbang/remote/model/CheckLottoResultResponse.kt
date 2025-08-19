package com.hanbang.remote.model

import com.hanbang.data.model.CheckLottoResultDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CheckLottoResultResponse(
    @SerialName("user_id")
    val userId: String = "",
    val round: Int = 0,
    @SerialName("draw_numbers")
    val drawNumbers: List<Int> = emptyList(),
    @SerialName("bonus_number")
    val bonusNumber: Int = 0,
    @SerialName("recommended_numbers")
    val recommendedNumbers: List<Int> = emptyList(),
    @SerialName("matched_count")
    val matchedCount: Int = 0,
    @SerialName("matched_numbers")
    val matchedNumbers: List<Int> = emptyList(),
    @SerialName("has_bonus")
    val hasBonus: Boolean = false,
    val rank: Int = 0,
    @SerialName("prize_amount")
    val prizeAmount: Long = 0L
)

internal fun CheckLottoResultResponse.toDto() = CheckLottoResultDto(
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
