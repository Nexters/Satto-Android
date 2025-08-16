package com.hanbang.fortune.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Gray2
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.R
import com.hanbang.designsystem.extension.getHbChipColors
import com.hanbang.designsystem.theme.Gray3
import com.hanbang.designsystem.theme.Gray6
import com.hanbang.domain.model.PillarElement
import com.hanbang.fortune.model.PillarDetailUiModel

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
internal fun FortuneSajuOverview(
	name: String,
	dateOfBirth: String,
	birthTime: String,
	timePillarDetail: PillarDetailUiModel,
	dayPillarDetail: PillarDetailUiModel,
	monthPillarDetail: PillarDetailUiModel,
	yearPillarDetail: PillarDetailUiModel,
	strongElement: String,
	weakElement: String,
	elementDescription: String,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier.fillMaxWidth()
	) {
		Text(
			text = "한 눈에 보는 사주",
			style = SattoTheme.typography.body18Bold,
			color = Gray2
		)

		Spacer(Modifier.height(4.dp))

		Row(
			modifier = Modifier.fillMaxWidth()
		) {
			Text(
				text = getNameWithHonorific(name),
				style = SattoTheme.typography.caption12Medium,
				color = Gray3
			)

			Icon(
				modifier = Modifier.size(3.dp),
				painter = painterResource(R.drawable.ic_dot),
				contentDescription = "dot",
				tint = Gray6
			)

			Text(
				text = dateOfBirth,
				style = SattoTheme.typography.caption12Medium,
				color = Gray3
			)

			Icon(
				modifier = Modifier.size(3.dp),
				painter = painterResource(R.drawable.ic_dot),
				contentDescription = "dot",
				tint = Gray6
			)

			Text(
				text = birthTime,
				style = SattoTheme.typography.caption12Medium,
				color = Gray3
			)
		}

		Spacer(Modifier.height(16.dp))

		FortuneSajuTable(
			timePillarDetail = timePillarDetail,
			dayPillarDetail = dayPillarDetail,
			monthPillarDetail = monthPillarDetail,
			yearPillarDetail = yearPillarDetail
		)

		Spacer(Modifier.height(10.dp))

		FortuneTojeongView(
			strongElement = strongElement,
			strongElementChipColor = PillarElement.parsePillarElementInString(strongElement).getHbChipColors(),
			weakElement = weakElement,
			weakElementChipColor = PillarElement.parsePillarElementInString(weakElement).getHbChipColors(),
			elementDescription = elementDescription,
		)
	}
}

private fun getNameWithHonorific(name: String): String {
	return if (name.isNotEmpty()) {
		"${name}님"
	} else {
		""
	}
}

@Preview
@Composable
private fun FortuneSajuOverviewPreview() {
	SattoTheme {
		FortuneSajuOverview(
			name = "콩떡님",
			dateOfBirth = "2025-08-10",
			birthTime = "10:00",
			timePillarDetail = PillarDetailUiModel(),
			dayPillarDetail = PillarDetailUiModel(),
			monthPillarDetail = PillarDetailUiModel(),
			yearPillarDetail = PillarDetailUiModel(),
			strongElement = "",
			weakElement = "",
			elementDescription = "",
			modifier = Modifier
		)
	}
}