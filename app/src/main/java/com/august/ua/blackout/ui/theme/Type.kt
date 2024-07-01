package com.august.ua.blackout.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.august.ua.blackout.R

val BlackoutFonts = FontFamily(
    Font(R.font.e_ukraine_bold, FontWeight.Bold),
    Font(R.font.e_ukraine_light, FontWeight.Light),
    Font(R.font.e_ukraine_medium, FontWeight.Medium),
    Font(R.font.e_ukraine_regular, FontWeight.Normal),
    Font(R.font.e_ukraine_thin, FontWeight.Thin),
    Font(R.font.e_ukraine_ultra_light, FontWeight.ExtraLight)
)

val BlackoutTextStyle = BlackoutTypography()

class BlackoutTypography(
    val heroText: TextStyle = BlackoutTypographyTokens.HeroText, // 29/32
    val hero2Text: TextStyle = BlackoutTypographyTokens.Hero2Text, // 26/32
    val h1Heading: TextStyle = BlackoutTypographyTokens.H1Heading, // 23/28
    val h2MediumHeading: TextStyle = BlackoutTypographyTokens.H2MediumHeading, // 21/24
    val h3SmallHeading: TextStyle = BlackoutTypographyTokens.H3smallHeading, // 18/24
    val h4ExtraSmallHeading: TextStyle = BlackoutTypographyTokens.H4ExtraSmallHeading, // 16/24,
    val h5SmallestHeading: TextStyle = BlackoutTypographyTokens.H5SmallestHeading, // 12/24,
    val t1BigText: TextStyle = BlackoutTypographyTokens.T1BigText, // 14/16
    val t2TextDescription: TextStyle = BlackoutTypographyTokens.T2TextDescription, // 12/16
    val t3TextBody: TextStyle = BlackoutTypographyTokens.T3TextBody, // 12/18
    val t4TextSmallDescription: TextStyle = BlackoutTypographyTokens.T4TextSmallDescription, // 11/16
    val t5TextSmallDescription: TextStyle = BlackoutTypographyTokens.T5TextSmallDescription, // 10/16
    val t5ExtraSmallDescription: TextStyle = BlackoutTypographyTokens.T5ExtraSmallDescription, // 9/11
    val numberText: TextStyle = BlackoutTypographyTokens.NumText, // 30/43
    val greetingText: TextStyle = BlackoutTypographyTokens.GreetingText // 38/44
)

internal object BlackoutTypographyTokens {
    val HeroText =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 29.sp,
            lineHeight = 32.sp
        )
    val Hero2Text =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            lineHeight = 32.sp
        )
    val H1Heading =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 23.sp,
            lineHeight = 28.sp
        )
    val H2MediumHeading =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 21.sp,
            lineHeight = 24.sp
        )
    val H3smallHeading =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
    val H4ExtraSmallHeading =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    val H5SmallestHeading =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 24.sp
        )
    val T1BigText =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 16.sp
        )
    val T2TextDescription =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp
        )
    val T3TextBody =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp
        )
    val T4TextSmallDescription =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 16.sp
        )
    val T5TextSmallDescription =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 16.sp
        )
    val T5ExtraSmallDescription =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 9.sp,
            lineHeight = 11.sp
        )
    val NumText =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 30.sp,
            lineHeight = 32.sp
        )
    val GreetingText =
        TextStyle(
            fontFamily = FontFamily(Font(R.font.e_ukraine_regular)),
            fontWeight = FontWeight.Normal,
            fontSize = 38.sp,
            lineHeight = 34.sp
        )
}