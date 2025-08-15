package com.hanbang.editprofile.model

import com.hanbang.domain.model.GenderType

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
data class EditProfileUiState(
	val name: String = "",
	val nameInputErrorMsg: String = "",
	val genderType: GenderType = GenderType.NONE,
	val dateOfBirth: String = "",
	val dateOfBirthErrorMsg: String = "",
	val birthTime: String = "",
	val userBirthTimeUnknown: Boolean = false,
	val buttonValidation: Boolean = true
) {
	val dateOfBirthYear: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(0, 4).orEmpty().toIntOrNull() ?: 0
	val dateOfBirthMonth: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(4, 6).orEmpty().toIntOrNull() ?: 0
	val dateOfBirthDay: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(6, 8).orEmpty().toIntOrNull() ?: 0

	val birthTimeHour: Int get() = birthTime.trimIndent().takeIf { it.isNotEmpty() }?.substring(0, 2).orEmpty().toIntOrNull() ?: 0
	val birthTimeMin: Int get() = birthTime.trimIndent().takeIf { it.isNotEmpty() }?.substring(2, 4).orEmpty().toIntOrNull() ?: 0
}