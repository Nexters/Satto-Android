package com.hanbang.designsystem.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.button.model.HbButtonStyleProperties
import com.hanbang.designsystem.radius.HbRadius

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
object HbButtonHeight {
	val xLarge = 56.dp
	val large = 48.dp
	val medium = 40.dp
	val small  = 36.dp
	val xSmall = 32.dp
}

object HbButtonStyles {
	val xLarge: HbButtonStyleProperties
		@Composable
		get() = HbButtonStyleProperties(
			height = HbButtonHeight.xLarge,
			shape = RoundedCornerShape(HbRadius.Radius8),
			contentPadding = PaddingValues(horizontal = 24.dp, vertical = 14.5.dp),
			spacing = 8.dp,
			textStyle = TextStyle.Default
		)

	val large: HbButtonStyleProperties
		@Composable
		get() = HbButtonStyleProperties(
			height = HbButtonHeight.large,
			shape = RoundedCornerShape(HbRadius.Radius8),
			contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp),
			spacing = 8.dp,
			textStyle = TextStyle.Default
		)

	val medium: HbButtonStyleProperties
		@Composable
		get() = HbButtonStyleProperties(
			height = HbButtonHeight.medium,
			shape = RoundedCornerShape(HbRadius.Radius8),
			contentPadding = PaddingValues(horizontal = 18.dp, vertical = 9.5.dp),
			spacing = 6.dp,
			textStyle = TextStyle.Default
		)

	val small: HbButtonStyleProperties
		@Composable
		get() = HbButtonStyleProperties(
			height = HbButtonHeight.small,
			shape = RoundedCornerShape(HbRadius.Radius8),
			contentPadding = PaddingValues(horizontal = 14.dp, vertical = 7.5.dp),
			spacing = 6.dp,
			textStyle = TextStyle.Default
		)

	val xSmall: HbButtonStyleProperties
		@Composable
		get() = HbButtonStyleProperties(
			height = HbButtonHeight.xSmall,
			shape = RoundedCornerShape(HbRadius.Radius8),
			contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
			spacing = 4.dp,
			textStyle = TextStyle.Default
		)
}