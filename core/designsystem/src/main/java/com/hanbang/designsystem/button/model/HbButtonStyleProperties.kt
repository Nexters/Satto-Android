package com.hanbang.designsystem.button.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
@Immutable
data class HbButtonStyleProperties(
	val height: Dp,
	val shape: Shape,
	val contentPadding: PaddingValues,
	val spacing: Dp,
	val textStyle: TextStyle,
)