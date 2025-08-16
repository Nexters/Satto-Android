package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class LottoRecommendation(
	val userId: String,
	val round: Int,
	val content: LottoRecommendationContent?,
)