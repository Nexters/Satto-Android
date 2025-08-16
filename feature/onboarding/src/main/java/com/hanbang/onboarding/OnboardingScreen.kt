package com.hanbang.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanbang.designsystem.birthtime.BirthTimeBottomSheetDialog
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.domain.model.BirthTimeRanges
import com.hanbang.domain.model.GenderType
import com.hanbang.onboarding.component.OnboardingAgreementBottomSheetDialog
import com.hanbang.onboarding.component.OnboardingBirthTimePicker
import com.hanbang.onboarding.component.OnboardingDateOfBirth
import com.hanbang.onboarding.component.OnboardingGenderContent
import com.hanbang.onboarding.component.OnboardingHeader
import com.hanbang.onboarding.component.OnboardingNamingContent
import com.hanbang.onboarding.component.OnboardingTitleSection
import com.hanbang.onboarding.component.StaggeredAppearItem
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
	paddingValues: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
	navigateToHome: () -> Unit,
	navigateUp: () -> Unit,
	viewModel: OnboardingViewModel = hiltViewModel()
) {
	val state by viewModel.collectAsState()
	var showBirthTimeDialog by remember { mutableStateOf(false) }
	var showAgreementDialog by remember { mutableStateOf(false) }

	OnboardingScreen(
		state = state,
		paddingValues = paddingValues,
		onNameInputChanged = viewModel::inputNameChanged,
		onValidateStage = viewModel::validateStage,
		onGenderSelected = viewModel::onGenderSelected,
		navigateUp = navigateUp,
		onDateOfBirthInputChanged = viewModel::inputDateOfBirth,
		onClickBirthTimePicker = { showBirthTimeDialog = true },
		onToggleUserBirthTimeUnknown = viewModel::toggleUserBirthTimeUnknown
	)

	if (state.isLoading) {
		LoadingProgress()
	}

	viewModel.collectSideEffect {
		when (it) {
			is OnboardingSideEffect.OpenAgreementDialog -> {
				showAgreementDialog = true
			}

			is OnboardingSideEffect.ShowError -> {
				onShowErrorSnackBar(HbSnackBarType.ERROR(it.message))
			}

			OnboardingSideEffect.UserCreated -> { navigateToHome() }
		}
	}

	if (showBirthTimeDialog) {
		BirthTimeBottomSheetDialog(
			initialTime = state.birthTimeStr,
			timeList = BirthTimeRanges.timeList,
			onDismissRequest = { showBirthTimeDialog = false },
			onSelectBirthTime = viewModel::saveBirthTime
		)
	}

	if (showAgreementDialog) {
		OnboardingAgreementBottomSheetDialog(
			onDismissRequest = { showAgreementDialog = false },
			onConfirmAgreement = viewModel::createUser,
			openServiceAgreementInfo = {},
			openPersonalInfoAgreementInfo = {}
		)
	}
}

@Composable
private fun OnboardingScreen(
	state: OnboardingState,
	paddingValues: PaddingValues,
	navigateUp: () -> Unit,
	onNameInputChanged: (String) -> Unit,
	onValidateStage: () -> Unit,
	onGenderSelected: (GenderType) -> Unit,
	onDateOfBirthInputChanged: (String) -> Unit,
	onClickBirthTimePicker: () -> Unit,
	onToggleUserBirthTimeUnknown: () -> Unit
) {
	val focusManager: FocusManager = LocalFocusManager.current

	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(White)
			.padding(paddingValues)
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
					OnboardingHeader(
						navigateUp = navigateUp,
					)
				}

				item {
					OnboardingTitleSection()
				}

				item {
					StaggeredAppearItem(
						stage = state.stage.order,
						index = OnboardingStage.BIRTH_TIME.order,
						itemHeight = itemHeight
					) {
						OnboardingBirthTimePicker(
							birthTime = state.birthTimeStr,
							userBirthTimeUnknown = state.userBirthTimeUnknown,
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
							dateOfBirth = state.dateOfBirth,
							dateOfBirthInputErrorMsg = state.dateOfBirthInputErrorMsg,
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
							genderType = state.genderType,
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
							name = state.name,
							nameInputErrorMsg = state.nameInputErrorMsg,
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
private fun LoadingProgress() {
	Box(
		modifier = Modifier.fillMaxSize()
	) {
		CircularProgressIndicator(
			modifier = Modifier
				.size(40.dp)
				.align(Alignment.Center)
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
			paddingValues = PaddingValues(),
			onNameInputChanged = {
			},
			onGenderSelected = {
				state = state.copy(genderType = it)
			},
			onValidateStage = {},
			navigateUp = {},
			onDateOfBirthInputChanged = {},
			onClickBirthTimePicker = {},
			onToggleUserBirthTimeUnknown = {}
		)
	}
}