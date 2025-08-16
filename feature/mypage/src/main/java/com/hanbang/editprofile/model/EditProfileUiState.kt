package com.hanbang.editprofile.model

import com.hanbang.domain.model.GenderType

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
data class EditProfileUiState(
	val isLoading: Boolean = false,
	val name: String = "",
	val nameInputErrorMsg: String = "",
	val genderType: GenderType = GenderType.NONE,
	val dateOfBirth: String = "",
	val dateOfBirthErrorMsg: String = "",
	val birthTime: List<String> = emptyList(),
	val userBirthTimeUnknown: Boolean = false,
	val buttonValidation: Boolean = true
) {
	val birthTimeStr = birthTime.joinToString(separator = " ~ ")
}