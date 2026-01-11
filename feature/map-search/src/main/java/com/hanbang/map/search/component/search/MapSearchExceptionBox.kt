package com.hanbang.map.search.component.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Gray2
import com.hanbang.designsystem.theme.Gray4
import com.hanbang.designsystem.theme.SattoTheme

/**
 * @author   JGeun
 * @created  2026/01/11
 */
@Composable
fun MapSearchExceptionBox(
	title: String,
	message: String,
	modifier: Modifier = Modifier
) {
	val screenHeight = LocalWindowInfo.current.containerSize.height
	val calculatedHeight = (160f * screenHeight) / 375f

	Column(
		modifier = modifier.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Spacer(modifier = Modifier.height(calculatedHeight.dp))

		Image(
			modifier = Modifier.size(100.dp),
			painter = painterResource(R.drawable.img_satto_hat_gray),
			contentDescription = ""
		)

		Spacer(Modifier.height(16.dp))

		Text(
			text = title,
			style = SattoTheme.typography.body16Semibold,
			color = Gray2
		)

		Spacer(Modifier.height(8.dp))

		Text(
			text = message,
			style = SattoTheme.typography.caption12Medium,
			color = Gray4
		)
	}
}
