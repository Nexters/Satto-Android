package com.hanbang.remote.model

import com.hanbang.data.model.FourPillarDto
import com.hanbang.data.model.PillarDetailDto
import com.hanbang.data.model.UserDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
@Serializable
data class GetUserResponse(
	val id: String,
	val name: String,
	@SerialName("birth_date")
	val birthDate: String,
	@SerialName("birth_time")
	val birthTime: List<String?>,
	val gender: String,
	@SerialName("four_pillar")
	val fourPillar: FourPillar
) {
	@Serializable
	data class FourPillar(
		@SerialName("strong_element")
		val strongElement: String,
		@SerialName("weak_element")
		val weakElement: String,
		val description: String,
		@SerialName("year_pillar_detail")
		val yearPillarDetail: PillarDetail,
		@SerialName("month_pillar_detail")
		val monthPillarDetail: PillarDetail,
		@SerialName("day_pillar_detail")
		val dayPillarDetail: PillarDetail,
		@SerialName("time_pillar_detail")
		val timePillarDetail: PillarDetail
	)

	@Serializable
	data class PillarDetail(
		val stem: String,
		val branch: String,
		@SerialName("stem_ten_god")
		val stemTenGod: String,
		@SerialName("branch_ten_god")
		val branchTenGod: String,
	)
}

internal fun GetUserResponse.toDto() = UserDto(
	id = id,
	name = name,
	birthDate = birthDate,
	birthTime = birthTime,
	gender = gender,
	fourPillar = fourPillar.toDto()
)

internal fun GetUserResponse.PillarDetail.toDto() = PillarDetailDto(
	stem = stem,
	branch = branch,
	stemTenGod = stemTenGod,
	branchTenGod = branchTenGod,
)

internal fun GetUserResponse.FourPillar.toDto() = FourPillarDto(
	strongElement = strongElement,
	weakElement = weakElement,
	description = description,
	yearPillarDetail = yearPillarDetail.toDto(),
	monthPillarDetail = monthPillarDetail.toDto(),
	dayPillarDetail = dayPillarDetail.toDto(),
	timePillarDetail = timePillarDetail.toDto()
)