package com.hanbang.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hanbang.designsystem.R
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.checkbox.HbCheckbox
import com.hanbang.designsystem.input.HbInputField
import com.hanbang.designsystem.radiobutton.HbRadioButton
import com.hanbang.designsystem.theme.Black
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Gray7
import com.hanbang.designsystem.theme.LocalSattoTypography
import com.hanbang.designsystem.theme.Red3
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.util.clickableSingle
import com.hanbang.domain.model.BirthTimeRanges
import com.hanbang.onboarding.component.OnboardingAgreementBottomSheetDialog
import com.hanbang.designsystem.birthtime.BirthTimeBottomSheetDialog
import com.hanbang.onboarding.model.OnboardingGenderType
import com.hanbang.onboarding.model.OnboardingSideEffect
import com.hanbang.onboarding.model.OnboardingState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 *
 * @author   JGeun
 * @created  2025/07/22
 */
@Composable
fun OnboardingRoute(
) {
	val viewModel = viewModel<OnboardingViewModel>()
	val state by viewModel.collectAsState()
	var showBirthTimeDialog by remember { mutableStateOf(false) }
	var showAgreementDialog by remember { mutableStateOf(false) }

	OnboardingScreen(
		state = state,
		onNameInputChanged = viewModel::inputNameChanged,
		onValidateStage = viewModel::validateStage,
		onGenderSelected = viewModel::onGenderSelected,
		navigateUp = { },
		onDateOfBirthInputChanged = viewModel::inputDateOfBirth,
		onClickBirthTimePicker = { showBirthTimeDialog = true },
		onToggleUserBirthTimeUnknown = viewModel::toggleUserBirthTimeUnknown
	)

	viewModel.collectSideEffect {
		when (it) {
			is OnboardingSideEffect.OpenAgreementDialog -> {
				showAgreementDialog = true
			}
		}
	}

	if (showBirthTimeDialog) {
		BirthTimeBottomSheetDialog(
			timeList = BirthTimeRanges.timeList,
			onDismissRequest = { showBirthTimeDialog = false },
			onSelectBirthTime = viewModel::saveBirthTime
		)
	}

	if (showAgreementDialog) {
		OnboardingAgreementBottomSheetDialog(
			onDismissRequest = { showAgreementDialog = false },
			onConfirmAgreement = {},
			openServiceAgreementInfo = {},
			openPersonalInfoAgreementInfo = {}
		)
	}
}

@Composable
private fun OnboardingScreen(
	state: OnboardingState,
	navigateUp: () -> Unit,
	onNameInputChanged: (String) -> Unit,
	onValidateStage: () -> Unit,
	onGenderSelected: (OnboardingGenderType) -> Unit,
	onDateOfBirthInputChanged: (String) -> Unit,
	onClickBirthTimePicker: () -> Unit,
	onToggleUserBirthTimeUnknown: () -> Unit
) {
	val typography = LocalSattoTypography.current
	val focusManager: FocusManager = LocalFocusManager.current

	Box(
		modifier = Modifier
			.fillMaxSize()

	) {
		Column(
			modifier = Modifier
				.fillMaxSize()
		) {
			val itemHeight = 28.dp

			LazyColumn(
				modifier = Modifier.fillMaxWidth()
			) {
				item {
					OnboardingNamingHeader(
						navigateUp = { navigateUp() },
					)
				}

				item {
					Column(
						modifier = Modifier
							.fillMaxWidth()
							.padding(horizontal = 24.dp)
					) {
						Text(
							text = "정보를 입력해 주세요",
							style = typography.headline22Bold
						)

						Spacer(Modifier.height(6.dp))

						Text(
							text = "회원님의 사주를 기반으로 로또 번호를 추천해드려요",
							style = typography.body14Medium
						)

						Spacer(Modifier.height(28.dp))
					}
				}

				item {
					StaggeredAppearItem(
						stage = state.stage.order,
						index = OnboardingStage.BIRTH_TIME.order,
						itemHeight = itemHeight
					) {
						OnboardingBirthTimePicker(
							state = state,
							onClickBirthTimePicker = {
								onClickBirthTimePicker()
								focusManager.clearFocus(true)
							},
							onToggleUserBirthTimeUnknown = onToggleUserBirthTimeUnknown
						)
					}
				}

				item {
					StaggeredAppearItem(
						stage = state.stage.order,
						index = OnboardingStage.DATE_OF_BIRTH.order,
						itemHeight = itemHeight
					) {
						OnboardingDateOfBirth(
							state = state,
							onDateOfBirthInputChanged = onDateOfBirthInputChanged,
							focusManager = focusManager,
							validateDateOfBirth = {
								if (state.stage == OnboardingStage.DATE_OF_BIRTH) {
									onValidateStage()
								}
							},
						)
					}
				}

				item {
					StaggeredAppearItem(
						stage = state.stage.order,
						index = OnboardingStage.GENDER.order,
						itemHeight = itemHeight
					) {
						OnboardingGenderContent(
							state = state,
							onGenderSelected = onGenderSelected,
						)
					}
				}

				item {
					StaggeredAppearItem(
						stage = state.stage.order,
						index = OnboardingStage.NAMING.order,
						itemHeight = itemHeight
					) {
						OnboardingNamingContent(
							state = state,
							onNameInputChanged = onNameInputChanged,
							validateName = {
								if (state.stage == OnboardingStage.NAMING) {
									onValidateStage()
								}
							},
							focusManager = focusManager,
						)
					}
				}
			}
		}

		HbBoxButton(
			modifier = Modifier
				.align(Alignment.BottomCenter)
				.fillMaxWidth()
				.padding(horizontal = 24.dp)
				.padding(bottom = 24.dp),
			text = "다음",
			onClick = { onValidateStage() },
			colors = HbButtonColorType.primary,
			styles = HbButtonStyles.xLarge,
			isEnabled = state.buttonValidation
		)
	}
}

@Composable
private fun OnboardingNamingHeader(
	navigateUp: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		Icon(
			modifier = Modifier
				.size(24.dp)
				.clickableSingle(activeRippleEffect = false) { navigateUp() },
			painter = painterResource(R.drawable.ic_arrow_left),
			contentDescription = "left arrow",
		)
	}
}

@Composable
private fun StaggeredAppearItem(
	stage: Int,
	index: Int,
	itemHeight: Dp,
	content: @Composable () -> Unit
) {
	val appear = remember(index) { MutableTransitionState(false) }
	val topPadding by animateDpAsState(
		targetValue = itemHeight, //if (stage > index) ((stage - index) * itemHeight) else 0.dp,
		animationSpec = tween(200)
	)

	LaunchedEffect(stage) {
		if (stage >= index) {
			appear.targetState = true
		}
	}

	AnimatedVisibility(
		visibleState = appear,
		enter = slideInVertically(
			initialOffsetY = { 0 }
		) + fadeIn(animationSpec = tween(600)),
		modifier = Modifier
			.padding(top = topPadding)
			.fillMaxWidth()
	) {
		content()
	}
}

@Composable
private fun OnboardingNamingContent(
	state: OnboardingState,
	onNameInputChanged: (String) -> Unit,
	validateName: () -> Unit,
	modifier: Modifier = Modifier,
	focusManager: FocusManager = LocalFocusManager.current,
) {
	val typography = LocalSattoTypography.current
	val keyboardController = LocalSoftwareKeyboardController.current

	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp)
	) {
		Text(
			text = "이름",
			style = typography.body16Bold
		)

		Spacer(Modifier.height(8.dp))

		HbInputField(
			value = state.name,
			onValueChange = { onNameInputChanged(it) },
			hint = "김사또",
			isError = state.nameInputErrorMsg.isNotEmpty(),
			errorMessage = state.nameInputErrorMsg,
			errorTextStyle = typography.caption12Medium.copy(
				color = Red3
			),
			modifier = Modifier.fillMaxWidth(),
			textColor = Color.Black,
			textStyle = typography.body14Medium,
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

@Composable
private fun OnboardingGenderContent(
	state: OnboardingState,
	onGenderSelected: (OnboardingGenderType) -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp)
	) {
		Text(
			text = "성별",
			style = LocalSattoTypography.current.body16Bold,
			color = Gray1
		)

		Row(
			horizontalArrangement = Arrangement.spacedBy(20.dp)
		) {
			HbRadioButton(
				text = "남성",
				onClick = { onGenderSelected(OnboardingGenderType.MALE) },
				isActive = state.gender == OnboardingGenderType.MALE,
				isEnabled = true
			)

			HbRadioButton(
				text = "여성",
				onClick = { onGenderSelected(OnboardingGenderType.FEMALE) },
				isActive = state.gender == OnboardingGenderType.FEMALE,
				isEnabled = true
			)
		}
	}
}

@Composable
private fun OnboardingDateOfBirth(
	state: OnboardingState,
	onDateOfBirthInputChanged: (String) -> Unit,
	validateDateOfBirth: () -> Unit,
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
			value = state.dateOfBirth,
			onValueChange = { onDateOfBirthInputChanged(it) },
			hint = "2000-01-01",
			isError = state.dateOfBirthInputErrorMsg.isNotEmpty(),
			errorMessage = state.dateOfBirthInputErrorMsg,
			errorTextStyle = typography.caption12Medium.copy(
				color = Red3
			),
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
					validateDateOfBirth()
					keyboardController?.hide()
					focusManager.clearFocus(true)
				},
			),
		)
	}
}

@Composable
private fun OnboardingBirthTimePicker(
	state: OnboardingState,
	onClickBirthTimePicker: () -> Unit,
	onToggleUserBirthTimeUnknown: () -> Unit,
) {
	val birthTimepickerBgColor = if (state.userBirthTimeUnknown) {
		Gray7
	} else {
		White
	}

	val birthTimepickerBorderWidth = if (state.userBirthTimeUnknown) {
		0.dp
	} else {
		1.dp
	}

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 24.dp),
	) {
		Text(
			text = "태어난 시",
			style = LocalSattoTypography.current.body16Bold,
			color = Gray1
		)

		Spacer(Modifier.height(8.dp))

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.background(
					color = birthTimepickerBgColor,
					shape = RoundedCornerShape(8.dp)
				)
				.border(
					width = birthTimepickerBorderWidth,
					color = Gray7,
					shape = RoundedCornerShape(8.dp)
				)
				.clickableSingle(activeRippleEffect = false) {
					onClickBirthTimePicker()
				}
				.padding(horizontal = 14.dp, vertical = 11.dp)
		) {
			Text(
				text = if (state.userBirthTimeUnknown) {
					"입력하지 않아도 괜찮아요"
				} else if (state.birthTime.isEmpty()) {
					"23:00 ~ 00:59"
				} else {
					state.birthTime
				},
				style = LocalSattoTypography.current.body14Semibold,
				color = if (state.userBirthTimeUnknown || state.birthTime.isEmpty()) {
					Gray5
				} else {
					Black
				}
			)
		}

		Spacer(Modifier.height(12.dp))

		HbCheckbox(
			text = "모르겠어요",
			isActive = state.userBirthTimeUnknown,
			isEnabled = true,
			onClick = { onToggleUserBirthTimeUnknown() },
		)
	}
}

@Composable
@Preview(showBackground = true)
private fun OnboardingNamingScreenPreview() {

	var state by remember { mutableStateOf(OnboardingState()) }

	SattoTheme {
		OnboardingScreen(
			state = state,
			onNameInputChanged = {
				state = state.copy(it)
			},
			onGenderSelected = {
				state = state.copy(gender = it)
			},
			onValidateStage = {},
			navigateUp = {},
			onDateOfBirthInputChanged = {},
			onClickBirthTimePicker = { state = state.copy(birthTime = "23:00 ~ 00:59") },
			onToggleUserBirthTimeUnknown = {}
		)
	}
}