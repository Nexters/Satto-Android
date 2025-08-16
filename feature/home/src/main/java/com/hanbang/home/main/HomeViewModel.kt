package com.hanbang.home.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.domain.usecase.GetDailyFortunesUserCase
import com.hanbang.domain.usecase.GetLottoRecommendationUseCase
import com.hanbang.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getLottoRecommendationUseCase: GetLottoRecommendationUseCase,
    private val getDailyFortunesUseCase: GetDailyFortunesUserCase
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeUiState, HomeSideEffect> = container(
        initialState = HomeUiState(isLoading = true, content = HomeUiState.Content.empty),
        onCreate = {
            fetchHome()
        }
    )

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }

    private fun fetchHome() = intent {
        viewModelScope.launch(exceptionHandler) {
            val lottoRecommendationDeferred = async {
                getLottoRecommendationUseCase().first()
            }
            val fortunesDeferred = async {
                getDailyFortunesUseCase(fortuneDate = LocalDate.now().toString()).first()
            }
            val userDeferred = async {
                getUserUseCase().first()
            }

            val lottoRecommendation = lottoRecommendationDeferred.await()
            val fortunes = fortunesDeferred.await()
            val user = userDeferred.await()

            reduce {
                state.copy(
                    isLoading = false,
                    content = HomeUiState.Content(
                        round = lottoRecommendation.round,
                        lottoNumbers = lottoRecommendation.content?.let {
                            listOf(it.num1, it.num2, it.num3, it.num4, it.num5, it.num6)
                        } ?: List(6, { null }),
                        title = fortunes.title,
                        userName = user.name,
                        date = LocalDate.now(),
                        fortuneCategories = fortunes.content.map { fortune ->
                            HomeUiState.FortuneCategory(
                                id = fortune.id,
                                label = fortune.fortuneType,
                                imageUrl = fortune.imageUrl,
                                description = fortune.description
                            )
                        }
                    )
                )
            }
        }
    }
}