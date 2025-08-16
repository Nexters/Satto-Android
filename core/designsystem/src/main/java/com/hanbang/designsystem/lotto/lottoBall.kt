package com.hanbang.designsystem.lotto

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R
import com.hanbang.designsystem.theme.SattoTheme
import com.hanbang.designsystem.theme.White

@Composable
fun LottoBall(
    number: Int?
) {
    Box {
        Image(
            painter = painterResource(
                when (number) {
                    null -> R.drawable.img_lotto_ball_default
                    in 40 until Int.MAX_VALUE -> R.drawable.img_lotto_ball_green
                    in 30 until 40 -> R.drawable.img_lotto_ball_gray
                    in 20 until 30 -> R.drawable.img_lotto_ball_red
                    in 10 until 20 -> R.drawable.img_lotto_ball_blue
                    in 1 until 10 -> R.drawable.img_lotto_ball_yellow
                    else -> R.drawable.img_lotto_ball_default
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(32.dp)
        )

        Text(
            text = number?.toString() ?: "?",
            style = SattoTheme.typography.body14Bold,
            color = White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun LottoBallGreenPreview() {
    LottoBall(40)
}

@Preview
@Composable
private fun LottoBallDefaultPreview() {
    LottoBall(null)
}