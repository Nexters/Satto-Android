package com.hanbang.onboarding.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.radiobutton.HbRadioButton
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.LocalSattoTypography
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.domain.model.GenderType

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun OnboardingGenderContent(
	genderType: GenderType,
	onGenderSelected: (GenderType) -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Text(
			text = "성별",
			style = LocalSattoTypography.current.body16Bold,
			color = Gray1
		)

		Row(
			horizontalArrangement = Arrangement.spacedBy(20.dp)
		) {
			HbRadioButton(
				text = "남성",
				onClick = { onGenderSelected(GenderType.MALE) },
				isActive = genderType == GenderType.MALE,
				isEnabled = true
			)

			HbRadioButton(
				text = "여성",
				onClick = { onGenderSelected(GenderType.FEMALE) },
				isActive = genderType == GenderType.FEMALE,
				isEnabled = true
			)
		}
	}
}

@Preview
@Composable
private fun OnboardingGenderContentPreview() {
	SattoTheme {
		OnboardingGenderContent(
			genderType = GenderType.MALE,
			onGenderSelected = {}
		)

	}
}