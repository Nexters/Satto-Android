package com.hanbang.designsystem.chip.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 *
 * @author   JGeun
 * @created  2025/07/26
 */
@Immutable
data class HbChipColors(
	val containerColor: Color,
	val contentColor: Color,
	val strokeColor :Color = Color.Transparent
)