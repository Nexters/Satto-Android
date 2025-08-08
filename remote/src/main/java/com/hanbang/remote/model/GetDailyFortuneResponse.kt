package com.hanbang.remote.model

import com.hanbang.data.model.DailyFortuneDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
@Serializable
data class GetDailyFortuneResponse(
	val id: Int,
	@SerialName("user_id")
	val userId: String,
	@SerialName("fortune_date")
	val fortuneDate: String,
	@SerialName("fortune_type")
	val fortuneType: String,
	@SerialName("image_url")
	val imageUrl: String,
	val description: String
)

internal fun GetDailyFortuneResponse.toDto() = DailyFortuneDto(
	id = id,
	userId = userId,
	fortuneDate = fortuneDate,
	fortuneType = fortuneType,
	imageUrl = imageUrl,
	description = description
)

internal fun List<GetDailyFortuneResponse>.toDto(): List<DailyFortuneDto> =
	this.map { it.toDto() }