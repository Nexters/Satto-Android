package com.hanbang.history

import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
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
	val webViewUrl = "https://clever-kataifi-dcedaf.netlify.app/lotto-history"

	Box(
		modifier = Modifier.fillMaxSize()
			.padding(paddingValues)
	) {
		AndroidView(
			modifier = Modifier
				.fillMaxSize(),
			factory = {
				val myWebView = WebView(it).apply {
					settings.run {
						javaScriptEnabled = true
						domStorageEnabled = true
						loadsImagesAutomatically = true
						mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
					}
				}

				myWebView.apply {
					layoutParams = ViewGroup.LayoutParams(
						ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT
					)
				}
			},
			update = { view ->
				if (view.url != webViewUrl) {
					view.loadUrl(webViewUrl)
				}
			}
		)
	}
}
