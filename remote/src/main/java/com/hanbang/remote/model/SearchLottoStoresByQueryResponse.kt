package com.hanbang.remote.model

import com.hanbang.data.model.LottoStoresDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @author   JGeun
 * @created  2026/01/11
 */
@Serializable
data class SearchLottoStoresByQueryResponseResult(
	@SerialName("results")
	val results: List<SearchLottoStoresByQueryResponse>
)

@Serializable
data class SearchLottoStoresByQueryResponse(
	@SerialName("id")
	val id: Long,
	@SerialName("name")
	val name: String,
	@SerialName("address")
	val address: String,
	@SerialName("latitude")
	val latitude: Double,
	@SerialName("longitude")
	val longitude: Double
)

internal fun SearchLottoStoresByQueryResponseResult.toDto(): List<LottoStoresDto> {
	return results.map { it.toDto() }
}

internal fun SearchLottoStoresByQueryResponse.toDto() = LottoStoresDto(
	id = id,
	name = name,
	address = address,
	latitude = latitude,
	longitude = longitude
)