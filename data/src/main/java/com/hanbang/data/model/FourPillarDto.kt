package com.hanbang.data.model

import com.hanbang.domain.model.FourPillar

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class FourPillarDto(
	val strongElement: String,
	val weakElement: String,
	val description: String,
	val yearPillarDetail: PillarDetailDto,
	val monthPillarDetail: PillarDetailDto,
	val dayPillarDetail: PillarDetailDto,
	val timePillarDetail: PillarDetailDto
)

internal fun FourPillarDto.toDomain(): FourPillar {
	return FourPillar(
		strongElement = strongElement,
		weakElement = weakElement,
		description = description,
		yearPillarDetail = yearPillarDetail.toDomain(),
		monthPillarDetail = monthPillarDetail.toDomain(),
		dayPillarDetail = dayPillarDetail.toDomain(),
		timePillarDetail = timePillarDetail.toDomain()
	)
}