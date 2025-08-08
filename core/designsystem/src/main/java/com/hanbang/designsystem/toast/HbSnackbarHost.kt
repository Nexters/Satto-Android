package com.hanbang.designsystem.toast

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Gray2
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import kotlinx.coroutines.launch

/**
 *
 * @author   JGeun
 * @created  2025/08/09
 */
@Composable
fun HbToastSnackbarHost(
	hostState: SnackbarHostState,
	modifier: Modifier = Modifier,
	snackBarType: HbSnackBarType = HbSnackBarType.NOTICE(),
) {
	SnackbarHost(
		hostState = hostState,
		modifier = modifier
			.padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
			.alpha(0.9f),
	) { data ->
		Card(
			shape = RoundedCornerShape(10.dp),
			colors = CardDefaults.cardColors(
				containerColor = Gray2,
				contentColor = White,
			),
		) {
			Row(
				modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
				verticalAlignment = Alignment.CenterVertically,
			) {
				if (snackBarType.leadingIconRes != null) {
					Image(
						modifier = Modifier.width(16.dp).height(20.dp),
						painter = painterResource(snackBarType.leadingIconRes),
						contentDescription = "Leading Icon",
					)
					Spacer(modifier = Modifier.padding(end = 8.dp))
				}

				Text(
					text = data.visuals.message,
					style = SattoTheme.typography.body14Bold,
				)
			}
		}
	}
}

suspend fun showMnSnackbar(
	snackbarHostState: SnackbarHostState,
	message: String,
	actionLabel: String? = null,
	duration: SnackbarDuration = SnackbarDuration.Short,
	actionPerformed: () -> Unit = {},
	dismissed: () -> Unit = {},
) {
	val result = snackbarHostState.showSnackbar(
		message = message,
		actionLabel = actionLabel,
		duration = duration,
	)

	when (result) {
		SnackbarResult.ActionPerformed -> actionPerformed()
		SnackbarResult.Dismissed -> dismissed()
	}
}

@Preview
@Composable
private fun PreviewToast() {
	SattoTheme {
		val snackbarHostState = remember { SnackbarHostState() }

		val scope = rememberCoroutineScope()

		Scaffold (
			snackbarHost = {
				HbToastSnackbarHost(
					hostState = snackbarHostState,
					snackBarType = HbSnackBarType.NORMAL,
				)
			},
		) { padding ->
			Box(
				modifier = Modifier
					.background(Color.Yellow)
					.fillMaxSize(),
			) {
				Button(onClick = {
					scope.launch {
						showMnSnackbar(
							snackbarHostState = snackbarHostState,
							message = "사또 테스트입니다요",
							actionLabel = "확인"
						)
					}
				}) { Text(text = "show Toast") }
			}
		}
	}
}