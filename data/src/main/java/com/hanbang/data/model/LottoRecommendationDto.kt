package com.hanbang.data.model

import com.hanbang.domain.model.LottoRecommendation

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class LottoRecommendationDto(
	val userId: String,
	val round: Int,
	val content: LottoRecommendationContentDto?,
	val isFinished: Boolean
)

internal fun LottoRecommendationDto.toDomain(): LottoRecommendation {
	return LottoRecommendation(
		userId = userId,
		round = round,
		content = content?.toDomain(),
		isFinished = isFinished
	)
}