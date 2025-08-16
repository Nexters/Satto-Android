package com.hanbang.home.main

import java.time.LocalDate

data class HomeUiState(
    val isLoading: Boolean = false,
    val content: Content,
) {
    data class Content(
        val round: Int,
        val title: String,
        val date: LocalDate,
        val userName: String,
        val lottoNumbers: List<Int?>,
        val fortuneCategories: List<FortuneCategory>,
    ) {
        companion object {
            val empty = Content(
                round = -1,
                title = "",
                userName = "",
                date = LocalDate.now(),
                lottoNumbers = emptyList(),
                fortuneCategories = emptyList(),
            )
        }
    }

    data class FortuneCategory(
        val id: Int,
        val label: String,
        val imageUrl: String,
        val description: String,
    )
}

sealed interface HomeSideEffect {

}
