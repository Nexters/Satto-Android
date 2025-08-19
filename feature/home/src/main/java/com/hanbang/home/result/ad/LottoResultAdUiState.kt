package com.hanbang.home.result.ad

import com.hanbang.navigation.feature.lottoresult.RouteLottoResultModel

data class LottoResultAdUiState(
    val isPlaying: Boolean = false,
    val elapsedMillis: Long = 0L,
    val userName: String = ""
) {
    val TOTAL_MILLIS = 3000L
    val isComplete = elapsedMillis >= TOTAL_MILLIS
}

sealed interface LottoResultAdEvent {
    data class NavigateToResult(val args: RouteLottoResultModel) : LottoResultAdEvent
}
