package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class LottoRecommendationContent(
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