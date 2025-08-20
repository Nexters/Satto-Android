package com.hanbang.home.lottoad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.domain.usecase.CreateLottoRecommendationUseCase
import com.hanbang.domain.usecase.GetUserUseCase
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
class LottoAdViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val createLottoRecommendationUseCase: CreateLottoRecommendationUseCase
) : ContainerHost<LottoAdUiState, LottoAdEvent>, ViewModel() {
    override val container: Container<LottoAdUiState, LottoAdEvent> = container(
        initialState = LottoAdUiState(),
        onCreate = {
            val userName = getUserUseCase().first().name
            reduce {
                state.copy(userName = userName)
            }
        }
    )

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ -> }

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
            val lottoDeferred = async { createLottoRecommendationUseCase().first() }
            currentMillis = System.currentTimeMillis()
            reduce { state.copy(isPlaying = true) }
            val delayDeferred = async { delay(state.TOTAL_MILLIS - state.elapsedMillis) }

            lottoDeferred.await()
            delayDeferred.await()

            reduce { state.copy(isPlaying = false) }
            postSideEffect(LottoAdEvent.NavigateToLottoRecommend)
        }
    }

    private fun pauseAnimation() = intent {
        val remainMillis = System.currentTimeMillis() - currentMillis
        reduce { state.copy(isPlaying = false, elapsedMillis = remainMillis) }
    }
}