package com.hanbang.onboarding.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.input.HbInputField
import com.hanbang.designsystem.theme.LocalSattoTypography
import com.hanbang.designsystem.theme.Red3
import com.hanbang.designsystem.theme.SattoTheme

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun OnboardingNamingContent(
	name: String,
	nameInputErrorMsg: String,
	onNameInputChanged: (String) -> Unit,
	validateName: () -> Unit,
	modifier: Modifier = Modifier,
	focusManager: FocusManager = LocalFocusManager.current,
) {
	val keyboardController = LocalSoftwareKeyboardController.current

	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp)
	) {
		Text(
			text = "이름",
			style = SattoTheme.typography.body16Bold
		)

		Spacer(Modifier.height(8.dp))

		HbInputField(
			value = name,
			onValueChange = { onNameInputChanged(it) },
			hint = "김사또",
			isError = nameInputErrorMsg.isNotEmpty(),
			errorMessage = nameInputErrorMsg,
			errorTextStyle = SattoTheme.typography.caption12Medium.copy(
				color = Red3
			),
			modifier = Modifier.fillMaxWidth(),
			textColor = Color.Black,
			textStyle = SattoTheme.typography.body14Medium,
			focusManager = focusManager,
			keyboardController = keyboardController,
			keyboardActions = KeyboardActions(
				onDone = {
					validateName()
					keyboardController?.hide()
					focusManager.clearFocus(true)
				}
			),
		)
	}
}

@Preview
@Composable
private fun OnboardingNamingContentPreview() {
	SattoTheme {
		OnboardingNamingContent(
			name = "",
			nameInputErrorMsg = "",
			onNameInputChanged = {},
			validateName = {}
		)
	}
}