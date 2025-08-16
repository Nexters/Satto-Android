package com.hanbang.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanbang.domain.usecase.GetUserIdUseCase
import com.hanbang.domain.usecase.GetUserUseCase
import com.hanbang.intro.model.NextScreenType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@HiltViewModel
class IntroViewModel @Inject constructor(
	private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel() {

	private val _nextScreenFlow: MutableStateFlow<NextScreenType> = MutableStateFlow(NextScreenType.NONE)
	val nextScreenFlow = _nextScreenFlow.asStateFlow()

	fun checkNextScreen() {
		viewModelScope.launch {
			val userIdDeferred = async { getUserIdUseCase() }
			val splashTimeDeferred = async { delay(SPLASH_TIME) }

			val userId = awaitAll(userIdDeferred, splashTimeDeferred).firstOrNull() as? String ?: ""
			_nextScreenFlow.value = if (userId.isNotEmpty()) {
				NextScreenType.HOME
			} else {
				NextScreenType.LOGIN
			}
		}
	}

	companion object {
		private const val SPLASH_TIME = 3000L
	}
}