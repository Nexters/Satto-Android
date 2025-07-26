package com.hanbang.designsystem.chip

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.chip.model.HbChipStyleProperties
import com.hanbang.designsystem.radius.HbRadius

/**
 *
 * @author   JGeun
 * @created  2025/07/26
 */

object HbChipStyles {
	val round: HbChipStyleProperties
		@Composable
		get() = HbChipStyleProperties(
			height = 28.dp,
			shape = RoundedCornerShape(99.dp),
			contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
			spacing = 2.dp,
			textStyle = TextStyle.Default
		)

	val square: HbChipStyleProperties
		@Composable
		get() = HbChipStyleProperties(
			height = 28.dp,
			shape = RoundedCornerShape(HbRadius.Radius6),
			contentPadding = PaddingValues(horizontal = 10.dp, vertical = 0.dp),
			spacing = 2.dp,
			textStyle = TextStyle.Default
		)
}