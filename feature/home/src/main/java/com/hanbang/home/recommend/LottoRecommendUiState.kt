package com.hanbang.home.recommend

data class LottoRecommendUiState(
    val userName: String = "",
    val round: Int = 0,
    val lottoNumbers: List<Pair<Int, Int>> = emptyList(),
    val remainTime: Long = Long.MAX_VALUE,
    val isLoading: Boolean = false,
    val strongElement: String = "",
    val reason: String = "",
    val weakElement: String = "",
    val weakNumbers: List<Int> = emptyList(),
    val infrequentNumbers: List<Int> = emptyList(),
) {
    val sortedLottoNumbers: List<Int> = lottoNumbers.flatMap { listOf(it.first, it.second) }.sorted()
}

sealed interface LottoRecommendUiEvent {

}