package com.hanbang.domain.model

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class PillarDetail(
	val stem: PillarElement,
	val branch: PillarElement,
	val stemTenGod: String,
	val branchTenGod: String,
)