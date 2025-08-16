package com.hanbang.home.main

import java.time.LocalDate

data class HomeUiState(
    val isLoading: Boolean = false,
    val content: Content,
) {
    data class Content(
        val round: Int,
        val title: String,
        val imageUrl: String,
        val date: LocalDate,
        val userName: String,
        val lottoNumbers: List<Int?>,
        val fortuneCategories: List<FortuneCategory>,
    ) {
        companion object {
            val empty = Content(
                round = -1,
                title = "",
                imageUrl = "",
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
        val imageRes: Int,
        val description: String,
    )
}

sealed interface HomeSideEffect {

}
