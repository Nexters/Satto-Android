package com.hanbang.editprofile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.Gray4
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White

/**
 *
 * @author   JGeun
 * @created  2025/08/15
 */
@Composable
internal fun EditProfileNavigateUpMiddleDialog(
	onConfirm: () -> Unit,
	onDismiss: () -> Unit
) {
	MiddleDialog(
		onDismiss = onDismiss
	) {
		Column(
			modifier = Modifier
				.padding(horizontal = 24.dp)
				.background(color = White, shape = RoundedCornerShape(10.dp))
				.padding(24.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		){
			Text(
				text = "수정 중인 내용이 있소",
				style = SattoTheme.typography.headline20Bold,
				color = Gray1
			)

			Spacer(Modifier.height(8.dp))

			Text(
				text = "저장하지 않고 화면을 벗어나면\n감쪽같이 사라질 것이오",
				style = SattoTheme.typography.body16Medium,
				color = Gray4,
				textAlign = TextAlign.Center
			)

			Spacer(Modifier.height(24.dp))

			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(8.dp)
			) {
				HbBoxButton(
					text  = "나가기",
					modifier = Modifier.weight(1f),
					colors = HbButtonColorType.border,
					styles = HbButtonStyles.large,
					onClick = onConfirm
				)

				HbBoxButton(
					text  = "계속 수정하기",
					modifier = Modifier.weight(1f),
					colors = HbButtonColorType.primary,
					styles = HbButtonStyles.large,
					onClick = onDismiss
				)
			}
		}
	}
}