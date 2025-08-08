package com.hanbang.editprofile.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.editprofile.EditProfileRoute
import com.hanbang.navigation.feature.editprofile.EditProfileRouteModel
import com.hanbang.navigation.feature.editprofile.RouteEditProfile
import com.hanbang.navigation.feature.editprofile.toJson

/**
 *
 * @author   JGeun
 * @created  2025/08/06
 */
fun NavController.navigateToEditProfile(
	editProfileRouteModel: EditProfileRouteModel
) {
	this.navigate(route = RouteEditProfile(editProfileRouteModel.toJson()))
}

fun NavGraphBuilder.editProfileNavGraph(
	padding: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
	onNavigateUp: () -> Unit
) {
	composable<RouteEditProfile> {
		EditProfileRoute(
			paddingValues = padding,
			onShowErrorSnackBar = onShowErrorSnackBar,
			onNavigateUp = onNavigateUp
		)
	}
}