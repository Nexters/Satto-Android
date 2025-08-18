package com.hanbang.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
@Serializable
data class CreateUserParam(
	@SerialName("id")
	val deviceId: String,
	val name: String,
	@SerialName("birth_date")
	val birthDate: String,
	@SerialName("birth_time")
	val birthTime: List<String?>? = null,
	val gender: String
) {
	fun toRequestBody(): RequestBody {
		val json = Json{
			encodeDefaults = false   // default 값은 제외
			explicitNulls = false
		}
		return json.encodeToString(this).toRequestBody()
	}
}