package com.hanbang.onboarding.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun OnboardingHeader(
	navigateUp: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Icon(
			modifier = Modifier
				.size(24.dp)
				.clickableSingle(activeRippleEffect = false) { navigateUp() },
			painter = painterResource(R.drawable.ic_arrow_left),
			contentDescription = "left arrow",
		)
	}
}

@Preview
@Composable
private fun OnboardingHeaderPreview() {
	SattoTheme {
		OnboardingHeader(navigateUp = {})
	}
}
