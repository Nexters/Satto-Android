package com.hanbang.remote.model

import com.hanbang.data.model.DailyFortuneDetailDto
import com.hanbang.data.model.FortuneDetailDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
@Serializable
data class GetDailyFortuneDetailResponse(
	val id: String,
	@SerialName("user_id")
	val userId: String,
	@SerialName("fortune_date")
	val fortuneDate: String,
	@SerialName("fortune_score")
	val fortuneScore: Int,
	@SerialName("fortune_comment")
	val fortuneComment: String,
	@SerialName("fortune_details")
	val fortuneDetails: List<FortuneDetailResponse>
)

@Serializable
data class FortuneDetailResponse(
	val type: String,
	val title: String,
	val content: String
)

internal fun GetDailyFortuneDetailResponse.toDto() = DailyFortuneDetailDto(
	id = id,
	userId = userId,
	fortuneDate = fortuneDate,
	fortuneScore = fortuneScore,
	fortuneComment = fortuneComment,
	fortuneDetails = fortuneDetails.map { it.toDto() }
)

internal fun FortuneDetailResponse.toDto() = FortuneDetailDto(
	type = type,
	title = title,
	content = content
)