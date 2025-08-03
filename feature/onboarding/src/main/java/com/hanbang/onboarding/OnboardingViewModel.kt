package com.hanbang.onboarding

import androidx.lifecycle.ViewModel
import com.hanbang.domain.extension.toggle
import com.hanbang.onboarding.model.OnboardingGenderType
import com.hanbang.onboarding.model.OnboardingSideEffect
import com.hanbang.onboarding.model.OnboardingState
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
class OnboardingViewModel : ContainerHost<OnboardingState, OnboardingSideEffect>, ViewModel() {

	override val container = container<OnboardingState, OnboardingSideEffect>(OnboardingState())

	fun inputNameChanged(name: String) = blockingIntent {
		reduce {
			state.copy(
				name = name,
				nameInputErrorMsg = "",
				buttonValidation = validateButtonState(state.copy(name = name))
			)
		}
	}

	fun onGenderSelected(genderType: OnboardingGenderType) = intent {
		reduce {
			state.copy(
				gender = genderType,
				buttonValidation = false,
				stage = state.stage.nextStage(),
			)
		}
	}

	fun inputDateOfBirth(dateOfBirth: String) = blockingIntent {
		reduce {
			state.copy(
				dateOfBirth = dateOfBirth,
				dateOfBirthInputErrorMsg = "",
				buttonValidation = validateButtonState(state.copy(dateOfBirth = dateOfBirth))
			)
		}
	}

	// TODO TextFieldValue를 사용하여 입력값을 관리하는 방법으로 변경해야 커서 조절이 가능
//	private fun formatBirthDate(input: String): String {
//		val digits = input.filter { it.isDigit() }.take(8)
//
//		return buildString {
//			for (i in digits.indices) {
//				append(digits[i])
//				if (i == 3 || i == 5) append('-') // yyyy-MM-dd 포맷용
//			}
//		}
//	}

	fun saveBirthTime(time: String) = blockingIntent {
		reduce {
			state.copy(
				birthTime = time,
				userBirthTimeUnknown = false,
				buttonValidation = true
			)
		}
	}

	fun toggleUserBirthTimeUnknown() = blockingIntent {
		reduce {
			state.copy(
				userBirthTimeUnknown = state.userBirthTimeUnknown.toggle(),
				buttonValidation = state.userBirthTimeUnknown.toggle() || state.birthTime.isNotEmpty()
			)
		}
	}

	fun validateStage() = intent {
		when (state.stage) {
			OnboardingStage.NAMING -> validateName()
			OnboardingStage.GENDER -> nextStage()
			OnboardingStage.DATE_OF_BIRTH -> validateDateOfBirth()
			OnboardingStage.BIRTH_TIME -> {
				nextStage()
				postSideEffect(OnboardingSideEffect.OpenAgreementDialog)
			}

			OnboardingStage.AGREEMENT -> {
				postSideEffect(OnboardingSideEffect.OpenAgreementDialog)
			}
		}
	}

	private fun validateName() = intent {
		when {
			state.name.isBlank() -> {
				reduce { state.copy(nameInputErrorMsg = "이름을 입력해주세요.") }
			}

			state.name.length > 6 -> {
				reduce { state.copy(nameInputErrorMsg = "이름은 최대 6자까지 입력 가능해요") }
			}

			else -> {
				reduce {
					state.copy(
						buttonValidation = false,
						nameInputErrorMsg = "",
						stage = state.stage.nextStage()
					)
				}
			}
		}
	}

	private fun nextStage() = intent {
		reduce {
			state.copy(
				stage = state.stage.nextStage(),
				buttonValidation = state.stage != OnboardingStage.AGREEMENT
			)
		}
	}

	private fun validateDateOfBirth() = blockingIntent {
		when {
			state.dateOfBirth.isBlank() -> {
				reduce { state.copy(dateOfBirthInputErrorMsg = "올바른 형식으로 입력해 주세요.") }
			}

			else -> {
				reduce {
					state.copy(
						dateOfBirthInputErrorMsg = "",
						stage = state.stage.nextStage(),
						buttonValidation = false
					)
				}
			}
		}
	}

	private fun validateButtonState(state: OnboardingState): Boolean {
		return when (state.stage) {
			OnboardingStage.NAMING -> {
				state.name.isNotBlank()
			}

			OnboardingStage.GENDER -> {
				state.gender != OnboardingGenderType.NONE
			}

			OnboardingStage.DATE_OF_BIRTH -> {
				state.dateOfBirth.isNotBlank()
			}

			OnboardingStage.BIRTH_TIME -> {
				state.userBirthTimeUnknown || state.birthTime.isNotEmpty()
			}

			OnboardingStage.AGREEMENT -> {
				true
			}
		}
	}
}