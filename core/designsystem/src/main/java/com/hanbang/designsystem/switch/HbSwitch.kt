package com.hanbang.designsystem.switch

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.util.clickableSingle

/**
 *
 * @author   JGeun
 * @created  2025/07/26
 */
private data class HbSwitchColors(
	val checkedThumbColor: Color,
	val unCheckedThumbColor: Color,
	val checkedTrackColor: Color,
	val unCheckedTrackColor: Color
)

@Composable
fun HbSwitch(
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit,
	modifier: Modifier = Modifier,
	isEnabled: Boolean = true,
) {

	val colors = when (isEnabled) {
		true -> {
			HbSwitchColors(
				checkedThumbColor = Color(0xFFFFFFFF),
				unCheckedThumbColor = Color(0xFFFFFFFF),
				checkedTrackColor = Color(0xFF6A00FF),
				unCheckedTrackColor = Color(0xFFCFD6D9)
			)
		}

		else -> {
			HbSwitchColors(
				checkedThumbColor = Color(0xFFFFFFFF),
				unCheckedThumbColor = Color(0xFFFFFFFF),
				checkedTrackColor = Color(0xFFF7F5FF),
				unCheckedTrackColor = Color(0xFFF7F9FA)
			)
		}
	}

	Switch(
		modifier = Modifier,
		checked = checked,
		onCheckedChange = onCheckedChange,
		thumbContent = {
			Box(
				modifier = Modifier
					.size(24.dp)
					.clip(CircleShape)
			)
		},
		colors = SwitchDefaults.colors(
			checkedThumbColor = colors.checkedThumbColor,
			uncheckedThumbColor = colors.unCheckedThumbColor,
			checkedTrackColor = colors.checkedTrackColor,
			uncheckedTrackColor = colors.unCheckedTrackColor,

		)
	)
}

@Composable
fun PureCustomSwitch(
	checked: Boolean,
	onCheckedChange: (Boolean) -> Unit,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	width: Dp = 50.dp,
	height: Dp = 28.dp,
	thumbDiameter: Dp = 24.dp,
	padding: Dp = 2.dp,
) {
	val trackColor by animateColorAsState(
		targetValue = when {
			!enabled -> if (checked) Color(0xFFF7F5FF) else Color(0xFFF7F9FA)
			checked -> Color(0xFF6A00FF)
			else -> Color(0xFFCFD6D9)
		},
		label = "Track Color"
	)

	val thumbColor by animateColorAsState(
		targetValue = Color.White,
		label = "Thumb Color"
	)

	val thumbOffset by animateDpAsState(
		targetValue = if (checked)
			width - thumbDiameter - padding
		else
			padding,
		label = "Thumb Offset"
	)

	Box(
		modifier = modifier
			.size(width, height)
			.clip(RoundedCornerShape(100.dp))
			.background(trackColor)
			.clickableSingle(
				enabled = enabled,
				activeRippleEffect = false
			) { onCheckedChange(!checked) },
		contentAlignment = Alignment.CenterStart
	) {
		Box(
			modifier = Modifier
				.offset { IntOffset(x = thumbOffset.toPx().toInt(), y= 0) }
				.size(thumbDiameter)
				.clip(CircleShape)
				.background(thumbColor)
		)
	}
}

@Preview
@Composable
private fun HbSwitchPreview() {
	var isChecked by remember { mutableStateOf(false) }

	HbSwitch(
		checked = isChecked,
		onCheckedChange = {
			isChecked = !isChecked
		}
	)
}

@Preview
@Composable
private fun PureCustomSwitchPreview() {
	var isChecked by remember { mutableStateOf(false) }

	PureCustomSwitch(
		checked = isChecked,
		onCheckedChange = {
			isChecked = !isChecked
		}
	)
}