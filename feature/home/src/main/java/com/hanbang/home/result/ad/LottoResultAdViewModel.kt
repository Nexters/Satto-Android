package com.hanbang.home.result.ad

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.hanbang.domain.usecase.CheckLottoResultUseCase
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultAd
import com.hanbang.navigation.feature.lottoresult.RouteLottoResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LottoResultAdViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val checkLottoResultUseCase: CheckLottoResultUseCase
) : ContainerHost<LottoResultAdUiState, LottoResultAdEvent>, ViewModel() {
    override val container: Container<LottoResultAdUiState, LottoResultAdEvent> = container(
        initialState = LottoResultAdUiState()
    )

    private val resultAdRoute: RouteLottoResultAd = savedStateHandle.toRoute()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->  }
    private var animationJob: Job? = null
    private var currentMillis: Long = 0

    fun onResume() {
        startAnimation()
    }

    fun onPause() {
        pauseAnimation()
    }

    private fun startAnimation() = intent {
        if (state.isComplete) return@intent
        if (animationJob?.isActive == true) return@intent

        animationJob?.cancel()
        animationJob = viewModelScope.launch(coroutineExceptionHandler) {
            val lottoDeferred = async { checkLottoResultUseCase(resultAdRoute.round).first() }
            currentMillis = System.currentTimeMillis()
            reduce { state.copy(isPlaying = true) }
            val delayDeferred = async { delay(state.TOTAL_MILLIS - state.elapsedMillis) }

            val response = lottoDeferred.await()
            delayDeferred.await()

            reduce { state.copy(isPlaying = false) }
            postSideEffect(
                LottoResultAdEvent.NavigateToResult(
                    RouteLottoResultModel(
                        round = response.round,
                        recommendedNumbers = response.recommendedNumbers.associateWith { response.matchedNumbers.contains(it) },
                        resultNumbers = response.drawNumbers,
                        bonusNumber = response.bonusNumber,
                        rank = response.rank,
                        prizeAmount = response.prizeAmount
                    )
                )
            )
        }
    }

    private fun pauseAnimation() = intent {
        val remainMillis = System.currentTimeMillis() - currentMillis
        reduce { state.copy(isPlaying = false, elapsedMillis = remainMillis) }
    }
}