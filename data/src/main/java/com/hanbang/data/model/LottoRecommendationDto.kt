package com.hanbang.data.model

import com.hanbang.domain.model.LottoRecommendation

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class LottoRecommendationDto(
	val id: Int,
	val userId: String,
	val round: Int,
	val content: LottoRecommendationContentDto,
	val createdAt: String,
	val updatedAt: String
)

internal fun LottoRecommendationDto.toDomain(): LottoRecommendation {
	return LottoRecommendation(
		id = id,
		userId = userId,
		round = round,
		content = content.toDomain(),
		createdAt = createdAt,
		updatedAt = updatedAt
	)
}