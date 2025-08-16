package com.hanbang.data.model

import com.hanbang.domain.model.DailyFortuneDetail

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
data class DailyFortuneDetailDto(
	val id: String,
	val userId: String,
	val fortuneDate: String,
	val fortuneScore: Int,
	val fortuneComment: String
)

fun DailyFortuneDetailDto.toDomain() = DailyFortuneDetail(
	id = id,
	userId = userId,
	fortuneDate = fortuneDate,
	fortuneScore = fortuneScore,
	fortuneComment = fortuneComment
)