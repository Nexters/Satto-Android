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
import com.hanbang.designsystem.extension.getColor
import com.hanbang.designsystem.theme.Blue4
import com.hanbang.designsystem.theme.Gray6
import com.hanbang.designsystem.theme.Gray7
import com.hanbang.designsystem.theme.Red4
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.theme.Yellow4
import com.hanbang.fortune.model.PillarDetailUiModel

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
internal fun FortuneSajuTable(
	timePillarDetail: PillarDetailUiModel,
	dayPillarDetail: PillarDetailUiModel,
	monthPillarDetail: PillarDetailUiModel,
	yearPillarDetail: PillarDetailUiModel,
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
					text = timePillarDetail.stemTenGod,
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
					text = dayPillarDetail.stemTenGod,
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
					text = monthPillarDetail.stemTenGod,
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
					text = yearPillarDetail.stemTenGod,
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
				pillar = timePillarDetail.stem.hanja,
				bgColor = timePillarDetail.stem.getColor()
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = dayPillarDetail.stem.hanja,
				bgColor = dayPillarDetail.stem.getColor()
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = monthPillarDetail.stem.hanja,
				bgColor = monthPillarDetail.stem.getColor()
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = yearPillarDetail.stem.hanja,
				bgColor = yearPillarDetail.stem.getColor()
			)
		}

		Row (
			modifier = Modifier.fillMaxWidth()
				.background(color = Gray7)
		) {
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = timePillarDetail.branch.hanja,
				bgColor = timePillarDetail.branch.getColor()
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = dayPillarDetail.branch.hanja,
				bgColor = dayPillarDetail.branch.getColor()
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = monthPillarDetail.branch.hanja,
				bgColor = monthPillarDetail.branch.getColor()
			)
			SajuPillarTextBox(
				modifier = Modifier.weight(1f),
				pillar = yearPillarDetail.branch.hanja,
				bgColor = yearPillarDetail.branch.getColor()
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
					text = timePillarDetail.branchTenGod,
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
					text = dayPillarDetail.branchTenGod,
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
					text = monthPillarDetail.branchTenGod,
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
					text = yearPillarDetail.branchTenGod,
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
			timePillarDetail = PillarDetailUiModel(),
			dayPillarDetail = PillarDetailUiModel(),
			monthPillarDetail = PillarDetailUiModel(),
			yearPillarDetail = PillarDetailUiModel(),
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