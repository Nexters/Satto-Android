package com.hanbang.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.domain.usecase.GetUserUseCase
import com.hanbang.mypage.model.MyPageSideEffect
import com.hanbang.mypage.model.MyPageState
import com.hanbang.mypage.model.toEditProfileRouteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@HiltViewModel
class MyPageViewModel @Inject constructor(
	private val getUserUseCase: GetUserUseCase
) : ViewModel(), ContainerHost<MyPageState, MyPageSideEffect> {

	override val container = container<MyPageState, MyPageSideEffect>(MyPageState())

	fun initializeUserData() {
		viewModelScope.launch {
			getUserUseCase().collect { user ->
				val birthDateTime = parseBirthDateTime(user.birthDate).split("-")
				val dateOfBirth = "${birthDateTime.getOrNull(0).orEmpty()}-${birthDateTime.getOrNull(1).orEmpty()}-${birthDateTime.getOrNull(2).orEmpty()}"
				val birthTime = "${birthDateTime.getOrNull(3).orEmpty()}:${birthDateTime.getOrNull(4).orEmpty()}"

				intent {
					reduce {
						state.copy(
							isLoading = false,
							name = user.name,
							genderType = user.gender,
							dateOfBirth = dateOfBirth,
							birthTime = birthTime
						)
					}
				}
			}
		}
	}

	fun navigateToProfileEdit() {
		intent {
			postSideEffect(MyPageSideEffect.NavigateToEditProfile(state.toEditProfileRouteModel()))
		}
	}

	private fun parseBirthDateTime(dateTimeStr: String): String {
		val parsed: LocalDateTime = LocalDateTime.parse(dateTimeStr) ?: LocalDateTime.now()
		return parsed.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"))
	}
}