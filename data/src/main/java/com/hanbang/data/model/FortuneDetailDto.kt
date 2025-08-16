package com.hanbang.data.model

import com.hanbang.domain.model.FortuneDetail

/**
 *
 * @author   JGeun
 * @created  2025/08/17
 */
data class FortuneDetailDto(
	val type: String,
	val title: String,
	val content: String
)

fun FortuneDetailDto.toDomain() = FortuneDetail(
	type = type,
	title = title,
	content = content
)