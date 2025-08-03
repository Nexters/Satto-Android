package com.hanbang.onboarding

/**
 *
 * @author   JGeun
 * @created  2025/07/29
 */
enum class OnboardingStage(
	val order: Int
) {
	NAMING(1),
	GENDER(2),
	DATE_OF_BIRTH(3),
	BIRTH_TIME(4),
	AGREEMENT(5);

	fun nextStage() = when (this) {
		NAMING -> GENDER
		GENDER -> DATE_OF_BIRTH
		DATE_OF_BIRTH -> BIRTH_TIME
		else -> AGREEMENT
	}
}