package com.hanbang.designsystem.toast

import androidx.annotation.DrawableRes
import com.hanbang.designsystem.R

/**
 *
 * @author   JGeun
 * @created  2025/08/09
 */
sealed class HbSnackBarType(
	@DrawableRes val leadingIconRes: Int? = null,
	open val message: String = ""
) {
	data object NORMAL : HbSnackBarType()

	data class NOTICE(
		override val message: String = ""
	) : HbSnackBarType(
		leadingIconRes = R.drawable.ic_toast_notice,
		message = message
	)

	data class ERROR(
		override val message: String = ""
	) : HbSnackBarType(
		leadingIconRes = R.drawable.ic_toast_error,
		message = message
	)

	data class SUCCESS(
		override val message: String = ""
	) : HbSnackBarType(
		leadingIconRes = R.drawable.ic_toast_success,
		message = message
	)

	data class WARNING(
		override val message: String = ""
	) : HbSnackBarType(
		leadingIconRes = R.drawable.img_toast_warning,
		message = message
	)
}