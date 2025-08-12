package com.hanbang.fortune.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 *
 * @author   JGeun
 * @created  2025/08/10
 */
@Composable
fun FortuneWaveHeader(
	modifier: Modifier = Modifier,
	lavender: Color = Color(0xFFF1ECFF), // 연보라 핵심색
	background: Color = Color.White,     // 아래 배경(필요 없으면 그리기 생략 가능)
	amplitude: Dp = 28.dp,               // 파도 깊이(이미지 느낌: 22~32.dp)
) {
	val amp = with(LocalDensity.current) { amplitude.toPx() }

	Canvas(modifier = modifier) {
		// 바탕(아래 흰색)
		drawRect(background, size = size)

		// 상단 연보라 웨이브
		val path = Path().apply {
			// 우측부터 좌측으로 내려오는 한 번의 부드러운 굴곡
			moveTo(size.width, 0f)
			lineTo(size.width, amp * 0.18f)
			cubicTo(
				size.width * 0.82f, amp * 0.55f,   // 1차 컨트롤(완만한 시작)
				size.width * 0.35f, amp,           // 2차 컨트롤(가장 낮은 지점)
				0f, amp * 0.70f                    // 끝점(좌측으로 올라오며 마무리)
			)
			lineTo(0f, 0f)
			close()
		}
		drawPath(path, color = lavender)
	}
}

@Preview
@Composable
private fun FortuneWaveHeaderPreview() {
	FortuneWaveHeader(
		modifier = Modifier.fillMaxWidth().height(56.dp),
	)
}
