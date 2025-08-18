package com.hanbang.fortune.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Gray2
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Gray5

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
internal fun FortuneLuckInfoMoreSection(
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier.fillMaxWidth()
	) {
		Text(
			text = "더 많은 행운 정보",
			style = SattoTheme.typography.body18Bold,
			color = Gray2
		)

		Spacer(Modifier.height(12.dp))
		
		Column(
			modifier = Modifier.fillMaxWidth()
				.height(252.dp)
				.background(color = White, shape = RoundedCornerShape(10.dp)),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Image(
				painter = painterResource(R.drawable.img_satto_update_wait),
				contentDescription = "satto_update_wait",
			)
			Text(
				text = "행운 정보를 준비 중이에요",
				style = SattoTheme.typography.body14Regular,
				color = Gray5
			)
		}
	}
}

@Preview
@Composable
private fun FortuneLuckInfoMoreSectionPreview() {
	SattoTheme {
		Box(
			modifier = Modifier.fillMaxWidth()
		) {
			FortuneLuckInfoMoreSection()
		}
	}
}