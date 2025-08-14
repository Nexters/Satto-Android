package com.hanbang.fortune.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
fun FortuneArcGauge(
	modifier: Modifier = Modifier,
	backgroundColor: Color = Color(0xFFF1ECFF),     // 연보라 배경
	colors: List<Color> = listOf(
		Color(0xFFEFE4FF), Color(0xFFB58CFF), Color(0xFF7A58FF), Color(0xFF9E7BFF)
	),                                               // 보라 계열 그라데이션
	startAngleDeg: Float = 210f,                     // 시작 각도(시계방향, 0=오른쪽)
	sweepAngleDeg: Float = 120f,                     // 그리는 각도
	strokeWidth: Dp = 24.dp,                         // 굵기
	glowWidthFactor: Float = 1.8f,                   // 글로우 두께 배수(>1.0)
	showTip: Boolean = true                          // 끝 팁 표시
) {
	Box(modifier.background(backgroundColor)) {
		Canvas(modifier = Modifier.matchParentSize()) {
			val w = size.width
			val h = size.height
			val minSide = minOf(w, h)
			val stroke = strokeWidth.toPx()
			val radius = (minSide / 2f) - stroke

			val center = Offset(w * 0.4f, h * 0.9f) // 이미지 비율처럼 살짝 왼쪽-아래로 중심 치우치기
			val rect = Rect(
				center.x - radius,
				center.y - radius,
				center.x + radius,
				center.y + radius
			)

			// 1) 소프트 글로우(뒤에 큰, 옅은 아크)
			drawArc(
				brush = Brush.sweepGradient(
					center = center,
					colors = colors
				),
				startAngle = startAngleDeg,
				sweepAngle = sweepAngleDeg,
				useCenter = false,
				style = Stroke(width = stroke * glowWidthFactor, cap = StrokeCap.Round),
				alpha = 0.25f,
				topLeft = rect.topLeft,
				size = rect.size
			)

			// 2) 메인 아크(선명)
			drawArc(
				brush = Brush.sweepGradient(
					center = center,
					colors = colors
				),
				startAngle = startAngleDeg,
				sweepAngle = sweepAngleDeg,
				useCenter = false,
				style = Stroke(width = stroke, cap = StrokeCap.Round),
				topLeft = rect.topLeft,
				size = rect.size
			)

			// 3) 안쪽 하이라이트(살짝 흰 기)
			drawArc(
				color = Color.White,
				startAngle = startAngleDeg + 2f,
				sweepAngle = sweepAngleDeg - 4f,
				useCenter = false,
				style = Stroke(width = stroke * 0.35f, cap = StrokeCap.Round),
				alpha = 0.20f,
				topLeft = rect.inflate(-stroke * 0.18f).topLeft,
				size = rect.inflate(-stroke * 0.18f).size
			)

			if (showTip) {
				// 끝점 좌표 계산
				val endAngleRad = Math.toRadians((startAngleDeg + sweepAngleDeg).toDouble())
				val endR = radius
				val end = Offset(
					x = center.x + endR * cos(endAngleRad).toFloat(),
					y = center.y + endR * sin(endAngleRad).toFloat()
				)

				// 아크 접선 방향 각도
				val tangentDeg = (startAngleDeg + sweepAngleDeg) + 90f

				// 끝 팁(둥근 사각형을 접선 방향으로 회전)
				val tipW = stroke * 0.95f
				val tipH = stroke * 0.55f
				val tipRect = Rect(
					end.x - tipW / 2f,
					end.y - tipH / 2f,
					end.x + tipW / 2f,
					end.y + tipH / 2f
				)

				rotate(degrees = tangentDeg, pivot = end) {
					drawRoundRect(
						brush = Brush.linearGradient(listOf(colors.last(), Color.White.copy(alpha = 0.35f))),
						topLeft = tipRect.topLeft,
						size = tipRect.size,
						cornerRadius = CornerRadius(tipH / 2f, tipH / 2f),
						alpha = 0.95f
					)
				}
			}
		}
	}
}

@Preview(showBackground = true, backgroundColor = 0xFFF1ECFF)
@Composable
private fun FortuneArcGaugePreview() {
	FortuneArcGauge(
		modifier = Modifier
			.size(width = 150.dp, height = 78.dp), // 제공 이미지 비율 근사
		strokeWidth = 22.dp,
		startAngleDeg = 205f,
		sweepAngleDeg = 130f
	)
}