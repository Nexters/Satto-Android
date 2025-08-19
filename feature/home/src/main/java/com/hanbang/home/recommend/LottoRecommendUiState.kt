package com.hanbang.home.recommend

sealed interface LottoRecommendUiState {
    data object Loading : LottoRecommendUiState

    data class Success(
        val userName: String = "",
        val round: Int = 0,
        val lottoNumbers: List<Pair<Int?, Int?>> = List(3) { Pair(null, null) },
        val remainTime: Long = Long.MAX_VALUE,
        val strongElement: String = "",
        val reason: String = "",
        val weakElement: String = "",
        val weakNumbers: List<Int> = emptyList(),
        val infrequentNumbers: List<Int> = emptyList(),
        val ctaType: CtaType = CtaType.CREATE_NUMBER
    ) : LottoRecommendUiState {
        val sortedLottoNumbers: List<Int?> = lottoNumbers.flatMap { listOf(it.first, it.second) }
    }

    enum class CtaType {
        CREATE_NUMBER, CHECK_RESULT
    }
}

sealed interface LottoRecommendUiEvent {

}