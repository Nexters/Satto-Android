package com.hanbang.onboarding.model

import androidx.compose.runtime.Stable
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
	val gender: OnboardingGenderType = OnboardingGenderType.NONE,
	val dateOfBirth: String = "",
	val dateOfBirthInputErrorMsg: String = "",
	val birthTime: String = "",
	val userBirthTimeUnknown: Boolean = false,
	val stage: OnboardingStage = OnboardingStage.NAMING
)