package com.hanbang.fortune.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Black
import com.hanbang.designsystem.theme.Gray2
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.White

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
internal fun FortuneComprehensiveSection(
	modifier: Modifier = Modifier
) {
	Column(modifier = modifier.fillMaxWidth()) {
		Text(
			text  = "종합 운세",
			style = SattoTheme.typography.body18Bold,
			color = Gray2
		)

		Spacer(Modifier.height(16.dp))

		LazyRow(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(10.dp)
		) {
			item {
				FortuneComprehensiveCard(
					text = "재물운",
					message = "적은 노력 뚜렷한 성과",
					imageRes = R.drawable.img_my_page_satto,
				)
			}

			item {
				FortuneComprehensiveCard(
					text = "재물운",
					message = "적은 노력 뚜렷한 성과",
					imageRes = R.drawable.img_my_page_satto,
				)
			}

			item {
				FortuneComprehensiveCard(
					text = "재물운",
					message = "적은 노력 뚜렷한 성과",
					imageRes = R.drawable.img_my_page_satto,
				)
			}

			item {
				FortuneComprehensiveCard(
					text = "재물운",
					message = "적은 노력 뚜렷한 성과",
					imageRes = R.drawable.img_my_page_satto,
				)
			}

			item {
				FortuneComprehensiveCard(
					text = "재물운",
					message = "적은 노력 뚜렷한 성과",
					imageRes = R.drawable.img_my_page_satto,
				)
			}
		}
	}
}

@Composable
private fun FortuneComprehensiveCard(
	text: String,
	message: String,
	@DrawableRes imageRes: Int,
	modifier: Modifier = Modifier
) {

	Box(
		modifier = modifier
			.width(130.dp)
			.height(140.dp)
			.background(color = White, shape = RoundedCornerShape(10.dp))
			.padding(12.dp)
	) {
		Column(
			modifier = Modifier.align(Alignment.TopStart)
		) {
			Text(
				text = text,
				style = SattoTheme.typography.body16Semibold,
				color = Black
			)

			Text(
				text = message,
				style = SattoTheme.typography.caption12Medium,
				color = Gray2
			)

			Spacer(Modifier.height(8.dp))
		}

		Image(
			painter = painterResource(imageRes),
			contentDescription = "fortune_image",
			modifier = Modifier
				.align(Alignment.BottomEnd)
				.size(64.dp)
		)
	}
}

@Preview
@Composable
private fun FortuneComprehensiveSectionPreview() {
	SattoTheme {
		FortuneComprehensiveSection()
	}
}