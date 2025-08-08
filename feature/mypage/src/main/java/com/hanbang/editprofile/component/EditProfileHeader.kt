package com.hanbang.editprofile.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
@Composable
internal fun EditProfileHeader(
	navigateUp: () -> Unit,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Icon(
			modifier = Modifier
				.align(Alignment.CenterStart)
				.size(24.dp)
				.clickableSingle(activeRippleEffect = false) { navigateUp() },
			painter = painterResource(R.drawable.ic_arrow_left),
			contentDescription = "left arrow",
		)

		Text(
			modifier = Modifier.align(Alignment.Center),
			text = "프로필 수정",
			style = SattoTheme.typography.body16Bold,
			color = Gray1
		)
	}
}

@Preview
@Composable
private fun EditProfileHeaderPreview() {
	SattoTheme {
		EditProfileHeader(
			navigateUp = { }
		)
	}
}