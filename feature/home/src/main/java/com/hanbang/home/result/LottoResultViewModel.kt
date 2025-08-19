package com.hanbang.home.result

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LottoResultViewModel @Inject constructor(): ContainerHost<LottoResultUiState, LottoResultEvent>, ViewModel() {
    override val container: Container<LottoResultUiState, LottoResultEvent> = container(
        initialState = LottoResultUiState(isLoading = true)
    )
}