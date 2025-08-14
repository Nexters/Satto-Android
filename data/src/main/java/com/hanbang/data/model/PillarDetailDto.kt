package com.hanbang.data.model

import com.hanbang.domain.model.PillarDetail

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class PillarDetailDto(
	val stem: String,
	val branch: String,
	val stemTenGod: String,
	val branchTenGod: String,
	val stemElement: String,
	val branchElement: String
)

internal fun PillarDetailDto.toDomain(): PillarDetail {
	return PillarDetail(
		stem = stem,
		branch = branch,
		stemTenGod = stemTenGod,
		branchTenGod = branchTenGod,
		stemElement = stemElement,
		branchElement = branchElement
	)
}