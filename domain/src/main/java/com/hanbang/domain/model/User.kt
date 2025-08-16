package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class User(
	   val id: String,
	   val name: String,
	   val birthDate: String,
	   val birthTime: List<String?>,
	   val gender: GenderType,
	   val fourPillar: FourPillar
)