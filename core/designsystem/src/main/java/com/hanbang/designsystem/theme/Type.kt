package com.hanbang.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.hanbang.designsystem.R

private val SattoFontFamily = FontFamily(
    Font(R.font.suit_bold, weight = FontWeight.Bold),
    Font(R.font.suit_semibold, weight = FontWeight.SemiBold),
    Font(R.font.suit_medium, weight = FontWeight.Medium),
    Font(R.font.suit_regular, weight = FontWeight.Normal),
)

internal val Typography: (density: Density) -> SattoTypography = { density ->
    SattoTypography(
        display28Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                28.dp.toSp()
            },
        ),
        display26Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                26.dp.toSp()
            },
        ),
        headline24Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                24.dp.toSp()
            },
        ),
        headline24Semibold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = with(density) {
                24.dp.toSp()
            },
        ),
        headline22Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                22.dp.toSp()
            },
        ),
        headline22SemiBold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = with(density) {
                22.dp.toSp()
            },
        ),
        headline20Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                20.dp.toSp()
            },
        ),
        headline20Semibold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = with(density) {
                20.dp.toSp()
            },
        ),
        headline20Medium = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = with(density) {
                20.dp.toSp()
            },
        ),
        headline20Regular = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = with(density) {
                20.dp.toSp()
            },
        ),
        body18Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                18.dp.toSp()
            },
        ),
        body18Semibold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = with(density) {
                18.dp.toSp()
            },
        ),
        body18Medium = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = with(density) {
                18.dp.toSp()
            },
        ),
        body18Regular = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = with(density) {
                18.dp.toSp()
            },
        ),
        body16Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                16.dp.toSp()
            },
        ),
        body16Semibold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = with(density) {
                16.dp.toSp()
            },
        ),
        body16Medium = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = with(density) {
                16.dp.toSp()
            },
        ),
        body16Regular = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = with(density) {
                16.dp.toSp()
            },
        ),
        body14Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                14.dp.toSp()
            },
        ),
        body14Semibold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = with(density) {
                14.dp.toSp()
            },
        ),
        body14Medium = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = with(density) {
                14.dp.toSp()
            },
        ),
        body14Regular = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = with(density) {
                14.dp.toSp()
            },
        ),
        caption12Bold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = with(density) {
                12.dp.toSp()
            },
        ),
        caption12Semibold = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = with(density) {
                12.dp.toSp()
            },
        ),
        caption12Medium = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = with(density) {
                12.dp.toSp()
            },
        ),
        caption12Regular = TextStyle(
            fontFamily = SattoFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = with(density) {
                12.dp.toSp()
            },
        ),
    )
}

val LocalSattoTypography = staticCompositionLocalOf { SattoTypography() }

@Immutable
data class SattoTypography(
    val display28Bold: TextStyle = TextStyle(),
    val display26Bold: TextStyle = TextStyle(),
    val headline24Bold: TextStyle = TextStyle(),
    val headline24Semibold: TextStyle = TextStyle(),
    val headline22Bold: TextStyle = TextStyle(),
    val headline22SemiBold: TextStyle = TextStyle(),
    val headline20Bold: TextStyle = TextStyle(),
    val headline20Semibold: TextStyle = TextStyle(),
    val headline20Medium: TextStyle = TextStyle(),
    val headline20Regular: TextStyle = TextStyle(),
    val body18Bold: TextStyle = TextStyle(),
    val body18Semibold: TextStyle = TextStyle(),
    val body18Medium: TextStyle = TextStyle(),
    val body18Regular: TextStyle = TextStyle(),
    val body16Bold: TextStyle = TextStyle(),
    val body16Semibold: TextStyle = TextStyle(),
    val body16Medium: TextStyle = TextStyle(),
    val body16Regular: TextStyle = TextStyle(),
    val body14Bold: TextStyle = TextStyle(),
    val body14Semibold: TextStyle = TextStyle(),
    val body14Medium: TextStyle = TextStyle(),
    val body14Regular: TextStyle = TextStyle(),
    val caption12Bold: TextStyle = TextStyle(),
    val caption12Semibold: TextStyle = TextStyle(),
    val caption12Medium: TextStyle = TextStyle(),
    val caption12Regular: TextStyle = TextStyle(),
)