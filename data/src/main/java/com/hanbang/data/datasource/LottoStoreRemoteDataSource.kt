package com.hanbang.data.datasource

import com.hanbang.data.model.LottoStoresDto

/**
 * @author   JGeun
 * @created  2026/01/11
 */
interface LottoStoreRemoteDataSource {

	suspend fun searchLottoStoresByQuery(
		query: String,
		limit: Int = 20
	): List<LottoStoresDto>
}
