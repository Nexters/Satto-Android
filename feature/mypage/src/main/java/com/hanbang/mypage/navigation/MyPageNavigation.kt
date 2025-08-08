package com.hanbang.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.editprofile.navigation.navigateToEditProfile
import com.hanbang.mypage.MyPageRoute
import com.hanbang.navigation.feature.editprofile.EditProfileRouteModel
import com.hanbang.navigation.feature.mypage.RouteMyPage

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
fun NavGraphBuilder.myPageNavGraph(
	padding: PaddingValues,
	navigateToEditProfile: (EditProfileRouteModel) -> Unit,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit
) {
	composable<RouteMyPage> {
		MyPageRoute(
			paddingValues = padding,
			navigateToEditProfile = { navigateToEditProfile(EditProfileRouteModel(name = "test")) }
		)
	}
}