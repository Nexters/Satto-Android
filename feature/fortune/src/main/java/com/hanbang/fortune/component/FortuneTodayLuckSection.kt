package com.hanbang.fortune.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.chip.HbChip
import com.hanbang.designsystem.chip.HbChipColorType
import com.hanbang.designsystem.chip.HbChipStyles
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.R

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
internal fun FortuneTodayLuckSection(
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier.fillMaxWidth()
			.padding(vertical = 4.dp, horizontal = 8.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		HbChip(
			text = "7월 18일 운세",
			onClick = {},
			colors = HbChipColorType.SolidPrimary,
			styles = HbChipStyles.round
		)

		Spacer(Modifier.height(12.dp))

		Text(
			text = "70점",
			style = SattoTheme.typography.display28Bold,
			color = Gray1
		)

		Spacer(Modifier.height(20.dp))

		Image(
			modifier = Modifier
				.width(148.dp)
				.height(74.dp),
			painter = painterResource(R.drawable.img_my_page_satto),
			contentDescription = null
		)

		Spacer(Modifier.height(18.dp))

		Text(
			text = "좋은 기운이 문을 두드리고 있소",
			style = SattoTheme.typography.body16Medium,
			color = Gray1
		)
	}
}

@Preview
@Composable
private fun FortuneTodayLuckSectionPreview() {
	SattoTheme {
		FortuneTodayLuckSection()
	}
}