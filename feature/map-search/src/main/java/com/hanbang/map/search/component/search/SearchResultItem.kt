package com.hanbang.map.search.component.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Gray3
import com.hanbang.designsystem.theme.Gray4
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.Primary8
import com.hanbang.designsystem.theme.SattoTheme

/**
 * @author   JGeun
 * @created  2025/12/16
 */
@Composable
fun SearchResultItem(
	title: String,
	address: String,
	searchQuery: String = "",
	modifier: Modifier = Modifier,
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(vertical = 23.dp, horizontal = 24.dp),
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		SearchResultItemPositionIconBox()

		SearchResultItemTexts(
			title = title,
			address = address,
			searchQuery = searchQuery,
			modifier = Modifier.weight(1f)
		)

		Icon(
			modifier = Modifier.size(24.dp),
			painter = painterResource(R.drawable.ic_chevron_right_16),
			contentDescription = "chevron_right_icon",
			tint = Gray4
		)
	}
}

@Composable
private fun SearchResultItemPositionIconBox(
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
			.size(32.dp)
			.background(color = Primary8, shape = CircleShape)
			.padding(8.dp)
	) {
		Icon(
			modifier = Modifier.size(16.dp),
			painter = painterResource(R.drawable.ic_map_pin),
			contentDescription = "position_icon",
			tint = Primary2
		)
	}
}

@Composable
private fun SearchResultItemTexts(
	title: String,
	address: String,
	searchQuery: String,
	modifier: Modifier = Modifier
) {
	val annotatedTitle = remember(title, searchQuery) {
		buildAnnotatedString {
			if (searchQuery.isBlank()) {
				append(title)
			} else {
				val lowerTitle = title.lowercase()
				val lowerQuery = searchQuery.lowercase()
				var currentIndex = 0
				while (currentIndex < title.length) {
					val matchIndex = lowerTitle.indexOf(lowerQuery, currentIndex)
					if (matchIndex == -1) {
						append(title.substring(currentIndex))
						break
					}
					if (matchIndex > currentIndex) {
						append(title.substring(currentIndex, matchIndex))
					}
					withStyle(SpanStyle(color = Primary2)) {
						append(title.substring(matchIndex, matchIndex + searchQuery.length))
					}
					currentIndex = matchIndex + searchQuery.length
				}
			}
		}
	}

	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.spacedBy(4.dp)
	) {
		Text(
			text = annotatedTitle,
			style = SattoTheme.typography.body16Semibold,
			color = Gray1
		)

		Text(
			text = address,
			style = SattoTheme.typography.caption12Medium,
			color = Gray3
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun SearchResultItemPreview() {
	SattoTheme {
		SearchResultItem(
			title = "한방병원",
			address = "서울특별시 강남구 테헤란로 123",
			searchQuery = "한방"
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun SearchResultItemPositionIconBoxPreview() {
	SattoTheme {
		SearchResultItemPositionIconBox()
	}
}

@Preview(showBackground = true)
@Composable
private fun SearchResultItemTextsPreview() {
	SattoTheme {
		Column {
			SearchResultItemTexts(
				title = "한방병원",
				address = "서울특별시 강남구 테헤란로 123",
				searchQuery = "한방"
			)

			SearchResultItemTexts(
				title = "한방병원",
				address = "서울특별시 강남구 테헤란로 123",
				searchQuery = ""
			)
		}
	}
}