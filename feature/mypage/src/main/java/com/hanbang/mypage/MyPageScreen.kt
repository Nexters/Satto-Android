package com.hanbang.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.Primary9
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.mypage.component.MyPageFeedbackBox
import com.hanbang.mypage.component.MyPageHeader
import com.hanbang.mypage.component.MyPageProfileBox
import com.hanbang.mypage.component.MyPageSectionItem
import com.hanbang.mypage.model.MyPageSideEffect
import com.hanbang.mypage.model.MyPageState
import com.hanbang.navigation.feature.editprofile.EditProfileRouteModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
internal fun MyPageRoute(
	paddingValues: PaddingValues,
	navigateToEditProfile: (EditProfileRouteModel) -> Unit,
	viewModel: MyPageViewModel = hiltViewModel()
) {
	val state by viewModel.collectAsState()

	LaunchedEffect(Unit)  {
		viewModel.initializeUserData()
	}

	Box(
		modifier = Modifier.fillMaxSize()
	) {
		MyPageScreen(
			state = state,
			paddingValues = paddingValues,
			navigateToProfileEdit = viewModel::navigateToProfileEdit
		)

		if (state.isLoading) {
			LoadingProgress()
		}
	}

	viewModel.collectSideEffect { sideEffect ->
		when(sideEffect) {
			is MyPageSideEffect.NavigateToEditProfile -> {
				navigateToEditProfile(sideEffect.routeModel)
			}
		}
	}
}

@Composable
private fun MyPageScreen(
	state: MyPageState,
	paddingValues: PaddingValues,
	navigateToProfileEdit: () -> Unit,
) {
	Column(
		modifier = Modifier.fillMaxSize()
			.background(Primary9)
			.padding(paddingValues)
	) {
		MyPageHeader()

		LazyColumn(
			modifier = Modifier.fillMaxSize()
				.padding(horizontal = 24.dp)
		) {
			item {
				MyPageProfileBox(
					name = state.name,
					genderType = state.genderType,
					birthDate = state.dateOfBirth,
					birthTime = state.birthTimeStr,
					onEditProfile = {
						navigateToProfileEdit()
					}
				)
			}

			item {
				Spacer(Modifier.height(12.dp))
			}

			item {
				MyPageFeedbackBox()
			}

			item {
				Spacer(Modifier.height(12.dp))
			}

			item {
				Column(
					modifier = Modifier.fillMaxWidth()
						.background(color = White, shape = RoundedCornerShape(10.dp))
						.padding(vertical = 10.dp, horizontal = 10.dp)
				) {
//					MyPageSectionItem(
//						text = "푸시알림",
//						onClick = {},
//						rightComponent = {
//							Icon(
//								modifier = Modifier.size(24.dp),
//								painter = painterResource(R.drawable.ic_chevron_right_16),
//								contentDescription = null,
//								tint = Gray5
//							)
//						}
//					)

					MyPageSectionItem(
						text = "이용약관",
						onClick = {},
						rightComponent = {
							Icon(
								modifier = Modifier.size(24.dp),
								painter = painterResource(R.drawable.ic_chevron_right_16),
								contentDescription = null,
								tint = Gray5
							)
						}
					)

					MyPageSectionItem(
						text = "개인정보 처리방침",
						onClick = {},
						rightComponent = {
							Icon(
								modifier = Modifier.size(24.dp),
								painter = painterResource(R.drawable.ic_chevron_right_16),
								contentDescription = null,
								tint = Gray5
							)
						}
					)

					MyPageSectionItem(
						text = "앱 버전",
						onClick = {},
						rightComponent = {
							Text(
								text = "1.0.0",
								style = SattoTheme.typography.body14Medium,
								color = Primary2
							)
						}
					)
				}
			}
		}
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
private fun MyPageScreenPreview() {
	SattoTheme {
		MyPageScreen(
			state = MyPageState(),
			paddingValues = PaddingValues(),
			navigateToProfileEdit = {}
		)
	}
}