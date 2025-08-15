package com.hanbang.onboarding

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.domain.extension.toggle
import com.hanbang.domain.model.GenderType
import com.hanbang.domain.usecase.CreateUserUseCase
import com.hanbang.onboarding.model.OnboardingSideEffect
import com.hanbang.onboarding.model.OnboardingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
@HiltViewModel
class OnboardingViewModel @Inject constructor(
	private val createUserUseCase: CreateUserUseCase
) : ContainerHost<OnboardingState, OnboardingSideEffect>, ViewModel() {

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

	fun onGenderSelected(genderType: GenderType) = intent {
		reduce {
			state.copy(
				genderType = genderType,
				buttonValidation = false,
				stage = if (state.stage == OnboardingStage.GENDER) state.stage.nextStage() else state.stage
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
			OnboardingStage.GENDER -> {
				validateName()
				nextStage()
			}
			OnboardingStage.DATE_OF_BIRTH -> {
				validateName()
				validateDateOfBirth()
			}
			OnboardingStage.BIRTH_TIME -> {
				validateName()
				validateDateOfBirth()
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
						stage = if (state.stage == OnboardingStage.NAMING) state.stage.nextStage() else state.stage
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

			// TODO InputField 수정 후엔 조건 변경 필요
			state.dateOfBirth.trimIndent().length != 8 -> {
				reduce { state.copy(dateOfBirthInputErrorMsg = "생년월일을 8자리로 입력해주세요.") }
			}

			state.dateOfBirth.trimIndent().isDigitsOnly().not() -> {
				reduce { state.copy(dateOfBirthInputErrorMsg = "생년월일을 숫자로만 입력해주세요.") }
			}

			state.dateOfBirthYear !in 1900..2050 -> {
				reduce { state.copy(dateOfBirthInputErrorMsg = "생년월일을 정확하게 입력해주세요") }
			}

			state.dateOfBirthMonth !in 1..12 -> {
				reduce { state.copy(dateOfBirthInputErrorMsg = "생년월일을 정확하게 입력해주세요") }
			}

			monthDaySet().getOrDefault(state.dateOfBirthMonth, 0) < state.dateOfBirthDay -> {
				reduce { state.copy(dateOfBirthInputErrorMsg = "생년월일을 정확하게 입력해주세요.") }
			}

			else -> {
				reduce {
					state.copy(
						dateOfBirthInputErrorMsg = "",
						stage = if (state.stage == OnboardingStage.DATE_OF_BIRTH) state.stage.nextStage() else state.stage,
						buttonValidation = false
					)
				}
			}
		}
	}

	private fun monthDaySet() = mapOf(
		1 to 31, // January
		2 to 29, // February (leap year)
		3 to 31, // March
		4 to 30, // April
		5 to 31, // May
		6 to 30, // June
		7 to 31, // July
		8 to 31, // August
		9 to 30, // September
		10 to 31, // October
		11 to 30, // November
		12 to 31 // December
	)


	private fun validateButtonState(state: OnboardingState): Boolean {
		return when (state.stage) {
			OnboardingStage.NAMING -> {
				state.name.isNotBlank()
			}

			OnboardingStage.GENDER -> {
				state.genderType != GenderType.NONE
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

	fun createUser() = intent {
		viewModelScope.launch {
			createUserUseCase(
				name = state.name,
				birthYear = state.dateOfBirthYear,
				birthMonth = state.dateOfBirthMonth,
				birthDay = state.dateOfBirthDay,
				birthHour = state.birthTimeHour,
				birthMinute = state.birthTimeMin,
				genderType = state.genderType
			).catch {
				postSideEffect(
					OnboardingSideEffect.ShowError(it.message ?: "사용자 생성 중 오류가 발생했습니다.")
				)
			}.collect { user ->
				postSideEffect(OnboardingSideEffect.UserCreated)
			}
		}
	}
}