package com.hanbang.designsystem.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.Primary9
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/08/02
 */
@Composable
fun HbBottomSheetItem(
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	isActive: Boolean = true,
) {
	Box(
		modifier = modifier
			.fillMaxWidth()
			.height(56.dp)
			.background(
				color = if (isActive) Primary9 else White,
				shape = RoundedCornerShape(10.dp)
			)
			.clickableSingle { onClick() }
			.padding(horizontal = 16.dp)
	) {
		Text(
			modifier = Modifier.align(Alignment.Center),
			text = text,
			style = SattoTheme.typography.body16Bold,
			color = if (isActive) Primary2 else Gray5
		)
	}
}

@Preview
@Composable
private fun HbBottomSheetItemPreview() {
	SattoTheme {
		Column {
			HbBottomSheetItem(
				text = "Atom",
				isActive = true,
				onClick = {}
			)

			HbBottomSheetItem(
				text = "Atom",
				isActive = false,
				onClick = {}
			)
		}

	}
}