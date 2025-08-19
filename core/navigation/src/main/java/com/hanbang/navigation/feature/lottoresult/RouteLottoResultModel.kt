package com.hanbang.navigation.feature.lottoresult

import com.hanbang.navigation.model.Route
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class RouteLottoResultAd(val round: Int): Route

@Serializable
data class RouteLottoResult(val lottoResultJson: String) : Route

@Serializable
data class RouteLottoResultModel(
    val round: Int = 0,
    val recommendedNumbers: Map<Int, Boolean>,
    val resultNumbers: List<Int>,
    val bonusNumber: Int,
    val rank: Int,
    val prizeAmount: Long
)

fun RouteLottoResultModel.toJson(): String {
    val json = Json { isLenient = true }
    return try {
        json.encodeToString(this)
    } catch (e: Exception) {
        "{}"
    }
}

fun String.toRouteLottoResultModel(): RouteLottoResultModel {
    val json = Json { isLenient = true }
    return try {
        json.decodeFromString(RouteLottoResultModel.serializer(), this)
    } catch (e: Exception) {
        RouteLottoResultModel(0, mapOf(), listOf(), 0, 0, 0)
    }
}