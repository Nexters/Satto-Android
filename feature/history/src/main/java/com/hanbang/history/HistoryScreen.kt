package com.hanbang.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.toast.HbSnackBarType

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
internal fun HistoryRoute(
	paddingValues: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
) {
	HistoryScreen(
		paddingValues = paddingValues,
		onShowErrorSnackBar = onShowErrorSnackBar
	)
}

@Composable
private fun HistoryScreen(
	paddingValues: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
) {
	Column(
		modifier = Modifier
	) {
		Text(
			modifier = Modifier,
			text = "HistoryScreen",
			style = SattoTheme.typography.body16Medium,
			color = Gray1
		)

		Button(
			onClick = { onShowErrorSnackBar(HbSnackBarType.NOTICE("History Screen Notice")) }
		) {
			Text(text = "Show ToastSnackBar")
		}

		Button(
			onClick = { onShowErrorSnackBar(HbSnackBarType.ERROR("History Screen Error")) }
		) {
			Text(text = "Show ToastSnackBar")
		}

		Button(
			onClick = { onShowErrorSnackBar(HbSnackBarType.SUCCESS("History Screen SUCCESS")) }
		) {
			Text(text = "Show ToastSnackBar")
		}

		Button(
			onClick = { onShowErrorSnackBar(HbSnackBarType.WARNING("History Screen WARNING")) }
		) {
			Text(text = "Show ToastSnackBar")
		}
	}
}