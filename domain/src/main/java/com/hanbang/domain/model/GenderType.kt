package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
enum class GenderType(
	val value: String = ""
) {
	NONE, MALE("M"), FEMALE("F");

	companion object {
		fun findByName(name: String) = entries.firstOrNull() { it.name == name } ?: NONE

		fun findByValue(value: String) = entries.firstOrNull { it.value == value } ?: NONE
	}
}