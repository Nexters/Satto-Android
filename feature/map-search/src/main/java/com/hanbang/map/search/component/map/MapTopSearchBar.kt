package com.hanbang.map.search.component.map

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.radius.HbRadius
import com.hanbang.designsystem.theme.Black
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White

/**
 * @author   JGeun
 * @created  2026/01/04
 */
@Composable
fun MapTopSearchBar(
	searchQuery: String,
	modifier: Modifier = Modifier
) {
	Surface(
		modifier = modifier
			.border(
				width = 1.dp,
				color = White,
				shape = RoundedCornerShape(HbRadius.Radius6)
			),
		elevation = 2.dp
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 14.dp, vertical = 11.dp),
			horizontalArrangement = Arrangement.spacedBy(8.dp)
		) {
			Icon(
				painter = painterResource(R.drawable.ic_search),
				contentDescription = "position_icon",
				tint = Primary2
			)

			if (searchQuery.isNotEmpty()) {
				Text(
					text = "우리 지역 명소 찾기",
					style= SattoTheme.typography.body14Medium,
					color = Black
				)
			} else {
				// Placeholder
				Text(
					text = "우리 지역 명소 찾기",
					style= SattoTheme.typography.body14Medium,
					color = Gray5
				)
			}

		}
	}
}

@Preview
@Composable
private fun MapSearchNavigationBarPreview() {
	SattoTheme {
		Column {
			MapTopSearchBar(
				searchQuery = "asdasdasd"
			)
			MapTopSearchBar(
				searchQuery = ""
			)
		}
	}
}