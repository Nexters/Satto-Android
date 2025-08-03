package com.hanbang.designsystem.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White

/**
 *
 * @author   JGeun
 * @created  2025/08/02
 */
object HbBottomSheetDefaults {
	@Composable
	fun colors(
		container: Color = White,
		titleColor:Color = Gray1,
	) = HbBottomSheetColors(
		containerColor = container,
		titleColor = titleColor
	)

	@Composable
	fun textStyles(
		titleTextStyle: TextStyle = SattoTheme.typography.body18Bold
	) = HbBottomSheetTextStyles(
		titleTextStyle = titleTextStyle,
	)
}

@Immutable
data class HbBottomSheetColors(
	val containerColor: Color,
	val titleColor: Color
)

@Immutable
data class HbBottomSheetTextStyles(
	val titleTextStyle: TextStyle
)
