package com.hanbang.domain.usecase

import com.hanbang.domain.model.LottoStores
import com.hanbang.domain.repository.SattoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @author   JGeun
 * @created  2025/12/17
 */
class SearchLottoStoresByQueryUseCase @Inject constructor(
	private val sattoRepository: SattoRepository
) {
	operator fun invoke(
		searchQuery: String,
		limit: Int = 20
	): Flow<List<LottoStores>> {
		return sattoRepository.searchLottoStoresByQuery(
			query = searchQuery,
			limit = limit
		)
	}
}