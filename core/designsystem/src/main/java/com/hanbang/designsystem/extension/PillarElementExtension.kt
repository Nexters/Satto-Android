package com.hanbang.designsystem.extension

import androidx.compose.runtime.Composable
import com.hanbang.designsystem.chip.HbChipColorType
import com.hanbang.designsystem.chip.model.HbChipColors
import com.hanbang.designsystem.theme.Blue4
import com.hanbang.designsystem.theme.Gray3
import com.hanbang.designsystem.theme.Gray4
import com.hanbang.designsystem.theme.Gray6
import com.hanbang.designsystem.theme.Red4
import com.hanbang.designsystem.theme.Yellow4
import com.hanbang.domain.model.PillarElement

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
fun PillarElement.getColor() = when(this) {
	PillarElement.WOOD -> Gray4
	PillarElement.FIRE -> Red4
	PillarElement.EARTH -> Yellow4
	PillarElement.METAL -> Gray3
	PillarElement.WATER -> Blue4
	PillarElement.NONE -> Gray6
}

@Composable
fun PillarElement.getHbChipColors(): HbChipColors = when(this) {
	PillarElement.NONE -> HbChipColorType.SolidGray
	PillarElement.WOOD -> HbChipColorType.SolidGray
	PillarElement.FIRE -> HbChipColorType.SolidRed
	PillarElement.EARTH -> HbChipColorType.SolidYellow
	PillarElement.METAL -> HbChipColorType.SolidBlack
	PillarElement.WATER -> HbChipColorType.SolidBlue
}