package com.hanbang.map.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hanbang.designsystem.theme.Gray6
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.designsystem.util.noRippleClickable
import com.hanbang.domain.model.LottoStores
import com.hanbang.map.search.component.map.MapTopSearchBar
import com.hanbang.map.search.component.search.MapSearchInputField
import com.hanbang.map.search.component.search.SearchHintBox
import com.hanbang.map.search.component.search.SearchResultItem
import com.hanbang.map.search.model.MapSearchUiExceptionType
import com.hanbang.map.search.model.MapSearchUiState
import com.hanbang.map.search.model.MapSearchUiType
import com.hanbang.map.search.model.SearchSpecificData
import org.orbitmvi.orbit.compose.collectAsState

/**
 * @author   JGeun
 * @created  2025/12/16
 */
@Composable
fun MapSearchRoute(
	paddingValues: PaddingValues,
	viewModel: MapSearchViewModel = hiltViewModel(),
	onShowErrorSnackBar: (HbSnackBarType) -> Unit
) {
	val uiState = viewModel.collectAsState().value

	BackHandler(enabled = uiState.uiType == MapSearchUiType.SEARCH) {
		viewModel.onChangeUiType(MapSearchUiType.MAP)
	}

	MapSearchScreen(
		uiState = uiState,
		onSearchQueryChanged = viewModel::onSearchQueryChanged,
		onSearchSubmit = viewModel::onSearchSubmit,
		onChangeUiType = viewModel::onChangeUiType,
		paddingValues = paddingValues,
	)
}

@Composable
private fun MapSearchScreen(
	uiState: MapSearchUiState,
	paddingValues: PaddingValues,
	onChangeUiType: (MapSearchUiType) -> Unit,
	onSearchQueryChanged: (String) -> Unit,
	onSearchSubmit: () -> Unit,
) {
	when (uiState.uiType) {
		MapSearchUiType.MAP -> {
			MapScreen(
				modifier = Modifier
					.fillMaxSize()
					.padding(paddingValues),
				searchQuery = uiState.searchQuery,
				onClickMapTopSearchBar = {
					onChangeUiType(MapSearchUiType.SEARCH)
				}
			)
		}
		MapSearchUiType.SEARCH -> {
			SearchScreen(
				modifier = Modifier
					.fillMaxSize()
					.padding(paddingValues),
				searchQuery = uiState.searchQuery,
				searchResultList = uiState.searchResult,
				searchSpecificData = uiState.searchSpecificData,
				onSearchQueryChanged = onSearchQueryChanged,
				onClearSearchQuery = { onSearchQueryChanged("") },
				onSearchSubmit = onSearchSubmit,
			)
		}
	}
}

@Composable
private fun MapScreen(
	searchQuery: String,
	modifier: Modifier = Modifier,
	onClickMapTopSearchBar: () -> Unit = {}
) {
	Box(modifier = modifier.fillMaxSize()) {
		// 지도 화면
		Box(modifier = Modifier.fillMaxSize()) {
			Text(
				modifier = Modifier.align(Alignment.Center),
				text = "지도 화면 대체 예정."
			)
		}

		MapTopSearchBar(
			modifier = Modifier
				.align(Alignment.TopCenter)
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 6.5.dp)
				.noRippleClickable {
					onClickMapTopSearchBar()
				},
			searchQuery = searchQuery,
		)
	}
}

@Composable
private fun SearchScreen(
	searchQuery: String,
	searchResultList: List<LottoStores>,
	searchSpecificData: SearchSpecificData,
	modifier: Modifier = Modifier,
	onSearchQueryChanged: (String) -> Unit = {},
	onClearSearchQuery: () -> Unit = {},
	onSearchSubmit: () -> Unit = {},
) {
	val focusRequester = remember { FocusRequester() }

	LaunchedEffect(Unit) {
		focusRequester.requestFocus()
	}

	Column(
		modifier = modifier.fillMaxSize()
			.background(White)
	) {
		MapSearchInputField(
			keyword = searchQuery,
			onKeywordChange = onSearchQueryChanged,
			onClearKeyword = onClearSearchQuery,
			onSearchAction = onSearchSubmit,
			focusRequester = focusRequester,
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 16.dp, vertical = 6.5.dp)
		)

		when {
			searchSpecificData.isLoading -> {
				Box(
					modifier = Modifier.fillMaxSize(),
					contentAlignment = Alignment.Center
				) {
					CircularProgressIndicator(color = Primary2)
				}
			}
			searchQuery.isEmpty() -> {
				SearchHintBox(
					title = "어느 장소를 찾으세요?",
					message = "정확한 지명(구/동) 혹은\n상호명을 입력해 보시게",
				)
			}
			searchSpecificData.exceptionType != MapSearchUiExceptionType.NONE -> {
				SearchHintBox(
					title = searchSpecificData.exceptionType.title,
					message = searchSpecificData.exceptionType.message,
				)
			}
			else -> {
				LazyColumn(
					modifier = Modifier.fillMaxSize(),
					contentPadding = PaddingValues(vertical = 8.dp)
				) {
					itemsIndexed(
						searchResultList
					) { index, item ->
						SearchResultItem(
							title = item.name,
							address = item.address,
							searchQuery = searchQuery
						)

						if (index != searchResultList.lastIndex) {
							Spacer(
								Modifier
									.fillMaxWidth()
									.height(1.dp)
									.background(Gray6)
							)
						}
					}
				}
			}
		}
	}
}
