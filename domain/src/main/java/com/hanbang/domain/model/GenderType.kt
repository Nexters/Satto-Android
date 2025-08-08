package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
enum class GenderType {
	NONE, MALE, FEMALE;

	companion object {
		fun findByName(name: String) = entries.firstOrNull() { it.name == name } ?: NONE
	}
}