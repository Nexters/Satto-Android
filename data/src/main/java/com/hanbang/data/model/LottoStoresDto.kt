package com.hanbang.data.model

import com.hanbang.domain.model.LottoStores

/**
 * @author   JGeun
 * @created  2026/01/11
 */
data class LottoStoresDto(
	val id: Long,
	val name: String,
	val address: String,
	val latitude: Double,
	val longitude: Double
)

internal fun LottoStoresDto.toDomain(): LottoStores {
	return LottoStores(
		id = id,
		name = name,
		address = address,
		latitude = latitude,
		longitude = longitude
	)
}
