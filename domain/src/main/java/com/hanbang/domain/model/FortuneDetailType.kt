package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/19
 */
enum class FortuneDetailType(
	val value: String
) {
	MONEY("money"),
	JOB("job"),
	LOVE("love"),
	NONE("none");

	companion object {
		fun findByValue(value: String) = entries.find { it.value == value } ?: NONE
	}
}