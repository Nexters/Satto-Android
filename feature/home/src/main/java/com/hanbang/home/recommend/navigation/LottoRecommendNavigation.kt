package com.hanbang.home.recommend.navigation

import kotlinx.serialization.Serializable

@Serializable
data class LottoRecommendRoute(
    val userId: Long,
    val userName: String,
    val lottoNumber: List<Int>
)