package com.hanbang.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.checkbox.HbCheckbox
import com.hanbang.designsystem.theme.Black
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Gray7
import com.hanbang.designsystem.theme.LocalSattoTypography
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.util.clickableSingle
import com.hanbang.onboarding.model.OnboardingState

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun OnboardingBirthTimePicker(
	birthTime: String,
	userBirthTimeUnknown: Boolean,
	onClickBirthTimePicker: () -> Unit,
	onToggleUserBirthTimeUnknown: () -> Unit,
) {
	val birthTimepickerBgColor = if (userBirthTimeUnknown) {
		Gray7
	} else {
		White
	}

	val birthTimepickerBorderWidth = if (userBirthTimeUnknown) {
		0.dp
	} else {
		1.dp
	}

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp),
	) {
		Text(
			text = "태어난 시",
			style = LocalSattoTypography.current.body16Bold,
			color = Gray1
		)

		Spacer(Modifier.height(8.dp))

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(
					color = birthTimepickerBgColor,
					shape = RoundedCornerShape(8.dp)
				)
				.border(
					width = birthTimepickerBorderWidth,
					color = Gray7,
					shape = RoundedCornerShape(8.dp)
				)
				.clickableSingle(activeRippleEffect = false) {
					onClickBirthTimePicker()
				}
				.padding(horizontal = 14.dp, vertical = 11.dp)
		) {
			Text(
				text = if (userBirthTimeUnknown) {
					"입력하지 않아도 괜찮아요"
				} else if (birthTime.isEmpty()) {
					"23:00 ~ 00:59"
				} else {
					birthTime
				},
				style = LocalSattoTypography.current.body14Semibold,
				color = if (userBirthTimeUnknown || birthTime.isEmpty()) {
					Gray5
				} else {
					Black
				}
			)
		}

		Spacer(Modifier.height(12.dp))

		HbCheckbox(
			text = "모르겠어요",
			isActive = userBirthTimeUnknown,
			isEnabled = true,
			onClick = { onToggleUserBirthTimeUnknown() },
		)
	}
}

@Preview
@Composable
private  fun OnboardingBirthTimePickerPreview() {
	SattoTheme {
		OnboardingBirthTimePicker(
			birthTime = "",
			userBirthTimeUnknown = false,
			onClickBirthTimePicker = {},
			onToggleUserBirthTimeUnknown = {}
		)
	}
}