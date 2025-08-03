package com.hanbang.onboarding.model

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
sealed class OnboardingSideEffect {
	data object OpenAgreementDialog : OnboardingSideEffect()
}