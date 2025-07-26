package com.hanbang.designsystem.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.chip.model.HbChipColors
import com.hanbang.designsystem.chip.model.HbChipStyleProperties
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/07/26
 */
@Composable
fun HbChip(
	text: String,
	onClick: () -> Unit,
	colors: HbChipColors,
	styles: HbChipStyleProperties,
	modifier: Modifier = Modifier,
	leftContent: @Composable () -> Unit = {},
	rightContent: @Composable () -> Unit = {}
) {
	Row(
		modifier = Modifier
			.background(colors.containerColor, shape = styles.shape)
			.border(width = 1.dp, color = colors.strokeColor, shape = styles.shape)
			.clickableSingle(activeRippleEffect = false) { onClick() }
			.padding(styles.contentPadding),
		horizontalArrangement = Arrangement.spacedBy(styles.spacing)
	) {
		leftContent()

		Text(
			text = text,
			style = styles.textStyle,
			color = colors.contentColor
		)

		rightContent()
	}
}

@Preview
@Composable
private fun HbChipPreview() {
	Column {
		HbChip(
			text = "테스트",
			onClick = {},
			colors = HbChipColorType.SolidPrimary,
			styles = HbChipStyles.square
		)

		HbChip(
			text = "테스트",
			onClick = {},
			colors = HbChipColorType.BorderBlue,
			styles = HbChipStyles.round
		)
	}

}