package com.hanbang.fortune.component

import androidx.annotation.FloatRange
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.theme.Primary4
import com.hanbang.designsystem.theme.Primary6
import com.hanbang.designsystem.theme.SattoTheme

/**
 *
 * @author   JGeun
 * @created  2025/08/16
 */
@Composable
internal fun FortuneLuckArc(
	@FloatRange(0.0, 1.0) value: Float,
	modifier: Modifier = Modifier,
) {
	val animatedValue by animateFloatAsState(
		targetValue = value,
		animationSpec = tween(3000)
	)

	Box(
		modifier = modifier
	) {
		Canvas(modifier = Modifier.matchParentSize()) {
			val strokeWidth = 20.dp.toPx()

			translate(
				left = strokeWidth / 2,
				top = strokeWidth / 2
			) {
				drawArc(
					color = Primary6,
					size = Size(size.width - strokeWidth, (size.height - strokeWidth) * 2),
					startAngle = 180f,
					sweepAngle = 180f,
					style = Stroke(
						strokeWidth,
						cap = StrokeCap.Butt
					),
					useCenter = false
				)


				drawArc(
					color = Primary4,
					size = Size(size.width - strokeWidth, (size.height - strokeWidth) * 2),
					startAngle = 180f,
					sweepAngle = 180f * animatedValue,
					style = Stroke(
						strokeWidth + 20,
						cap = StrokeCap.Butt
					),
					useCenter = false
				)
			}
		}
	}
}

@Preview
@Composable
private fun FortuneLuckArcPreview() {
	SattoTheme {
		FortuneLuckArc(
			value = 0.7f,
			modifier = Modifier.width(155.dp)
		)
	}
}