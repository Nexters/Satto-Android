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
)