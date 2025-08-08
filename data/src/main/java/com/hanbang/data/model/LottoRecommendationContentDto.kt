package com.hanbang.data.model

import com.hanbang.domain.model.LottoRecommendationContent

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class LottoRecommendationContentDto(
	val reason: String,
	val num1: Int,
	val num2: Int,
	val num3: Int,
	val num4: Int,
	val num5: Int,
	val num6: Int,
	val coldNums: List<Int>,
	val infrequentNums: List<Int>,
	val strongElement: String,
	val weakElement: String
)

internal fun LottoRecommendationContentDto.toDomain(): LottoRecommendationContent {
	return LottoRecommendationContent(
		reason = reason,
		num1 = num1,
		num2 = num2,
		num3 = num3,
		num4 = num4,
		num5 = num5,
		num6 = num6,
		coldNums = coldNums,
		infrequentNums = infrequentNums,
		strongElement = strongElement,
		weakElement = weakElement
	)
}