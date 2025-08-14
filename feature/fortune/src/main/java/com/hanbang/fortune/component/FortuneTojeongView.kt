package com.hanbang.fortune.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.chip.HbChip
import com.hanbang.designsystem.chip.HbChipColorType
import com.hanbang.designsystem.chip.HbChipStyles
import com.hanbang.designsystem.chip.model.HbChipColors
import com.hanbang.designsystem.chip.model.HbChipStyleProperties
import com.hanbang.designsystem.theme.Gray4
import com.hanbang.designsystem.theme.Gray8
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
internal fun FortuneTojeongView(
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier.fillMaxWidth()
			.background(color = White, shape = RoundedCornerShape(10.dp))
			.padding(top = 10.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)
	) {
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = "2025년 토정비결",
			style = SattoTheme.typography.body14Semibold,
			color = Gray4,
			textAlign = TextAlign.Center
		)

		Spacer(Modifier.height(8.dp))

		Spacer(
			modifier = Modifier.fillMaxWidth()
				.height(1.dp)
				.background(color = Gray8, shape = RoundedCornerShape(1.dp))
		)

		Spacer(Modifier.height(8.dp))

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			HbChip(
				text = "금",
				onClick = {},
				colors = HbChipColorType.SolidGray,
				styles = HbChipStyles.round
			)

			Spacer(Modifier.width(2.dp))

			HbChip(
				text = "수",
				onClick = {},
				colors = HbChipColorType.SolidBlue,
				styles = HbChipStyles.round
			)

			Spacer(Modifier.width(2.dp))

			HbChip(
				text = "토",
				onClick = {},
				colors = HbChipColorType.SolidYellow,
				styles = HbChipStyles.round
			)

			Spacer(Modifier.width(2.dp))

			HbChip(
				text = "화",
				onClick = {},
				colors = HbChipColorType.SolidRed,
				styles = HbChipStyles.round
			)
		}

		Spacer(Modifier.height(8.dp))

		Text(
			modifier = Modifier.fillMaxWidth(),
			text = "근심과 즐거움이 상반하니\n세월의 흐름을 잘 읽어보세요",
			style = SattoTheme.typography.body14Regular,
			color = Gray4,
			textAlign = TextAlign.Center
		)
	}
}

@Preview
@Composable
private fun FortuneTojeongViewPreview() {
	SattoTheme {
		FortuneTojeongView()
	}
}