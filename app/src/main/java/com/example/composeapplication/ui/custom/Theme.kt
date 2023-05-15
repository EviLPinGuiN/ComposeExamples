package com.example.composeapplication.ui.custom

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat

@Composable
fun ItisTheme(
    style: ItisStyle = ItisStyle.Purple,
    textSize: ItisSize = ItisSize.Medium,
    paddingSize: ItisSize = ItisSize.Medium,
    corners: ItisCorners = ItisCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when {
        darkTheme -> {
            when (style) {
                ItisStyle.Purple -> purpleDarkPalette
                ItisStyle.Blue -> blueDarkPalette
                ItisStyle.Orange -> orangeDarkPalette
                ItisStyle.Red -> redDarkPalette
                ItisStyle.Green -> greenDarkPalette
            }
        }

        else -> {
            when (style) {
                ItisStyle.Purple -> purpleLightPalette
                ItisStyle.Blue -> blueLightPalette
                ItisStyle.Orange -> orangeLightPalette
                ItisStyle.Red -> redLightPalette
                ItisStyle.Green -> greenLightPalette
            }
        }
    }

    val typography = ItisTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                ItisSize.Small -> 24.sp
                ItisSize.Medium -> 28.sp
                ItisSize.Big -> 32.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                ItisSize.Small -> 14.sp
                ItisSize.Medium -> 16.sp
                ItisSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontFamily = FontFamily.Cursive,
            fontSize = when (textSize) {
                ItisSize.Small -> 14.sp
                ItisSize.Medium -> 16.sp
                ItisSize.Big -> 18.sp
            },
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontSize = when (textSize) {
                ItisSize.Small -> 10.sp
                ItisSize.Medium -> 12.sp
                ItisSize.Big -> 14.sp
            }
        )
    )

    val shapes = ItisShape(
        padding = when (paddingSize) {
            ItisSize.Small -> 12.dp
            ItisSize.Medium -> 16.dp
            ItisSize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            ItisCorners.Flat -> RoundedCornerShape(0.dp)
            ItisCorners.Rounded -> RoundedCornerShape(8.dp)
        }
    )

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.primaryBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        LocalItisColors provides colors,
        LocalItisTypography provides typography,
        LocalItisShape provides shapes,
        content = content
    )
}