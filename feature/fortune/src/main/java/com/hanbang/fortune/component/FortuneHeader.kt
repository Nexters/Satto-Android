package com.hanbang.fortune.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R

/**
 *
 * @author   JGeun
 * @created  2025/08/09
 */
@Composable
internal fun FortuneHeader(
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier.fillMaxWidth()
			.padding(vertical = 18.dp, horizontal = 24.dp)
	) {
		Image(
			modifier = Modifier.width(74.dp).height(20.dp),
			painter = painterResource(R.drawable.img_satto_logo),
			contentDescription = "satto_logo",
		)
	}
}

@Preview
@Composable
private fun FortuneHeaderPreview() {
	FortuneHeader(
		modifier = Modifier.fillMaxWidth()
			.padding(horizontal = 24.dp)
			.height(56.dp)
	)
}