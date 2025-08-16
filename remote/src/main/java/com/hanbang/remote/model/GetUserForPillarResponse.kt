package com.hanbang.remote.model

import com.hanbang.data.model.FourPillarDto
import com.hanbang.data.model.PillarDetailDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
@Serializable
data class GetUserForPillarResponse(
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
) {
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

internal fun GetUserForPillarResponse.toDto() = FourPillarDto(
	strongElement = strongElement,
	weakElement = weakElement,
	description = description,
	yearPillarDetail = yearPillarDetail.toDto(),
	monthPillarDetail = monthPillarDetail.toDto(),
	dayPillarDetail = dayPillarDetail.toDto(),
	timePillarDetail = timePillarDetail.toDto()
)

internal fun GetUserForPillarResponse.PillarDetail.toDto() = PillarDetailDto(
	stem = stem,
	branch = branch,
	stemTenGod = stemTenGod,
	branchTenGod = branchTenGod,
)