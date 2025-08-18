package com.hanbang.home.result.ad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LottoResultAdViewModel @Inject constructor() : ContainerHost<LottoResultAdUiState, LottoResultAdEvent>, ViewModel() {
    override val container: Container<LottoResultAdUiState, LottoResultAdEvent> = container(
        initialState = LottoResultAdUiState()
    )

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
        animationJob = viewModelScope.launch {
            val lottoDeferred = async {  }
            currentMillis = System.currentTimeMillis()
            reduce { state.copy(isPlaying = true) }
            val delayDeferred = async { delay(state.TOTAL_MILLIS - state.elapsedMillis) }

            lottoDeferred.await()
            delayDeferred.await()

            reduce { state.copy(isPlaying = false) }
            postSideEffect(LottoResultAdEvent.NavigateToResult)
        }
    }

    private fun pauseAnimation() = intent {
        val remainMillis = System.currentTimeMillis() - currentMillis
        reduce { state.copy(isPlaying = false, elapsedMillis = remainMillis) }
    }
}