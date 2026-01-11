package com.hanbang.designsystem.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Black
import com.hanbang.designsystem.theme.Gray4
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Gray7
import com.hanbang.designsystem.theme.Gray8
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.Red3
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import kotlinx.coroutines.launch

/**
 *
 * @author   JGeun
 * @created  2025/07/27
 */
@Composable
fun HbInputField(
	value: String,
	onValueChange: (String) -> Unit,
	textColor: Color,
	textStyle: TextStyle,
	modifier: Modifier = Modifier,
	isSingleLine: Boolean = true,
	counterMaxLength: Int = 0,
	readOnly: Boolean = false,
	isEnabled: Boolean = true,
	hint: String = "",
	hintTextStyle: TextStyle  = TextStyle.Default,
	isError: Boolean = false,
	errorMessage: String = "",
	errorTextStyle: TextStyle  = TextStyle.Default,
	visualTransformation: VisualTransformation = VisualTransformation.None,
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	focusManager: FocusManager = LocalFocusManager.current,
	keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
	keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
	keyboardActions: KeyboardActions = KeyboardActions(
		onDone = {
			keyboardController?.hide()
			focusManager.clearFocus(true)
		},
	),
	focusRequester: FocusRequester = remember { FocusRequester() },
	onFocusChanged: (FocusState) -> Unit = {},
	leadingIcon: (@Composable (() -> Unit))? = null,
	trailingIcon: (@Composable (() -> Unit))? = null
) {
	val isFocused by interactionSource.collectIsFocusedAsState()

	val coroutineScope = rememberCoroutineScope()
	val bringIntoViewRequester = remember { BringIntoViewRequester() }

	val containerColor = if (isEnabled) White else Gray8
	val contentColor = if (isEnabled) textColor else Gray5
	val borderColor = when {
		isError -> Red3
		isEnabled -> {
			if (isFocused) {
				Primary2
			} else {
				Gray7
			}
		}
		else -> Gray5
	}
	val hintColor = when {
		isEnabled -> Gray4
		isError -> Red3
		else -> Gray5
	}

	Column(
		modifier = modifier,
		verticalArrangement = Arrangement.spacedBy(6.dp)
	) {
		BasicTextField(
			modifier = Modifier
				.fillMaxWidth()
				.background(containerColor)
				.border(1.dp, borderColor, RoundedCornerShape(6.dp))
				.padding(horizontal = 14.dp, vertical = 11.dp)
				.onFocusEvent { event ->
					if (event.isFocused && isError) {
						coroutineScope.launch {
							bringIntoViewRequester.bringIntoView()
						}
					}
					onFocusChanged(event)
				}
				.focusRequester(focusRequester),
			value = value,
			onValueChange = {
				val newValue = if (counterMaxLength > 0) {
					it.take(counterMaxLength)
				} else {
					it
				}
				onValueChange(newValue)
			},
			enabled = isEnabled,
			readOnly = readOnly,
			textStyle = textStyle.copy(contentColor),
			keyboardOptions = keyboardOptions,
			keyboardActions = keyboardActions,
			singleLine = isSingleLine,
			interactionSource = interactionSource,
			visualTransformation = visualTransformation,
			cursorBrush = SolidColor(Primary2),
			decorationBox = @Composable { innerTextField ->
				Row(
					modifier = Modifier.fillMaxWidth(),
					verticalAlignment = Alignment.CenterVertically
				) {
					if (leadingIcon != null) {
						leadingIcon()
						Spacer(modifier = Modifier.width(8.dp))
					}

					Box(modifier = Modifier.weight(1f)) {
						if (value.isEmpty()) {
							Text(
								text = hint,
								style = textStyle.copy(color = Gray5)
							)
						}
						innerTextField()
					}

					if (trailingIcon != null) {
						Spacer(modifier = Modifier.width(8.dp))
						trailingIcon()
					}
				}
			},
		)

		if (isError && errorMessage.isNotEmpty()) {
			Text(
				modifier = Modifier
					.bringIntoViewRequester(bringIntoViewRequester),
				text = errorMessage,
				style = errorTextStyle,
			)
		}
	}
}

@Preview
@Composable
private fun HbInputFieldPreview() {
	var inputText by remember { mutableStateOf("입력 필드") }
	var inputText2 by remember { mutableStateOf("") }

	SattoTheme {
		val textStyle = TextStyle(color = Gray7)
		val hintTextStyle = TextStyle(color = Gray4)
		val errorTextStyle = TextStyle(color = Red3)

		Column(
			verticalArrangement = Arrangement.spacedBy(6.dp)
		) {
			HbInputField(
				value = inputText,
				onValueChange = {
					inputText = it
				},
				modifier = Modifier.fillMaxWidth(),
				textColor = Black,
				textStyle = textStyle,
				counterMaxLength = 20,
				readOnly = false,
				isEnabled = true,
				hint = "힌트 텍스트",
				hintTextStyle = hintTextStyle,
				isError = false,
				errorMessage = "",
				errorTextStyle = errorTextStyle,
				visualTransformation = VisualTransformation.None,
				interactionSource = remember { MutableInteractionSource() },
			)

			HbInputField(
				value = inputText2,
				onValueChange = {
					inputText2 = it
				},
				modifier = Modifier.fillMaxWidth(),
				textColor = Black,
				textStyle = textStyle,
				counterMaxLength = 20,
				readOnly = false,
				isEnabled = true,
				hint = "힌트 텍스트",
				hintTextStyle = hintTextStyle,
				isError = false,
				errorMessage = "",
				errorTextStyle = errorTextStyle,
				visualTransformation = VisualTransformation.None,
				interactionSource = remember { MutableInteractionSource() },
			)

			HbInputField(
				value = inputText,
				onValueChange = {
					inputText = it
				},
				modifier = Modifier.fillMaxWidth(),
				textColor = Black,
				textStyle = textStyle,
				counterMaxLength = 20,
				readOnly = false,
				isEnabled = false,
				hint = "힌트 텍스트",
				hintTextStyle = hintTextStyle,
				isError = false,
				errorMessage = "",
				errorTextStyle = errorTextStyle,
				visualTransformation = VisualTransformation.None,
				interactionSource = remember { MutableInteractionSource() },
			)
		}

	}
}