package com.hanbang.editprofile.model

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
sealed class EditProfileSideEffect {

	data object NavigateUp : EditProfileSideEffect()

	data class ShowErrorMessage(
		val message: String
	) : EditProfileSideEffect()
}