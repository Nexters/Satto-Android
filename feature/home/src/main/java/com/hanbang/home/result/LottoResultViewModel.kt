package com.hanbang.home.result

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.hanbang.navigation.feature.lottoresult.RouteLottoResult
import com.hanbang.navigation.feature.lottoresult.toRouteLottoResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LottoResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ContainerHost<LottoResultUiState, LottoResultEvent>, ViewModel() {
    override val container: Container<LottoResultUiState, LottoResultEvent> = container(
        initialState = LottoResultUiState(),
        onCreate = {
            val route = savedStateHandle.toRoute<RouteLottoResult>().lottoResultJson.toRouteLottoResultModel()
            intent {
                reduce {
                    state.copy(
                        round = route.round,
                        recommendedNumbers = route.recommendedNumbers,
                        resultNumbers = route.resultNumbers,
                        bonusNumber = route.bonusNumber,
                        rank = route.rank,
                        prizeAmount = route.prizeAmount
                    )
                }
            }
        }
    )
}