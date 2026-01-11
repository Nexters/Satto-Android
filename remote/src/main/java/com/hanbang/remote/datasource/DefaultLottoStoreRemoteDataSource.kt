package com.hanbang.remote.datasource

import com.hanbang.data.datasource.LottoStoreRemoteDataSource
import com.hanbang.data.model.LottoStoresDto
import com.hanbang.remote.model.toDto
import com.hanbang.remote.service.LottoStoreService
import javax.inject.Inject

/**
 * @author   JGeun
 * @created  2026/01/11
 */
class DefaultLottoStoreRemoteDataSource @Inject constructor(
	private val lottoStoreService: LottoStoreService
) : LottoStoreRemoteDataSource {

	override suspend fun searchLottoStoresByQuery(
		query: String,
		limit: Int
	): List<LottoStoresDto> {
		return lottoStoreService.searchLottoStoresByQuery(
			query = query,
			limit = limit
		).toDto()
	}
}
