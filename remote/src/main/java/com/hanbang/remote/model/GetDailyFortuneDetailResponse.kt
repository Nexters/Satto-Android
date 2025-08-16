package com.hanbang.remote.model

import com.hanbang.data.model.DailyFortuneDetailDto
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
	val fortuneComment: String
)

internal fun GetDailyFortuneDetailResponse.toDto() = DailyFortuneDetailDto(
	id = id,
	userId = userId,
	fortuneDate = fortuneDate,
	fortuneScore = fortuneScore,
	fortuneComment = fortuneComment
)