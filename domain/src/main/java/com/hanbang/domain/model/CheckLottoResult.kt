package com.hanbang.domain.model

data class CheckLottoResult(
    val userId: String,
    val round: Int,
    val drawNumbers: List<Int>,
    val bonusNumber: Int,
    val recommendedNumbers: List<Int>,
    val matchedCount: Int,
    val matchedNumbers: List<Int>,
    val hasBonus: Boolean,
    val rank: Int,
    val prizeAmount: Long
)
