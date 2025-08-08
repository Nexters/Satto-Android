package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class LottoRecommendation(
	val id: Int,
	val userId: String,
	val round: Int,
	val content: LottoRecommendationContent,
	val createdAt: String,
	val updatedAt: String
)