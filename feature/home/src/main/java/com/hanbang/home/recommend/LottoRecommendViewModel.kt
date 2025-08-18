package com.hanbang.home.recommend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.domain.usecase.GetLottoRecommendationUseCase
import com.hanbang.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@HiltViewModel
class LottoRecommendViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getLottoRecommendationUseCase: GetLottoRecommendationUseCase
) : ContainerHost<LottoRecommendUiState, LottoRecommendUiEvent>, ViewModel() {
    override val container: Container<LottoRecommendUiState, LottoRecommendUiEvent> = container(
        initialState = LottoRecommendUiState.Loading,
        onCreate = {
            fetchLottoRecommend()
        }
    )

    private val lottoOpenDateTime = LocalDateTime
        .now()
        .with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
        .withHour(20)
        .withMinute(30)

    private fun fetchLottoRecommend() = intent {
        viewModelScope.launch {
            val userDeferred = async { getUserUseCase().first() }
            val lottoDeferred = async { getLottoRecommendationUseCase().first() }

            val userName = userDeferred.await().name
            val lottoData = lottoDeferred.await()

            reduce {
                LottoRecommendUiState.Success(
                    userName = userName,
                    round = lottoData.round,
                    lottoNumbers = lottoData.content?.let {
                        listOf(
                            Pair(it.num1, it.num2),
                            Pair(it.num3, it.num4),
                            Pair(it.num5, it.num6)
                        )
                    } ?: List(3) { Pair(null, null) },
                    remainTime = Duration.between(LocalDateTime.now(), lottoOpenDateTime).toMillis() / 1000L,
                    strongElement = lottoData.content?.strongElement ?: "",
                    reason = lottoData.content?.reason ?: "",
                    weakElement = lottoData.content?.weakElement ?: "",
                    weakNumbers = lottoData.content?.coldNums ?: emptyList(),
                    infrequentNumbers = lottoData.content?.infrequentNums ?: emptyList()
                )
            }
        }
    }
}