package com.hanbang.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.Primary8
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/08/05
 */
@Composable
fun MyPageFeedbackBox(
	modifier: Modifier = Modifier,
	bgColor: Color = White,
	bgShape: Shape = RoundedCornerShape(10.dp),
	onClick: () -> Unit = {  }
) {
	Row(
		modifier = modifier.fillMaxWidth()
			.background(color = bgColor, shape = bgShape)
			.clip(bgShape)
			.clickableSingle { onClick() }
			.padding(20.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Column(
			modifier = Modifier.weight(1f),
			verticalArrangement = Arrangement.spacedBy(12.dp)
		) {
			Text(
				text = "더 나은 서비스를 위해, 여러분의 목소리를 들려주세요",
				style = SattoTheme.typography.body14Semibold,
				color = Gray1
			)

			Row(
				modifier = Modifier
					.background(color = Primary8, shape = RoundedCornerShape(6.dp))
					.padding(vertical = 7.dp, horizontal = 12.dp),
				horizontalArrangement = Arrangement.spacedBy(4.dp)
			) {
				Text(
					text = "의견 보내기",
					style = SattoTheme.typography.caption12Bold,
					color = Primary2
				)

				Icon(
					modifier = Modifier.size(20.dp),
					painter = painterResource(R.drawable.ic_chevron_right_16),
					contentDescription = null,
					tint = Primary2
				)
			}
		}

		Spacer(Modifier.width(14.dp))

		Image(
			modifier = Modifier.size(86.dp),
			painter = painterResource(R.drawable.img_my_page_satto),
			contentDescription = null
		)
	}
}

@Preview
@Composable
private fun MyPageFeedbackBoxPreview() {
	SattoTheme {
		MyPageFeedbackBox(
			modifier = Modifier.padding(horizontal = 16.dp)
		)
	}
}