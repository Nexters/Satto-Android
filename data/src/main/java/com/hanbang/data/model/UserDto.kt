package com.hanbang.data.model

import com.hanbang.domain.model.GenderType
import com.hanbang.domain.model.User

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class UserDto(
	val id: String,
	val name: String,
	val birthDate: String,
	val gender: String,
	val fourPillar: FourPillarDto
)

internal fun UserDto.toDomain() = User(
	id = id,
	name = name,
	birthDate = birthDate,
	gender = GenderType.findByValue(gender),
	fourPillar = fourPillar.toDomain()
)