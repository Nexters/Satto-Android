package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class FourPillar(
	val strongElement: String,
	val weakElement: String,
	val description: String,
	val yearPillarDetail: PillarDetail,
	val monthPillarDetail: PillarDetail,
	val dayPillarDetail: PillarDetail,
	val timePillarDetail: PillarDetail
)