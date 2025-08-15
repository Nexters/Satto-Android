package com.hanbang.onboarding.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.input.HbInputField
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Red3
import com.hanbang.designsystem.theme.SattoTheme

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun OnboardingDateOfBirth(
	dateOfBirth: String,
	dateOfBirthInputErrorMsg: String,
	onDateOfBirthInputChanged: (String) -> Unit,
	validateDateOfBirth: () -> Unit,
	modifier: Modifier = Modifier,
	focusManager: FocusManager = LocalFocusManager.current,
) {
	val keyboardController = LocalSoftwareKeyboardController.current

	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Text(
			text = "생년월일",
			style = SattoTheme.typography.body16Bold,
			color = Gray1
		)

		HbInputField(
			value = dateOfBirth,
			onValueChange = { onDateOfBirthInputChanged(it) },
			hint = "20000101",
			isError = dateOfBirthInputErrorMsg.isNotEmpty(),
			errorMessage = dateOfBirthInputErrorMsg,
			errorTextStyle =  SattoTheme.typography.caption12Medium.copy(
				color = Red3
			),
			counterMaxLength = 8,
			modifier = Modifier.fillMaxWidth(),
			textColor = Color.Black,
			textStyle =  SattoTheme.typography.body14Medium,
			focusManager = focusManager,
			keyboardController = keyboardController,
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Number
			),
			keyboardActions = KeyboardActions(
				onDone = {
					validateDateOfBirth()
					keyboardController?.hide()
					focusManager.clearFocus(true)
				},
			),
		)
	}
}