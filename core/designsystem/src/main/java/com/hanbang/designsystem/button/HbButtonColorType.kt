package com.hanbang.designsystem.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hanbang.designsystem.button.model.HbButtonColors

/**
 *
 * @author   JGeun
 * @created  2025/07/23
 */
object HbButtonColorType {
	val primary: HbButtonColors
		@Composable
		get() = HbButtonColors(
			contentColor = Color(0xFFFFFFFF),
			containerColor = Color(0xFF7843FF),
			iconColor = Color(0xFFFFFFFF),
			pressedContentColor = Color(0xFFFFFFFF),
			pressedContainerColor = Color(0xFF6A00FF),
			pressedIconColor = Color(0xFFFFFFFF),
			disabledContentColor = Color(0xFFFFFFFF),
			disabledContainerColor = Color(0xFFDDD0FF),
			disabledIconColor = Color(0xFFFFFFFF),
		)

	val tinted: HbButtonColors
		@Composable
		get() = HbButtonColors(
			contentColor = Color(0xFF7843FF),
			containerColor = Color(0xFFF2EDFF),
			iconColor = Color(0xFF7843FF),
			pressedContentColor = Color(0xFFF2EDFF),
			pressedContainerColor = Color(0xFFDDD0FF),
			pressedIconColor = Color(0xFFF2EDFF),
			disabledContainerColor = Color(0xFFF2EDFF),
			disabledContentColor = Color(0xFFDDD0FF),
			disabledIconColor = Color(0xFFDDD0FF),
		)

	val border: HbButtonColors
		@Composable
		get() = HbButtonColors(
			contentColor = Color(0xFF7843FF),
			containerColor = Color(0xFFFFFFFF),
			iconColor = Color(0xFF7843FF),
			pressedContentColor = Color(0xFF7843FF),
			pressedContainerColor = Color(0xFFF2EDFF),
			pressedIconColor = Color(0xFF7843FF),
			disabledContentColor = Color(0xFFAEB7BC),
			disabledContainerColor = Color(0xFFFFFFFF),
			disabledIconColor = Color(0xFFAEB7BC),
			strokeColor = Color(0xFF7843FF)
		)

	val borderGray: HbButtonColors
		@Composable
		get() = HbButtonColors(
			contentColor = Color(0xFF222729),
			containerColor = Color(0xFFFFFFFF),
			iconColor = Color(0xFF222729),
			pressedContentColor = Color(0xFF222729),
			pressedContainerColor = Color(0xFFF7F9FA),
			pressedIconColor = Color(0xFF222729),
			disabledContentColor = Color(0xFF000000),
			disabledContainerColor = Color(0xFFFFFFFF),
			disabledIconColor = Color(0xFF000000),
			strokeColor = Color(0xFF000000)
		)

	val text: HbButtonColors
		@Composable
		get() = HbButtonColors(
			contentColor = Color(0xFF171719),
			containerColor = Color.Transparent,
			iconColor = Color(0xFF171719),
			pressedContentColor = Color(0xFF6A00FF),
			pressedContainerColor = Color.Transparent,
			pressedIconColor = Color(0xFF6A00FF),
			disabledContentColor = Color(0xFFAEB7BC),
			disabledContainerColor = Color.Transparent,
			disabledIconColor = Color(0xFFAEB7BC),
			strokeColor = Color.Transparent
		)
}