package com.hanbang.map.search.component.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.input.HbInputField
import com.hanbang.designsystem.theme.Black
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.util.noRippleClickable

/**
 * @author   JGeun
 * @created  2025/12/16
 */
@Composable
internal fun MapSearchInputField(
	keyword: String,
	onKeywordChange: (String) -> Unit,
	onClearKeyword: () -> Unit,
	onSearchAction: () -> Unit = {},
	focusRequester: FocusRequester = remember { FocusRequester() },
	modifier: Modifier = Modifier,
) {
	HbInputField(
		modifier = modifier.fillMaxWidth(),
		value = keyword,
		hint = "우리 지역 명소 찾기",
		onValueChange = onKeywordChange,
		textColor = Black,
		hintTextStyle = TextStyle(
			color = Gray5,
			fontWeight = FontWeight.W500,
			fontSize = SattoTheme.typography.body14Medium.fontSize,
		),
		textStyle = SattoTheme.typography.body14Medium,
		focusRequester = focusRequester,
		keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
		keyboardActions = KeyboardActions(onSearch = { onSearchAction() }),
		leadingIcon = {
			Icon(
				modifier = Modifier.size(16.dp),
				painter = painterResource(R.drawable.ic_search),
				contentDescription = "Search",
				tint = Primary2
			)
		},
		trailingIcon = if (keyword.isNotEmpty()) {
			{
				Icon(
					modifier = Modifier
						.size(16.dp)
						.noRippleClickable { onClearKeyword() },
					painter = painterResource(R.drawable.ic_close),
					contentDescription = "Clear",
					tint = Gray5
				)
			}
		} else null
	)
}

@Preview
@Composable
private fun MapSearchInputFieldPreview() {
	var keyword = "Sample Keyword"
	SattoTheme {
		MapSearchInputField(
			keyword = keyword,
			onKeywordChange = { keyword = it },
			onClearKeyword = { keyword = "" }
		)
	}
}