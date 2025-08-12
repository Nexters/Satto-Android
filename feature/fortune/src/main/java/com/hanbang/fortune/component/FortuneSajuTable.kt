package com.hanbang.fortune.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Blue4
import com.hanbang.designsystem.theme.Gray6
import com.hanbang.designsystem.theme.Gray7
import com.hanbang.designsystem.theme.Red4
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.theme.Yellow4

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
internal fun FortuneSajuTable(
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier.fillMaxWidth()
			.background(color = White, shape = RoundedCornerShape(10.dp))
	) {
		Row (
			modifier = Modifier.fillMaxWidth()
				.background(color = White, shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
			verticalAlignment = Alignment.CenterVertically
		) {
			Box(
				modifier = Modifier.weight(1f)
					.aspectRatio(81.75f/44)
					.padding(vertical = 10.dp, horizontal = 12.dp)
			) {
				Text(
					modifier = Modifier.align(Alignment.Center),
					text = "시주",
					style = SattoTheme.typography.body16Semibold,
					textAlign = TextAlign.Center
				)
			}

			Box(
				modifier = Modifier.weight(1f)
					.aspectRatio(81.75f/44)
			) {
				Spacer(
					modifier = Modifier
						.align(Alignment.CenterStart)
						.width(1.dp)
						.fillMaxHeight()
						.padding(vertical = 10.dp)
						.background(color = Gray7, shape = RoundedCornerShape(1.dp))
				)

				Text(
					modifier = Modifier
						.padding(vertical = 10.dp, horizontal = 12.dp).align(Alignment.Center),
					text = "일주",
					style = SattoTheme.typography.body16Semibold,
					textAlign = TextAlign.Center
				)
			}
			Box(
				modifier = Modifier.weight(1f)
					.aspectRatio(81.75f/44)
			) {
				Spacer(
					modifier = Modifier
						.align(Alignment.CenterStart)
						.width(1.dp)
						.fillMaxHeight()
						.padding(vertical = 10.dp)
						.background(color = Gray7, shape = RoundedCornerShape(1.dp))
				)

				Text(
					modifier = Modifier
						.padding(vertical = 10.dp, horizontal = 12.dp).align(Alignment.Center),
					text = "월주",
					style = SattoTheme.typography.body16Semibold,
					textAlign = TextAlign.Center
				)
			}
			Box(
				modifier = Modifier.weight(1f)
					.aspectRatio(81.75f/44)
			) {
				Spacer(
					modifier = Modifier
						.align(Alignment.CenterStart)
						.width(1.dp)
						.fillMaxHeight()
						.padding(vertical = 10.dp)
						.background(color = Gray7, shape = RoundedCornerShape(1.dp))
				)

				Text(
					modifier = Modifier
						.padding(vertical = 10.dp, horizontal = 12.dp).align(Alignment.Center),
					text = "년주",
					style = SattoTheme.typography.body16Semibold,
					textAlign = TextAlign.Center
				)
			}
		}

		Row (
			modifier = Modifier.fillMaxWidth()
				.background(color = Gray7)
		) {
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = "辛",
				bgColor = Blue4
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = "辛",
				bgColor = Red4
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = "辛",
				bgColor = Gray6
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = "辛",
				bgColor = Gray6
			)
		}

		Row (
			modifier = Modifier.fillMaxWidth()
				.background(color = Gray7)
		) {
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = "辛",
				bgColor = Blue4
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = "辛",
				bgColor = Gray6
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = "辛",
				bgColor = Yellow4
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = "辛",
				bgColor = Yellow4
			)
		}

		Row (
			modifier = Modifier.fillMaxWidth()
				.background(color = White, shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
			verticalAlignment = Alignment.CenterVertically
		) {
			Box(
				modifier = Modifier.weight(1f)
					.aspectRatio(81.75f/44)
					.padding(vertical = 10.dp, horizontal = 12.dp),
			) {
				Text(
					modifier = Modifier.align(Alignment.Center),
					text = "정재",
					style = SattoTheme.typography.body16Semibold,
					textAlign = TextAlign.Center
				)
			}

			Box(
				modifier = Modifier.weight(1f)
					.aspectRatio(81.75f/44)
			) {
				Spacer(
					modifier = Modifier
						.align(Alignment.CenterStart)
						.width(1.dp)
						.fillMaxHeight()
						.padding(vertical = 10.dp)
						.background(color = Gray7, shape = RoundedCornerShape(1.dp))
				)

				Text(
					modifier = Modifier
						.padding(vertical = 10.dp, horizontal = 12.dp).align(Alignment.Center),
					text = "겁재",
					style = SattoTheme.typography.body16Semibold,
					textAlign = TextAlign.Center
				)
			}

			Box(
				modifier = Modifier.weight(1f)
					.aspectRatio(81.75f/44)
			) {
				Spacer(
					modifier = Modifier
						.align(Alignment.CenterStart)
						.width(1.dp)
						.fillMaxHeight()
						.padding(vertical = 10.dp)
						.background(color = Gray7, shape = RoundedCornerShape(1.dp))
				)

				Text(
					modifier = Modifier
						.padding(vertical = 10.dp, horizontal = 12.dp).align(Alignment.Center),
					text = "정재",
					style = SattoTheme.typography.body16Semibold,
					textAlign = TextAlign.Center
				)
			}

			Box(
				modifier = Modifier.weight(1f)
					.aspectRatio(81.75f/44)
			) {
				Spacer(
					modifier = Modifier
						.align(Alignment.CenterStart)
						.width(1.dp)
						.fillMaxHeight()
						.padding(vertical = 10.dp)
						.background(color = Gray7, shape = RoundedCornerShape(1.dp))
				)

				Text(
					modifier = Modifier
						.padding(vertical = 10.dp, horizontal = 12.dp).align(Alignment.Center),
					text = "겁재",
					style = SattoTheme.typography.body16Semibold,
					textAlign = TextAlign.Center
				)
			}
		}
	}
}

@Composable
private fun SajuPillarTextBox(
	pillar: String,
	bgColor: Color,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier.fillMaxWidth()
			.aspectRatio(1f)
			.padding(1.dp)
			.background(color = bgColor)
	) {
		Text(
			modifier = Modifier.align(Alignment.Center),
			text = pillar,
			style = SattoTheme.typography.display28Bold,
			color = White,
		)
	}
}

@Preview
@Composable
private fun FortuneSajuTablePreview() {
	SattoTheme {
		FortuneSajuTable(
			modifier = Modifier
				.padding(16.dp)
				.fillMaxWidth()
		)
	}
}

@Preview
@Composable
private fun SajuPillarTextBoxPreview() {
	SattoTheme {
		SajuPillarTextBox(
			pillar = "辛",
			bgColor = Blue4
		)
	}
}