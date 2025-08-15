package com.hanbang.onboarding.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun StaggeredAppearItem(
	stage: Int,
	index: Int,
	itemHeight: Dp,
	content: @Composable () -> Unit
) {
	val appear = remember(index) { MutableTransitionState(false) }
	val topPadding by animateDpAsState(
		targetValue = itemHeight, //if (stage > index) ((stage - index) * itemHeight) else 0.dp,
		animationSpec = tween(200)
	)

	LaunchedEffect(stage) {
		if (stage >= index) {
			appear.targetState = true
		}
	}

	AnimatedVisibility(
		visibleState = appear,
		enter = slideInVertically(
			initialOffsetY = { 0 }
		) + fadeIn(animationSpec = tween(600)),
		modifier = Modifier
			.padding(top = topPadding)
			.fillMaxWidth()
	) {
		content()
	}
}