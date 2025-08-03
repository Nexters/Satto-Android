package com.hanbang.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.SattoTheme

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
internal fun HistoryRoute(
	paddingValues: PaddingValues,
	onShowErrorSnackBar: (throwable: Throwable?) -> Unit,
) {
	HistoryScreen()
}

@Composable
private fun HistoryScreen(

) {
	Box(
		modifier = Modifier
	) {
		Text(
			modifier = Modifier.align(Alignment.Center),
			text = "HistoryScreen",
			style = SattoTheme.typography.body16Medium,
			color = Gray1
		)
	}
}