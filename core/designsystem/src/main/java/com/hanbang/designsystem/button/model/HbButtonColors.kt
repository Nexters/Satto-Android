package com.hanbang.designsystem.button.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
@Immutable
data class HbButtonColors(
	val contentColor: Color,
	val containerColor: Color,
	val iconColor: Color,
	val pressedContentColor: Color,
	val pressedContainerColor: Color,
	val pressedIconColor: Color,
	val disabledContentColor: Color,
	val disabledContainerColor: Color,
	val disabledIconColor: Color,
	val strokeColor: Color = Color.Transparent
)