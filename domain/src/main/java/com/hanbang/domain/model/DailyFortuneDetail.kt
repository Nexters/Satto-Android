package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
data class DailyFortuneDetail(
	val id: String,
	val userId: String,
	val fortuneDate: String,
	val fortuneScore: Int,
	val fortuneComment: String
)