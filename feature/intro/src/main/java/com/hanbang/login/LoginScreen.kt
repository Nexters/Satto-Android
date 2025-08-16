package com.hanbang.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.theme.Primary2
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White

/**
 *
 * @author   JGeun
 * @created  2025/08/17
 */
@Composable
internal fun LoginRoute(
	paddingValues: PaddingValues,
	navigateToOnboarding: () -> Unit
) {
	LoginScreen(
		paddingValues = paddingValues,
		navigateToOnboarding = navigateToOnboarding
	)
}

@Composable
private fun LoginScreen(
	paddingValues: PaddingValues,
	navigateToOnboarding: () -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(White)
			.padding(
				bottom = paddingValues.calculateBottomPadding() + 24.dp,
				top = paddingValues.calculateTopPadding() + 66.dp
			)
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.height(50.dp)
				.padding(horizontal = 40.dp)
		) {
			Image(
				modifier = Modifier.fillMaxSize(),
				painter = painterResource(R.drawable.img_satto_logo_login),
				contentScale = ContentScale.FillBounds,
				contentDescription = "logo_login",
			)
		}

		Spacer(Modifier.height(24.dp))

		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 40.dp),
			text  = "복을 가득 담아\n보내드리네",
			style = SattoTheme.typography.display28Bold,
			color  = Primary2
		)

		Spacer(Modifier.weight(1f))

		HbBoxButton(
			modifier = Modifier.fillMaxWidth()
				.padding(horizontal = 24.dp),
			text = "시작하기",
			colors = HbButtonColorType.primary,
			styles = HbButtonStyles.xLarge,
			onClick = navigateToOnboarding,
		)
	}
}
