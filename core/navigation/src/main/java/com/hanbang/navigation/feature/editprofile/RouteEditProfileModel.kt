package com.hanbang.navigation.feature.editprofile

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 *
 * @author   JGeun
 * @created  2025/08/06
 */
@Serializable
data class EditProfileRouteModel(
	val name: String = "",
	val gender: String = "",
	val dateOfBirth: String = "",
	val birthTime: String = "",
	val userBirthTimeUnknown: Boolean = false
)

fun EditProfileRouteModel.toJson(): String {
	val json = Json { isLenient = true }
	return try {
		json.encodeToString(this)
	} catch (e: Exception) {
		"{}"
	}
}

fun String.toEditProfileRouteModel(): EditProfileRouteModel {
	val json = Json { isLenient = true }
	return try {
		json.decodeFromString<EditProfileRouteModel>(this)
	} catch (e: Exception) {
		EditProfileRouteModel()
	}
}