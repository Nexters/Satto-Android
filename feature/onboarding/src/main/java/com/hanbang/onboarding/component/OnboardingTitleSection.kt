package com.hanbang.onboarding.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.SattoTheme

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun OnboardingTitleSection() {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp)
	) {
		Text(
			text = "정보를 입력해 주세요",
			style = SattoTheme.typography.headline22Bold
		)

		Spacer(Modifier.height(6.dp))

		Text(
			text = "회원님의 사주를 기반으로 로또 번호를 추천해드려요",
			style = SattoTheme.typography.body14Medium
		)
	}
}

@Preview
@Composable
private fun OnboardingTitleSectionPreview() {
	SattoTheme {
		OnboardingTitleSection()
	}
}
