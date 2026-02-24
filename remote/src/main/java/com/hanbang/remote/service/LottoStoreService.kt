package com.hanbang.remote.service

import com.hanbang.remote.model.SearchLottoStoresByQueryResponseResult
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author   JGeun
 * @created  2026/01/11
 */
interface LottoStoreService {

	/**
	 * query length: 1 ~ 100
	 * limit: 1 ~ 50
	 */
	@GET("/atm/search")
	suspend fun searchLottoStoresByQuery(
		@Query("query") query: String,
		@Query("limit") limit: Int = 20,
	): SearchLottoStoresByQueryResponseResult
}