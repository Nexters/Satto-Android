package com.hanbang.remote.model

import com.hanbang.data.model.FourPillarDto
import com.hanbang.data.model.PillarDetailDto
import com.hanbang.data.model.UserDto
import com.hanbang.remote.model.GetUserResponse.PillarDetail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
@Serializable
data class CreateUserResponse(
	val id: String,
	val name: String = "",
	@SerialName("birth_date")
	val birthDate: String = "",
	@SerialName("birth_time")
	val birthTime: List<String?>? = emptyList(),
	val gender: String = "",
	@SerialName("four_pillar")
	val fourPillar: FourPillar = FourPillar()
) {
	@Serializable
	data class FourPillar(
		@SerialName("strong_element")
		val strongElement: String = "",
		@SerialName("weak_element")
		val weakElement: String = "",
		val description: String = "",
		@SerialName("year_pillar_detail")
		val yearPillarDetail: PillarDetail? = null,
		@SerialName("month_pillar_detail")
		val monthPillarDetail: PillarDetail? = null,
		@SerialName("day_pillar_detail")
		val dayPillarDetail: PillarDetail? = null,
		@SerialName("time_pillar_detail")
		val timePillarDetail: PillarDetail? = null
	)

	@Serializable
	data class PillarDetail(
		val stem: String = "",
		val branch: String = "",
		@SerialName("stem_ten_god")
		val stemTenGod: String = "",
		@SerialName("branch_ten_god")
		val branchTenGod: String = "",
	)
}

internal fun CreateUserResponse.toDto() = UserDto(
	id = id,
	name = name,
	birthDate = birthDate,
	birthTime = birthTime ?: emptyList(),
	gender = gender,
	fourPillar = fourPillar.toDto()
)

internal fun CreateUserResponse.PillarDetail.toDto() = PillarDetailDto(
	stem = stem,
	branch = branch,
	stemTenGod = stemTenGod,
	branchTenGod = branchTenGod,
)

internal fun CreateUserResponse.FourPillar.toDto() = FourPillarDto(
	strongElement = strongElement,
	weakElement = weakElement,
	description = description,
	yearPillarDetail = yearPillarDetail?.toDto() ?: PillarDetailDto(),
	monthPillarDetail = monthPillarDetail?.toDto() ?: PillarDetailDto(),
	dayPillarDetail = dayPillarDetail?.toDto() ?: PillarDetailDto(),
	timePillarDetail = timePillarDetail?.toDto() ?: PillarDetailDto()
)