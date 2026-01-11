package com.hanbang.map.search.component.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Gray4
import com.hanbang.designsystem.theme.SattoTheme

/**
 * @author   JGeun
 * @created  2025/12/16
 */
@Composable
fun SearchHintBox(
	title: String,
	message: String,
	modifier: Modifier = Modifier,
) {
	Box(
		modifier = modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		Column(horizontalAlignment = Alignment.CenterHorizontally) {
			Image(
				painter = painterResource(com.hanbang.designsystem.R.drawable.img_search_hat),
				contentDescription = null
			)
			Text(
				text = title,
				style = SattoTheme.typography.body16Semibold.copy(color = Gray4)
			)
			if (message.isNotEmpty()) {
				Spacer(modifier = Modifier.height(8.dp))
				Text(
					text = message,
					style = SattoTheme.typography.body14Regular.copy(color = Gray4),
					textAlign = TextAlign.Center
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun SearchHintBoxPreview() {
	SattoTheme {
		SearchHintBox(
			title = "어느 장소를 찾으세요?",
			message = "정확한 지명(구/동) 혹은\n상호명을 입력해 보시게",
		)
	}
}