package com.hanbang.fortune.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.chip.HbChip
import com.hanbang.designsystem.chip.HbChipColorType
import com.hanbang.designsystem.chip.HbChipStyles
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.SattoTheme

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
internal fun FortuneTodayLuckSection(
	month: Int,
	day: Int,
	score: Int,
	comment: String,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier.fillMaxWidth()
			.padding(vertical = 4.dp, horizontal = 8.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		HbChip(
			text = "${month}월 ${day}일 운세",
			onClick = {},
			colors = HbChipColorType.SolidPrimary,
			styles = HbChipStyles.round
		)

		Spacer(Modifier.height(12.dp))

		Text(
			text = "${score}점",
			style = SattoTheme.typography.display28Bold,
			color = Gray1
		)

		Spacer(Modifier.height(20.dp))

		BoxWithConstraints(
			modifier = Modifier.fillMaxWidth()
		) {
			FortuneLuckArc(
				modifier = Modifier
					.align(Alignment.Center)
					.width(maxWidth - (maxWidth * 220 / 375))
					.aspectRatio(2f),
				value = score.coerceAtMost(100) / 100f
			)
		}

		Spacer(Modifier.height(18.dp))

		Text(
			text = comment,
			style = SattoTheme.typography.body16Medium,
			color = Gray1
		)
	}
}

@Preview
@Composable
private fun FortuneTodayLuckSectionPreview() {
	SattoTheme {
		FortuneTodayLuckSection(
			month = 7,
			day = 18,
			score = 70,
			comment = "좋은 기운이 문을 두드리고 있소"
		)
	}
}