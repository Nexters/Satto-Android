package com.hanbang.editprofile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun MiddleDialog(
	onDismiss: () -> Unit,
	content: @Composable () -> Unit
) {
	Dialog(
		onDismissRequest = onDismiss,
		properties = DialogProperties(usePlatformDefaultWidth = false)
	) {
		Box(
			Modifier
				.fillMaxSize()
				.background(Color.Black.copy(alpha = 0.2f))
				.clickable(
					indication = null,
					interactionSource = remember { MutableInteractionSource() }
				) {
					onDismiss()
				}
		) {
			Box(
				modifier = Modifier.align(Alignment.Center)
			) {
				content()
			}
		}
	}
}