package com.hanbang.fortune

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.domain.model.DailyFortuneDetail
import com.hanbang.domain.model.FourPillar
import com.hanbang.domain.model.User
import com.hanbang.domain.usecase.GetDailyFortuneDetailUseCase
import com.hanbang.domain.usecase.GetUserFourPillarUseCase
import com.hanbang.domain.usecase.GetUserUseCase
import com.hanbang.fortune.model.FortuneSideEffect
import com.hanbang.fortune.model.FortuneState
import com.hanbang.fortune.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@HiltViewModel
class FortuneViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val getUserUseCase: GetUserUseCase,
	private val getUserFourPillarUseCase: GetUserFourPillarUseCase,
	private val getDailyFortuneDetailUseCase: GetDailyFortuneDetailUseCase,
) : ViewModel(), ContainerHost<FortuneState, FortuneSideEffect> {

	override val container = container<FortuneState, FortuneSideEffect>(FortuneState())

	private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
		intent {
			dismissLoadingState()
			postSideEffect(FortuneSideEffect.ShowErrorMessage(throwable.message ?: "오류가 발생했습니다"))
		}
	}

	fun initializeFortuneViewData() = intent {
		showLoadingState()

		val todayDate = getTodayFortuneDate()
		viewModelScope.launch(exceptionHandler) {
			val userDeferred = async { getUserUseCase().first() }
			val fourPillarDeferred = async { getUserFourPillarUseCase().first() }
			val dailyFortuneDetailDeferred = async { getDailyFortuneDetailUseCase(fortuneDate = todayDate).first() }

			val user = userDeferred.await()
			val fourPillar = fourPillarDeferred.await()
			val dailyFortuneDetail = try {
				dailyFortuneDetailDeferred.await()
			} catch (e: Exception) {
				DailyFortuneDetail(
					id = "",
					userId = "",
					fortuneDate = "",
					fortuneScore = 0,
					fortuneComment = "",
					fortuneDetails = emptyList()
				)
			}

			reduce {
				Triple(user, fourPillar, dailyFortuneDetail).toFortuneState(todayDate)
			}
		}
	}

	private fun showLoadingState() = intent {
		reduce {
			state.copy(
				isLoading = true
			)
		}
	}

	private fun dismissLoadingState() = intent {
		reduce {
			state.copy(
				isLoading = false
			)
		}
	}

	private fun getTodayFortuneDate(): String {
		val localDateTime: LocalDateTime = LocalDateTime.now()
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	}
}

fun Triple<User, FourPillar, DailyFortuneDetail>.toFortuneState(
	todayDate: String
): FortuneState {
	val (user, fourPillar, dailyFortuneDetail) = this

	return FortuneState(
		isLoading = false,
		todayDate = todayDate,
		fortuneScore = dailyFortuneDetail.fortuneScore,
		fortuneComment = dailyFortuneDetail.fortuneComment,
		fortuneDetailList = dailyFortuneDetail.fortuneDetails,
		userName = user.name,
		userDateOfBirth = user.birthDate,
		userBirthTime = "",
		timePillarDetail = fourPillar.timePillarDetail.toUiModel(),
		dayPillarDetail = fourPillar.dayPillarDetail.toUiModel(),
		monthPillarDetail = fourPillar.monthPillarDetail.toUiModel(),
		yearPillarDetail = fourPillar.yearPillarDetail.toUiModel(),
		strongElement = fourPillar.strongElement,
		weakElement = fourPillar.weakElement,
		elementDescription = fourPillar.description
	)
}