package com.hanbang.map.search.model

import androidx.compose.runtime.Stable
import com.hanbang.domain.model.LottoStores

/**
 * @author   JGeun
 * @created  2025/12/16
 */
@Stable
data class MapSearchUiState(
	val uiType: MapSearchUiType = MapSearchUiType.MAP,
	// 공통 데이터
	val searchQuery: String = "",
	val searchResult: List<LottoStores> = emptyList(),
	// 화면별 특화 데이터
	val mapSpecificData: MapSpecificData = MapSpecificData(),
	val searchSpecificData: SearchSpecificData = SearchSpecificData()
)

@Stable
data class MapSpecificData(
	val zoomLevel: Float = 15f
)

@Stable
data class SearchSpecificData(
	val recentSearches: List<String> = emptyList(),
	val exceptionType: MapSearchUiExceptionType = MapSearchUiExceptionType.NONE,
	val isLoading: Boolean = false,
)