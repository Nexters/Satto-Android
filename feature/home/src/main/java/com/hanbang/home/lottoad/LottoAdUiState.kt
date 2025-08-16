package com.hanbang.home.lottoad

data class LottoAdUiState(
    val isPlaying: Boolean = false,
    val elapsedMillis: Long = 0L,
    val userName: String = ""
) {
    val TOTAL_MILLIS = 2000L
    val isComplete = elapsedMillis >= TOTAL_MILLIS
}

sealed interface LottoAdEvent {
    data object NavigateToLottoRecommend: LottoAdEvent
}