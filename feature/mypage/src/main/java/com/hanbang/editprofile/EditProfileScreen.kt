package com.hanbang.editprofile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanbang.designsystem.birthtime.BirthTimeBottomSheetDialog
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.domain.model.BirthTimeRanges
import com.hanbang.domain.model.GenderType
import com.hanbang.editprofile.component.EditProfileBirthTimePicker
import com.hanbang.editprofile.component.EditProfileDateOfBirthContent
import com.hanbang.editprofile.component.EditProfileGenderContent
import com.hanbang.editprofile.component.EditProfileHeader
import com.hanbang.editprofile.component.EditProfileNamingContent
import com.hanbang.editprofile.component.EditProfileNavigateUpMiddleDialog
import com.hanbang.editprofile.model.EditProfileSideEffect
import com.hanbang.editprofile.model.EditProfileUiState
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 *
 * @author   JGeun
 * @created  2025/08/05
 */

@Composable
fun EditProfileRoute(
	paddingValues: PaddingValues,
	onShowSnackBar: (HbSnackBarType) -> Unit,
	onNavigateUp: () -> Unit,
	viewModel: EditProfileViewModel = hiltViewModel()
) {
	var showBirthTimeDialog by remember { mutableStateOf(false) }
	var showNavigateUpMsgDialog by remember { mutableStateOf(false) }
	val state by viewModel.collectAsState()

	BackHandler {
		if (viewModel.equalPreviousProfileData()) {
			onNavigateUp()
		} else {
			showNavigateUpMsgDialog = true
		}
	}

	EditProfileScreen(
		state = state,
		onNavigateUp = {
			if (viewModel.equalPreviousProfileData()) {
				onNavigateUp()
			} else {
				showNavigateUpMsgDialog = true
			}
		},
		onNameInputChanged = viewModel::inputNameChanged,
		paddingValues = paddingValues,
		onGenderSelected = viewModel::onGenderSelected,
		onDateOfBirthInputChanged = viewModel::inputDateOfBirth,
		onClickBirthTimePicker = { showBirthTimeDialog = true },
		onToggleUserBirthTimeUnknown = viewModel::toggleUserBirthTimeUnknown,
		onStoreChangedUserInfo = viewModel::storeChangedUserInfo
	)

	if (state.isLoading) {
		LoadingProgress()
	}

	if (showBirthTimeDialog) {
		BirthTimeBottomSheetDialog(
			initialTime = state.birthTimeStr,
			timeList = BirthTimeRanges.timeList,
			onDismissRequest = { showBirthTimeDialog = false },
			onSelectBirthTime = viewModel::saveBirthTime
		)
	}

	if (showNavigateUpMsgDialog) {
		EditProfileNavigateUpMiddleDialog(
			onDismiss = { showNavigateUpMsgDialog = false },
			onConfirm = {
				showNavigateUpMsgDialog = false
				onNavigateUp()
			}
		)
	}

	viewModel.collectSideEffect { sideEffect ->
		when (sideEffect) {
			is EditProfileSideEffect.NavigateUp -> {
				onNavigateUp()
			}

			is EditProfileSideEffect.ShowErrorMessage -> {
				onShowSnackBar(
					HbSnackBarType.ERROR(
						message = sideEffect.message
					)
				)
			}

			is EditProfileSideEffect.ShowSnackBar -> {
				onShowSnackBar(sideEffect.hbSnackBarType)
			}
		}
	}
}

@Composable
private fun EditProfileScreen(
	state: EditProfileUiState,
	paddingValues: PaddingValues,
	onNavigateUp: () -> Unit,
	onNameInputChanged: (String) -> Unit,
	onGenderSelected: (GenderType) -> Unit,
	onDateOfBirthInputChanged: (String) -> Unit,
	onClickBirthTimePicker: () -> Unit,
	onToggleUserBirthTimeUnknown: () -> Unit,
	onStoreChangedUserInfo: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(paddingValues)
	) {
		EditProfileHeader(
			navigateUp = onNavigateUp
		)

		LazyColumn(
			modifier = Modifier
				.weight(1f, fill = true)
				.padding(vertical = 18.dp),
			verticalArrangement = Arrangement.spacedBy(28.dp)
		) {
			item {
				EditProfileNamingContent(
					name = state.name,
					nameInputErrorMsg = state.nameInputErrorMsg,
					onNameInputChanged = onNameInputChanged
				)
			}

			item {
				EditProfileGenderContent(
					genderType = state.genderType,
					onGenderSelected = onGenderSelected
				)
			}

			item {
				EditProfileDateOfBirthContent(
					dateOfBirth = state.dateOfBirth,
					errorMsg = state.dateOfBirthErrorMsg,
					onDateOfBirthInputChanged = onDateOfBirthInputChanged,
				)
			}

			item {
				EditProfileBirthTimePicker(
					birthTime = state.birthTimeStr,
					userBirthTimeUnknown = state.userBirthTimeUnknown,
					onClickBirthTimePicker = onClickBirthTimePicker,
					onToggleUserBirthTimeUnknown = onToggleUserBirthTimeUnknown
				)
			}
		}

		HbBoxButton(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 24.dp)
				.padding(bottom = 24.dp),
			text = "다음",
			onClick = onStoreChangedUserInfo,
			colors = HbButtonColorType.primary,
			styles = HbButtonStyles.xLarge,
			isEnabled = true
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

@Preview
@Composable
private fun EditProfileScreenPreview() {
	SattoTheme {
		EditProfileScreen(
			paddingValues = PaddingValues(),
			onNavigateUp = {},
			state = EditProfileUiState(
				name = "김사또",
				nameInputErrorMsg = "",
				genderType = GenderType.NONE,
				dateOfBirth = "2000-01-01",
				dateOfBirthErrorMsg = "",
				birthTime = emptyList(),
				userBirthTimeUnknown = false,
				buttonValidation = true
			),
			onNameInputChanged = {},
			onGenderSelected = {},
			onDateOfBirthInputChanged = {},
			onClickBirthTimePicker = {},
			onToggleUserBirthTimeUnknown = {},
			onStoreChangedUserInfo = {}
		)
	}
}
