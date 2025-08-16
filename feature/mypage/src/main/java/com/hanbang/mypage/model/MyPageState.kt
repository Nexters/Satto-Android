package com.hanbang.mypage.model

import androidx.compose.runtime.Stable
import com.hanbang.domain.model.GenderType
import com.hanbang.navigation.feature.editprofile.EditProfileRouteModel

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Stable
data class MyPageState(
	val isLoading: Boolean = true,
	val name: String = "",
	val dateOfBirth: String = "",
	val birthTime: List<String> = emptyList(),
	val genderType: GenderType = GenderType.NONE
) {
	val birthTimeStr = birthTime.joinToString(separator = " ~ ")
}

fun MyPageState.toEditProfileRouteModel() = EditProfileRouteModel(
	name = name,
	gender = genderType.name,
	dateOfBirth = dateOfBirth.replace("-", ""),
	birthTime = birthTime.joinToString(separator = " ~ "),
)