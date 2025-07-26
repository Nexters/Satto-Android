package com.hanbang.designsystem.chip

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.hanbang.designsystem.chip.model.HbChipColors

/**
 *
 * @author   JGeun
 * @created  2025/07/26
 */
object HbChipColorType {
	val SolidPrimary: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFF7843FF),
			contentColor = Color(0xFFFFFFFF)
		)

	val SolidBlack: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFF353C3F),
			contentColor = Color(0xFFFFFFFF)
		)

	val SolidGray : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFAEB7BC),
			contentColor = Color(0xFFFFFFFF)
		)

	val SolidRed : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFF5C67),
			contentColor = Color(0xFFFFFFFF)
		)

	val SolidOrange : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFF7943),
			contentColor = Color(0xFFFFFFFF)
		)

	val SolidYellow : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFDB3B),
			contentColor = Color(0xFFFFFFFF)
		)

	val SolidGreen : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFF41BD73),
			contentColor = Color(0xFFFFFFFF)
		)

	val SolidBlue : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFF48A3F3),
			contentColor = Color(0xFFFFFFFF)
		)

	val TintedPrimary : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFF2EDFF),
			contentColor = Color(0xFF7843FF)
		)

	val TintedBlack : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFF1F4F6),
			contentColor = Color(0xFF353C3F)
		)

	val TintedGray : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFF1F4F6),
			contentColor = Color(0xFFAEB7BC)
		)

	val TintedRed : HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFE6E8),
			contentColor = Color(0xFFFF5C67)
		)

	val TintedOrange: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFE8E0),
			contentColor = Color(0xFFF5662D)
		)

	val TintedYellow: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFF7D5),
			contentColor = Color(0xFFFFC300)
		)

	val TintedGreen: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFD2F8E3),
			contentColor = Color(0xFF41BD73)
		)

	val TintedBlue: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFE6F3FF),
			contentColor = Color(0xFF48A3F3)
		)

	val BorderPrimary: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFFFFF),
			contentColor = Color(0xFFBDA4FF),
			strokeColor = Color(0xFFDDD0FF)
		)

	val BorderBlack: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFFFFF),
			contentColor = Color(0xFF353C3F),
			strokeColor = Color(0xFF353C3F)
		)

	val BorderGray: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFFFFF),
			contentColor = Color(0xFFE7EDF0),
			strokeColor = Color(0xFFE7EDF0)
		)

	val BorderRed: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFFFFF),
			contentColor = Color(0xFFFF5C67),
			strokeColor = Color(0xFFFF5C67)
		)

	val BorderOrange: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFFFFF),
			contentColor = Color(0xFFFF7943),
			strokeColor = Color(0xFFFF7943)
		)

	val BorderYellow: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFFFFF),
			contentColor = Color(0xFFFFDB3B),
			strokeColor = Color(0xFFFFDB3B)
		)

	val BorderGreen: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFFFFF),
			contentColor = Color(0xFF41BD73),
			strokeColor = Color(0xFF41BD73)
		)

	val BorderBlue: HbChipColors
		@Composable
		get() = HbChipColors(
			containerColor = Color(0xFFFFFFFF),
			contentColor = Color(0xFF48A3F3),
			strokeColor = Color(0xFF48A3F3)
		)
}