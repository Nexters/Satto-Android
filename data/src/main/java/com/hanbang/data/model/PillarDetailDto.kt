package com.hanbang.data.model

import com.hanbang.domain.model.PillarDetail
import com.hanbang.domain.model.PillarElement

/**
 *
 * @author   JGeun
 * @created  2025/08/13
 */
data class PillarDetailDto(
	val stem: String = "",
	val branch: String = "",
	val stemTenGod: String = "",
	val branchTenGod: String = "",
)

internal fun PillarDetailDto.toDomain(): PillarDetail {
	return PillarDetail(
		stem = PillarElement.parsePillarElementInString(stem),
		branch = PillarElement.parsePillarElementInString(branch),
		stemTenGod = stemTenGod,
		branchTenGod = branchTenGod,
	)
}