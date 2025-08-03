package com.hanbang.designsystem.chip.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

/**
 *
 * @author   JGeun
 * @created  2025/07/26
 */
data class HbChipStyleProperties(
	val height: Dp,
	val shape: Shape,
	val contentPadding: PaddingValues,
	val spacing: Dp,
	val textStyle: TextStyle
)