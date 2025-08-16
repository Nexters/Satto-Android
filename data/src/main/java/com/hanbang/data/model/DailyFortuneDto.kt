package com.hanbang.data.model

import com.hanbang.domain.model.DailyFortune

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class DailyFortuneDto(
	val title: String,
	val content: List<Content>
) {
	data class Content(
		val id: Int,
		val userId: String,
		val fortuneDate: String,
		val fortuneType: String,
		val imageUrl: String,
		val description: String
	)
}

internal fun DailyFortuneDto.Content.toDomain(): DailyFortune.Content {
	return DailyFortune.Content(
		id = id,
		userId = userId,
		fortuneDate = fortuneDate,
		fortuneType = fortuneType,
		imageUrl = imageUrl,
		description = description
	)
}

internal fun List<DailyFortuneDto.Content>.toDomain() = map { it.toDomain() }

internal fun DailyFortuneDto.toDomain() = DailyFortune(
	title = title,
	content = content.toDomain()
)