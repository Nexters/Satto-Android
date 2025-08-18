package com.hanbang.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Primary2

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
fun SattoSplashScreen() {
	Box(
		modifier = Modifier.fillMaxSize()
			.background(Primary2)
	) {
		Image(
			modifier = Modifier.width(159.dp).height(44.dp)
				.align(Alignment.Center),
			painter = painterResource(R.drawable.img_satto_logo_splash),
			contentDescription = "Satto Logo",
		)
	}
}