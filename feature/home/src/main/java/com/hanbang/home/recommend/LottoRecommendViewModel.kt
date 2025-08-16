package com.hanbang.home.recommend

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LottoRecommendViewModel @Inject constructor(

): ContainerHost<LottoRecommendUiState, LottoRecommendUiEvent>, ViewModel() {
    override val container: Container<LottoRecommendUiState, LottoRecommendUiEvent> = container(
        initialState = LottoRecommendUiState(isLoading = true)
    )
}