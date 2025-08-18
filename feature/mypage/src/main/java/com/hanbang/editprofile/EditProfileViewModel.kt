package com.hanbang.editprofile

import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.domain.extension.toggle
import com.hanbang.domain.model.GenderType
import com.hanbang.domain.usecase.UpdateUserUseCase
import com.hanbang.editprofile.model.EditProfileSideEffect
import com.hanbang.editprofile.model.EditProfileUiState
import com.hanbang.navigation.feature.editprofile.RouteEditProfile
import com.hanbang.navigation.feature.editprofile.toEditProfileRouteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
@HiltViewModel
class EditProfileViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val updateUserUseCase: UpdateUserUseCase,
) : ViewModel(), ContainerHost<EditProfileUiState, EditProfileSideEffect> {

	override val container = container<EditProfileUiState, EditProfileSideEffect>(EditProfileUiState())

	private var previousStateData: EditProfileUiState? = null

	init {
		initializeStoreDetail()
	}

	private fun initializeStoreDetail() = intent {
		val route = savedStateHandle.toRoute<RouteEditProfile>()
		val routeModel = route.editProfileJson.toEditProfileRouteModel()

		reduce {
			state.copy(
				name = routeModel.name,
				genderType = GenderType.findByName(routeModel.gender),
				dateOfBirth = routeModel.dateOfBirth,
				birthTime = routeModel.birthTime.split(" ~ "),
				userBirthTimeUnknown = routeModel.userBirthTimeUnknown
			).also {
				if (previousStateData != null) return@also
				previousStateData = it
			}
		}
	}

	fun inputNameChanged(name: String) = blockingIntent {
		when {
			name.isBlank() -> {
				reduce {
					state.copy(
						name = name,
						nameInputErrorMsg = "이름을 입력해주세요.",
						buttonValidation = false
					)
				}
			}

			name.length > 6 -> {
				reduce {
					state.copy(
						name = name,
						nameInputErrorMsg = "이름은 최대 6자까지 입력 가능해요",
						buttonValidation = false
					)
				}
			}

			else -> {
				reduce {
					state.copy(
						name = name,
						nameInputErrorMsg = "",
						buttonValidation = checkButtonValidation(state.copy(name = name)),
					)
				}
			}
		}
	}

	fun onGenderSelected(genderType: GenderType) = intent {
		reduce {
			state.copy(
				genderType = genderType
			)
		}
	}

	fun inputDateOfBirth(dateOfBirth: String) = blockingIntent {
		when {
			dateOfBirth.isBlank() -> {
				reduce {
					state.copy(
						dateOfBirth = dateOfBirth,
						dateOfBirthErrorMsg = "올바른 형식으로 입력해 주세요."
					)
				}
			}

			else -> {
				reduce {
					state.copy(
						dateOfBirth = dateOfBirth,
						dateOfBirthErrorMsg = "",
						buttonValidation = checkButtonValidation(state.copy(dateOfBirth = dateOfBirth))
					)

				}
			}
		}
	}

	fun saveBirthTime(time: String) = blockingIntent {
		reduce {
			state.copy(
				birthTime = time.split(" ~ "),
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

	fun storeChangedUserInfo() = intent {
		if (equalPreviousProfileData()) {
			postSideEffect(EditProfileSideEffect.NavigateUp)
			return@intent
		}

		if (state.dateOfBirth.length != 8) {
			reduce {
				state.copy(
					dateOfBirthErrorMsg = "올바른 형식으로 입력해 주세요.",
					buttonValidation = false
				)
			}
			return@intent
		}

		reduce {
			state.copy(
				isLoading = true
			)
		}

		val updateUser = updateUserUseCase(
			name = state.name,
			birthDate = "${state.dateOfBirth.substring(0, 4)}-${state.dateOfBirth.substring(4, 6)}-${state.dateOfBirth.substring(6, 8)}",
			birthTime = if (state.userBirthTimeUnknown) emptyList() else  state.birthTime,
			genderType = state.genderType
		)

		updateUser
			.catch {
				postSideEffect(EditProfileSideEffect.ShowErrorMessage(it.message ?: "오류가 발생했습니다."))
			}.collect { user ->
				postSideEffect(EditProfileSideEffect.NavigateUp)
				postSideEffect(EditProfileSideEffect.ShowSnackBar(HbSnackBarType.SUCCESS(message = "프로필 수정이 완료됐소.")))
			}
	}


	private fun checkButtonValidation(state: EditProfileUiState): Boolean {
		return state.name.isNotEmpty() && state.nameInputErrorMsg.isEmpty()
				&& state.birthTime.isNotEmpty() && state.dateOfBirthErrorMsg.isEmpty()
				&& state.dateOfBirth.isNotEmpty() && state.dateOfBirthErrorMsg.isEmpty()
				&& state.dateOfBirth.isDigitsOnly() && state.dateOfBirth.length == 8
	}

	fun equalPreviousProfileData(): Boolean {
		return container.stateFlow.value == previousStateData
	}
}