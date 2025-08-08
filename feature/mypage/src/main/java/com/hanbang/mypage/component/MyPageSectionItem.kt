package com.hanbang.mypage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/08/05
 */
@Composable
fun MyPageSectionItem(
	text: String,
	modifier: Modifier = Modifier,
	onClick: () -> Unit = {},
	rightComponent: @Composable () -> Unit = {}
) {
	Row(
		modifier = modifier.fillMaxWidth()
			.clip(RoundedCornerShape(10.dp))
			.clickableSingle { onClick()  }
			.padding(vertical = 12.dp, horizontal = 10.dp),
		horizontalArrangement = Arrangement.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			modifier = Modifier.weight(1f),
			text = text,
			style = SattoTheme.typography.body14Medium,
			color = Gray1
		)

		rightComponent()
	}
}

@Preview
@Composable
private fun MyPageSectionItemPreview() {
	SattoTheme {
		MyPageSectionItem(
			text = "내 정보",
			modifier = Modifier.padding(horizontal = 16.dp),
			rightComponent = {
				Text(
					text = "수정",
					style = SattoTheme.typography.body14Medium,
					color = Gray1
				)
			}
		)
	}
}
