package com.hanbang.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
internal fun HomeRoute(
	paddingValues: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
) {
	HomeScreen()
}

@Composable
private fun HomeScreen(

) {
	Box(
		modifier = Modifier
	) {
		Text(
			modifier = Modifier.align(Alignment.Center),
			text = "HomeScreen",
			style = SattoTheme.typography.body16Medium,
			color = Gray1
		)
	}
}