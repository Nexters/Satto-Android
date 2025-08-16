package com.hanbang.remote.model

import com.hanbang.data.model.LottoRecommendationContentDto
import com.hanbang.data.model.LottoRecommendationDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
@Serializable
data class CreateLottoRecommendationResponse(
	@SerialName("user_id")
	val userId: String,
	val round: Int,
	val content: Content,
) {
	@Serializable
	data class Content(
		val reason: String,
		val num1: Int,
		val num2: Int,
		val num3: Int,
		val num4: Int,
		val num5: Int,
		val num6: Int,
		@SerialName("cold_nums")
		val coldNums: List<Int>,
		@SerialName("infrequent_nums")
		val infrequentNums: List<Int>,
		@SerialName("strong_element")
		val strongElement: String,
		@SerialName("weak_element")
		val weakElement: String
	)
}

internal fun CreateLottoRecommendationResponse.toDto() = LottoRecommendationDto(
	userId = userId,
	round = round,
	content = content.toDto(),
)

internal fun CreateLottoRecommendationResponse.Content.toDto() = LottoRecommendationContentDto(
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