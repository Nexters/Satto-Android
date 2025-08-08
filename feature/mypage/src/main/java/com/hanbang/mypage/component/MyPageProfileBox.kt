package com.hanbang.mypage.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Gray3
import com.hanbang.designsystem.theme.Gray5
import com.hanbang.designsystem.theme.Gray6
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White
import com.hanbang.designsystem.util.noRippleClickable

/**
 *
 * @author   JGeun
 * @created  2025/08/05
 */
@Composable
fun MyPageProfileBox(
	modifier: Modifier = Modifier,
	onEditProfile: () -> Unit = { }
) {
	Column(
		modifier = modifier.fillMaxWidth()
			.background(color = White, shape = RoundedCornerShape(10.dp))
			.padding(20.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Box(
			modifier = Modifier.size(88.dp)
				.clip(CircleShape)
		) {
			Image(
				modifier = Modifier.fillMaxSize(),
				painter = painterResource(R.drawable.img_my_page_base_thumbnail),
				contentDescription = null
			)
		}

		Spacer(Modifier.height(16.dp))

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Text(
				text = "이름",
				style = SattoTheme.typography.headline20Bold,
				color = Gray1
			)

			Spacer(Modifier.width(4.dp))

			Icon(
				modifier = Modifier.size(18.dp)
					.noRippleClickable { onEditProfile() },
				painter = painterResource(R.drawable.ic_edit_pencil),
				contentDescription = null,
				tint = Gray5
			)
		}

		Spacer(Modifier.height(8.dp))

		Row(
			modifier = Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center
		) {
			Text(
				text = "여",
				style = SattoTheme.typography.caption12Bold,
				color = Gray3
			)

			Spacer(Modifier.width(4.dp))

			Icon(
				modifier = Modifier.size(3.dp),
				painter = painterResource(R.drawable.ic_dot),
				contentDescription = null,
				tint = Gray6
			)

			Spacer(Modifier.width(4.dp))

			Text(
				text = "1999-12-25",
				style = SattoTheme.typography.caption12Bold,
				color = Gray3
			)

			Spacer(Modifier.width(4.dp))

			Icon(
				modifier = Modifier.size(3.dp),
				painter = painterResource(R.drawable.ic_dot),
				contentDescription = null,
				tint = Gray6
			)

			Spacer(Modifier.width(4.dp))

			Text(
				text = "01:00 ~ 02:59",
				style = SattoTheme.typography.caption12Bold,
				color = Gray3
			)
		}
	}
}

@Preview
@Composable
private fun MyPageProfileBoxPreview() {
	SattoTheme {
		MyPageProfileBox()
	}
}
