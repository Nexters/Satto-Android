package com.hanbang.editprofile.model

import com.hanbang.designsystem.toast.HbSnackBarType

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

	data class ShowSnackBar(
		val hbSnackBarType: HbSnackBarType
	) : EditProfileSideEffect()
}