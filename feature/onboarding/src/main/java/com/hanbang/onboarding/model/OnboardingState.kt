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
	val name: String = "",
	val nameInputErrorMsg: String = "",
	val buttonValidation: Boolean = false,
	val genderType: GenderType = GenderType.NONE,
	val dateOfBirth: String = "",
	val dateOfBirthInputErrorMsg: String = "",
	val birthTime: String = "",
	val userBirthTimeUnknown: Boolean = false,
	val stage: OnboardingStage = OnboardingStage.NAMING
) {
	val dateOfBirthYear: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(0, 4).orEmpty().toIntOrNull() ?: 0
	val dateOfBirthMonth: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(4, 6).orEmpty().toIntOrNull() ?: 0
	val dateOfBirthDay: Int get() = dateOfBirth.trimIndent().takeIf { it.isNotEmpty() }?.substring(6, 8).orEmpty().toIntOrNull() ?: 0

	val birthTimeHour: Int get() = birthTime.trimIndent().takeIf { it.isNotEmpty() }?.substring(0, 2).orEmpty().toIntOrNull() ?: 0
	val birthTimeMin: Int get() = birthTime.trimIndent().takeIf { it.isNotEmpty() }?.substring(2, 4).orEmpty().toIntOrNull() ?: 0
}