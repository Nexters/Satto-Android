package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class DailyFortune(
	val id: Int,
	val userId: String,
	val fortuneDate: String,
	val fortuneType: String,
	val imageUrl: String,
	val description: String
)