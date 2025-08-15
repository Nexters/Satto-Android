package com.hanbang.editprofile.component

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
import com.hanbang.designsystem.theme.LocalSattoTypography
import com.hanbang.designsystem.theme.Red3

/**
 *
 * @author   JGeun
 * @created  2025/08/08
 */
@Composable
internal fun EditProfileDateOfBirthContent(
	dateOfBirth: String,
	errorMsg: String,
	onDateOfBirthInputChanged: (String) -> Unit,
	modifier: Modifier = Modifier,
	focusManager: FocusManager = LocalFocusManager.current,
) {
	val typography = LocalSattoTypography.current
	val keyboardController = LocalSoftwareKeyboardController.current

	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Text(
			text = "생년월일",
			style = LocalSattoTypography.current.body16Bold,
			color = Gray1
		)

		HbInputField(
			value = dateOfBirth,
			onValueChange = { onDateOfBirthInputChanged(it) },
			hint = "20000101",
			isError = errorMsg.isNotEmpty(),
			errorMessage = errorMsg,
			errorTextStyle = typography.caption12Medium.copy(
				color = Red3
			),
			counterMaxLength = 8,
			modifier = Modifier.fillMaxWidth(),
			textColor = Color.Black,
			textStyle = typography.body14Medium,
			focusManager = focusManager,
			keyboardController = keyboardController,
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Number
			),
			keyboardActions = KeyboardActions(
				onDone = {
					keyboardController?.hide()
					focusManager.clearFocus(true)
				},
			),
		)
	}
}
