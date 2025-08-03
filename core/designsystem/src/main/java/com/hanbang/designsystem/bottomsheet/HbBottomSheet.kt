package com.hanbang.designsystem.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 *
 * @author   JGeun
 * @created  2025/08/02
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HbBottomSheet(
	onDismissRequest: () -> Unit,
	modifier: Modifier = Modifier,
	colors: HbBottomSheetColors = HbBottomSheetDefaults.colors(),
	headerContents: @Composable () -> Unit = {},
	contents: @Composable () -> Unit
) {
	ModalBottomSheet(
		modifier = modifier,
		containerColor = colors.containerColor,
		sheetState =  rememberModalBottomSheetState(skipPartiallyExpanded = true),
		dragHandle = {},
		onDismissRequest = onDismissRequest
	) {
		BottomSheetContent(
			headerContents = headerContents,
			contents = contents,
		)
	}
}

@Composable
private fun BottomSheetContent(
	modifier: Modifier = Modifier,
	headerContents: @Composable () -> Unit,
	contents: @Composable () -> Unit,
) {
	Column(
		modifier = modifier
		.padding(horizontal = 24.dp)
		.padding(top = 24.dp, bottom = 20.dp)
	) {
		Row(modifier = Modifier.fillMaxWidth()) {
			headerContents()
		}

		Spacer(Modifier.height(8.dp))

		contents()
	}
}
