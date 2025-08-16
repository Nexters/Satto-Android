package com.hanbang.fortune.model

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
sealed class FortuneSideEffect {
	data class ShowErrorMessage(val message: String) : FortuneSideEffect()
}