package com.hanbang.onboarding.model

import androidx.compose.runtime.Stable
import com.hanbang.domain.model.GenderType
import com.hanbang.onboarding.OnboardingStage

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
@Stable
data class OnboardingState(
	val isLoading: Boolean = false,
	val name: String = "",
	val nameInputErrorMsg: String = "",
	val buttonValidation: Boolean = false,
	val genderType: GenderType = GenderType.NONE,
	val dateOfBirth: String = "",
	val dateOfBirthInputErrorMsg: String = "",
	val birthTime: List<String> = emptyList(),
	val userBirthTimeUnknown: Boolean = false,
	val stage: OnboardingStage = OnboardingStage.NAMING
) {
	val dateOfBirthYear: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(0, 4).orEmpty().toIntOrNull() ?: 0
	val dateOfBirthMonth: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(4, 6).orEmpty().toIntOrNull() ?: 0
	val dateOfBirthDay: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(6, 8).orEmpty().toIntOrNull() ?: 0

	val birthTimeStr = birthTime.joinToString(separator = " ~ ")

	fun getDateOfBirthWithDash(): String {
		return if (dateOfBirth.length == 8) {
			"${dateOfBirth.substring(0, 4)}-${dateOfBirth.substring(4, 6)}-${dateOfBirth.substring(6, 8)}"
		} else {
			""
		}
	}
}