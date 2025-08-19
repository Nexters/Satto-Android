package com.hanbang.home.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.hanbang.domain.usecase.CheckLottoResultUseCase
import com.hanbang.navigation.feature.lottoresult.RouteLottoResult
import com.hanbang.navigation.feature.lottoresult.toRouteLottoResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LottoResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val checkLottoResultUseCase: CheckLottoResultUseCase
): ContainerHost<LottoResultUiState, LottoResultEvent>, ViewModel() {
    override val container: Container<LottoResultUiState, LottoResultEvent> = container(
        initialState = LottoResultUiState(),
        onCreate = {
            val route = savedStateHandle.toRoute<RouteLottoResult>().lottoResultJson.toRouteLottoResultModel()

        }
    )
}