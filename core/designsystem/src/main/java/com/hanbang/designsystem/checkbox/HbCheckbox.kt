package com.hanbang.designsystem.checkbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.util.clickableSingle
import com.hanbang.designsystem.util.noRippleClickable

/**
 *
 * @author   JGeun
 * @created  2025/07/26
 */
@Composable
fun HbCheckbox(
	text: String,
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	isActive: Boolean = false,
	isEnabled: Boolean = false,
	textStyle: TextStyle = TextStyle.Default,
) {
	val buttonRes = when (Pair(isActive, isEnabled)) {
		Pair(true, true) -> R.drawable.img_checkbox_active_default
		Pair(true, false) -> R.drawable.img_checkbox_active_disabled
		Pair(false, true) -> R.drawable.img_checkbox_inactive_default
		else -> R.drawable.img_checkbox_inacive_disabled
	}

	Row(
		modifier = modifier
			.noRippleClickable { onClick() },
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Image(
			modifier = Modifier.size(20.dp),
			painter = painterResource(buttonRes),
			contentDescription = ""
		)

		Text(
			text = text,
			style = textStyle
		)
	}
}

@Preview
@Composable
private fun HbCheckboxPreview() {

}