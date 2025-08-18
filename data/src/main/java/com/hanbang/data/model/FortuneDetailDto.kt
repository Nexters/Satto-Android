package com.hanbang.data.model

import com.hanbang.domain.model.FortuneDetail
import com.hanbang.domain.model.FortuneDetailType

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
	type = FortuneDetailType.findByValue(type),
	title = title,
	content = content
)