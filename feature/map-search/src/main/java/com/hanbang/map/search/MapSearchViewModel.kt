package com.hanbang.map.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.domain.model.LottoStores
import com.hanbang.domain.usecase.SearchLottoStoresByQueryUseCase
import com.hanbang.map.search.model.MapSearchSideEffect
import com.hanbang.map.search.model.MapSearchUiExceptionType
import com.hanbang.map.search.model.MapSearchUiState
import com.hanbang.map.search.model.MapSearchUiType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.blockingIntent
import org.orbitmvi.orbit.viewmodel.container
import java.io.IOException
import javax.inject.Inject

/**
 * @author   JGeun
 * @created  2025/12/16
 */
@HiltViewModel
class MapSearchViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val searchLottoStoresByQueryUseCase: SearchLottoStoresByQueryUseCase
) : ViewModel(), ContainerHost<MapSearchUiState, MapSearchSideEffect> {

	override val container = container<MapSearchUiState, MapSearchSideEffect>(
		MapSearchUiState(searchQuery = savedStateHandle.get<String>(KEY_SEARCH_QUERY) ?: "")
	)

	private var debounceJob: Job? = null

	fun onSearchQueryChanged(query: String) {
		blockingIntent {
			savedStateHandle[KEY_SEARCH_QUERY] = query
			reduce {
				state.copy(
					searchQuery = query,
					searchSpecificData = state.searchSpecificData.copy(
						exceptionType = MapSearchUiExceptionType.NONE,
					)
				)
			}
		}
		debounceJob?.cancel()
		if (query.isNotBlank()) {
			debounceJob = viewModelScope.launch {
				delay(DEBOUNCE_DURATION_MS)
				onSearchSubmit(false)
			}
		}
	}

	fun onSearchSubmit(showLoading: Boolean = true) = intent {
		debounceJob?.cancel()
		val query = state.searchQuery
		if (query.isBlank()) return@intent

		reduce {
			state.copy(
				searchSpecificData = state.searchSpecificData.copy(
					isLoading = showLoading,
					exceptionType = MapSearchUiExceptionType.NONE,
				)
			)
		}

		val startTime = System.currentTimeMillis()

		searchLottoStoresByQueryUseCase(query)
			.map { stores ->
				if (stores.isEmpty()) {
					emptyList<LottoStores>() to MapSearchUiExceptionType.EMPTY_RESULT
				} else {
					stores to MapSearchUiExceptionType.NONE
				}
			}
			.catch { exception ->
				val exceptionType = when (exception) {
					is IOException -> MapSearchUiExceptionType.NETWORK_ERROR
					else -> MapSearchUiExceptionType.UNKNOWN_ERROR
				}
				emit(emptyList<LottoStores>() to exceptionType)
			}
			.collect { (stores, exceptionType) ->
				val elapsed = System.currentTimeMillis() - startTime
				if (elapsed < MIN_LOADING_DURATION_MS) {
					delay(MIN_LOADING_DURATION_MS - elapsed)
				}
				reduce {
					state.copy(
						searchResult = stores,
						searchSpecificData = state.searchSpecificData.copy(
							isLoading = false,
							exceptionType = exceptionType,
						)
					)
				}
			}
	}

	fun onChangeUiType(uiType: MapSearchUiType) = intent {
		reduce { state.copy(uiType = uiType) }
	}

	companion object {
		private const val KEY_SEARCH_QUERY = "KEY_SEARCH_QUERY"
		private const val MIN_LOADING_DURATION_MS = 500L
		private const val DEBOUNCE_DURATION_MS = 2000L
	}
}