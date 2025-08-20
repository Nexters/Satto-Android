package com.hanbang.home.result

data class LottoResultUiState(
    val round: Int = 0,
    val recommendedNumbers: Map<Int, Boolean> = emptyMap(),
    val resultNumbers: List<Int> = emptyList(),
    val bonusNumber: Int = 0,
    val rank: Int = Int.MAX_VALUE,
    val prizeAmount: Long = 0L,
    val isPlayingAnim: Boolean = false
) {
    val isRanked = rank in 1..5
}

sealed interface LottoResultEvent {

}
