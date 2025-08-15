package com.hanbang.designsystem.extension

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
inline fun <reified T> Flow<T>.collectWithLifecycle(
	minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
	noinline action: suspend (T) -> Unit
) {
	val lifecycleOwner = LocalLifecycleOwner.current

	LaunchedEffect(this, lifecycleOwner) {
		lifecycleOwner.lifecycle.repeatOnLifecycle(minActiveState) {
			this@collectWithLifecycle.collect { action(it) }
		}
	}
}