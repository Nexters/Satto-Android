package com.hanbang.designsystem.bottomsheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
fun AnimatedBottomDialog(
	onDismiss: () -> Unit,
	content: @Composable (closeDialog: () -> Unit) -> Unit
) {
	val scope = rememberCoroutineScope()
	var visible by remember { mutableStateOf(false) }

	Dialog(
		onDismissRequest = {
			// 배경 클릭 시 exit 애니메이션 후 닫기
			scope.launch {
				visible = false
				delay(300) // exit 애니메이션 시간과 동일
				onDismiss()
			}
		},
		properties = DialogProperties(usePlatformDefaultWidth = false)
	) {

		LaunchedEffect(Unit) { visible = true }

		Box(
			Modifier
				.fillMaxSize()
				.background(Color.Black.copy(alpha = 0.2f))
				.clickable(
					indication = null,
					interactionSource = remember { MutableInteractionSource() }
				) {
					// 배경 클릭 시 exit 애니메이션 후 닫기
					scope.launch {
						visible = false
						delay(300) // exit 애니메이션 시간과 동일
						onDismiss()
					}
				}
		) {
			AnimatedVisibility(
				visible = visible,
				enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
				exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
				modifier = Modifier.align(Alignment.BottomCenter)
			) {
				Box(
					Modifier
						.fillMaxWidth()
						.wrapContentHeight()
						.background(Color.White, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
						.padding(16.dp)
				) {
					content {
						scope.launch {
							visible = false
							delay(300) // exit 애니메이션 시간과 동일
							onDismiss()
						}
					}
				}
			}
		}
	}
}