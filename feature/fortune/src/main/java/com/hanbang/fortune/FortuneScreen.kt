package com.hanbang.fortune

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtMost
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Primary8
import com.hanbang.designsystem.theme.Primary9
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.toast.HbSnackBarType
import com.hanbang.fortune.component.FortuneComprehensiveSection
import com.hanbang.fortune.component.FortuneHeader
import com.hanbang.fortune.component.FortuneLuckInfoMoreSection
import com.hanbang.fortune.component.FortuneSajuOverview
import com.hanbang.fortune.component.FortuneTodayLuckSection

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
internal fun FortuneRoute(
	paddingValues: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
) {
	FortuneScreen(
		paddingValues = paddingValues,
		onShowErrorSnackBar = onShowErrorSnackBar
	)
}

@Composable
private fun FortuneScreen(
	paddingValues: PaddingValues,
	onShowErrorSnackBar: (HbSnackBarType) -> Unit,
) {
	LazyColumn(
		modifier = Modifier.fillMaxSize()
			.padding(bottom = paddingValues.calculateBottomPadding())
	) {
		item {
			Box(
				modifier = Modifier.fillMaxWidth()
					.background(Primary9)
			) {
				BoxWithConstraints(
					modifier = Modifier.fillMaxWidth()
				) {
					// w:h = 264: 286
					Box(
						modifier = Modifier.align(Alignment.TopEnd)
							.width((maxWidth * 264 / 375).coerceAtMost(264.dp))
							.aspectRatio(264f / 286f)
							.background(Primary9)
					) {
						Image(
							modifier = Modifier.fillMaxSize(),
							painter = painterResource(R.drawable.img_fortune_bg_circle),
							contentDescription = null,
							contentScale = ContentScale.FillBounds
						)
					}
				}

				Column(
					Modifier.fillMaxWidth()
						.padding(top = paddingValues.calculateTopPadding())
				) {
					FortuneHeader()

					FortuneTodayLuckSection()

					Spacer(Modifier.height(32.dp))
				}
			}
		}

		item {
			Column(
				modifier = Modifier.fillMaxWidth()
			) {
				Image(
					modifier = Modifier.fillMaxWidth()
						.height(16.dp),
					painter = painterResource(R.drawable.img_fortune_wavve_background),
					contentDescription = "",
					contentScale = ContentScale.FillBounds
				)

				Column(
					modifier = Modifier.fillMaxWidth()
						.background(Primary8)
				) {
					FortuneComprehensiveSection(
						modifier = Modifier
							.background(Primary8)
							.padding(horizontal = 24.dp)
					)

					Spacer(Modifier.height(32.dp))

					FortuneSajuOverview(
						name = "콩떡님",
						dateOfBirth = "2025-08-10",
						birthTime = "10:00",
						modifier = Modifier
							.background(Primary8)
							.padding(horizontal = 24.dp)
					)

					Spacer(Modifier.height(32.dp))

					FortuneLuckInfoMoreSection(
						modifier = Modifier
							.background(Primary8)
							.padding(horizontal = 24.dp)
					)

					Spacer(Modifier.height(32.dp))
				}
			}
		}
	}
}

@Preview(backgroundColor = 0xFFF7F5FF, showBackground = true)
@Composable
private fun FortuneScreenPreview() {
	SattoTheme {
		FortuneScreen(
			paddingValues = PaddingValues(0.dp),
			onShowErrorSnackBar = {}
		)
	}
}