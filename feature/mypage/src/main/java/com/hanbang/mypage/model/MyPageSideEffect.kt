package com.hanbang.mypage.model

import com.hanbang.navigation.feature.editprofile.EditProfileRouteModel

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
sealed class MyPageSideEffect {

	data class NavigateToEditProfile(val routeModel: EditProfileRouteModel) : MyPageSideEffect()
}