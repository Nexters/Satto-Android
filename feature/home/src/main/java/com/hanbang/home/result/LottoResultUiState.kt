package com.hanbang.home.result

data class LottoResultUiState(
    val isLoading: Boolean = false
)

sealed interface LottoResultEvent {

}
