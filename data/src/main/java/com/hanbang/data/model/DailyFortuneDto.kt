package com.hanbang.data.model

import com.hanbang.domain.model.DailyFortune

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class DailyFortuneDto(
	val id: Int,
	val userId: String,
	val fortuneDate: String,
	val fortuneType: String,
	val imageUrl: String,
	val description: String
)

internal fun DailyFortuneDto.toDomain(): DailyFortune {
	return com.hanbang.domain.model.DailyFortune(
		id = id,
		userId = userId,
		fortuneDate = fortuneDate,
		fortuneType = fortuneType,
		imageUrl = imageUrl,
		description = description
	)
}

internal fun List<DailyFortuneDto>.toDomain() = map { it.toDomain() }