package com.hanbang.mypage.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.SattoTheme

/**
 *
 * @author   JGeun
 * @created  2025/08/05
 */
@Composable
fun MyPageHeader(
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier.fillMaxWidth()
			.height(56.dp)
	) {
		Text(
			modifier = Modifier.wrapContentSize()
				.align(Alignment.Center),
			text = "마이",
			style = SattoTheme.typography.body16Bold,
			color = Gray1
		)
	}
}

@Preview
@Composable
private fun MyPageHeaderPreview() {
	SattoTheme {
		MyPageHeader(
			modifier = Modifier.fillMaxWidth()
		)
	}
}