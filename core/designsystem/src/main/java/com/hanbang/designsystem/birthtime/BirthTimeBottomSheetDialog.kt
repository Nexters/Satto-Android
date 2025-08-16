package com.hanbang.designsystem.birthtime

import android.content.Context
import android.util.TypedValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.hanbang.designsystem.R
import com.hanbang.designsystem.bottomsheet.AnimatedBottomDialog
import com.hanbang.designsystem.bottomsheet.HbBottomSheetItem
import com.hanbang.designsystem.button.HbBoxButton
import com.hanbang.designsystem.button.HbButtonColorType
import com.hanbang.designsystem.button.HbButtonStyles
import com.hanbang.designsystem.theme.Gray1
import com.hanbang.designsystem.theme.LocalSattoTypography
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/08/03
 */
@Composable
fun BirthTimeBottomSheetDialog(
	initialTime: String,
	timeList: List<String>,
	onDismissRequest: () -> Unit,
	onSelectBirthTime: (String) -> Unit,
) {
	var isActiveTime by remember { mutableStateOf(initialTime) }
	val scrollState = rememberScrollState(
		initial = 0
	)

	val context = LocalContext.current

	LaunchedEffect(Unit) {
		val index = timeList.indexOfFirst { it == initialTime }.coerceAtLeast(0)
		val scrollValue = index * dpToPx(context,56f)
		scrollState.animateScrollTo(scrollValue.toInt())
	}

	AnimatedBottomDialog(
		onDismiss = onDismissRequest,
		content = { closeDialog ->
			Column(
				modifier = Modifier.fillMaxWidth()
			) {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(vertical = 8.dp)) {
					Text(
						modifier = Modifier.weight(1f),
						text = "태어난 시",
						style = LocalSattoTypography.current.body16Bold,
						color = Gray1
					)

					Icon(
						modifier = Modifier
							.size(24.dp)
							.clickableSingle(activeRippleEffect = false) { closeDialog() },
						painter = painterResource(R.drawable.ic_close),
						contentDescription = "close_icon"
					)
				}

				Column(
					modifier = Modifier
						.fillMaxWidth()
						.heightIn(max = 4 * 56.dp)
						.verticalScroll(scrollState)
				) {
					timeList.forEach { time ->
						HbBottomSheetItem(
							text = time,
							onClick = { isActiveTime = time },
							isActive = time == isActiveTime
						)
					}
				}

				Spacer(Modifier.height(28.dp))

				HbBoxButton(
					text = "선택 완료",
					onClick = {
						onSelectBirthTime(isActiveTime)
						onDismissRequest()
					},
					colors = HbButtonColorType.primary,
					styles = HbButtonStyles.xLarge,
				)
			}
		}
	)
}

fun dpToPx(context: Context, dp: Float): Float {
	val dm = context.resources.displayMetrics
	return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm)
}
