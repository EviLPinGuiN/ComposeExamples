package com.example.composeapplication.ui.custom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class ItisColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color,
)

data class ItisTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val caption: TextStyle
)

data class ItisShape(
    val padding: Dp,
    val cornersStyle: Shape
)

data class ItisImages(
    val id: Int,
    val contentDesc: String
)

object ItisTheme {
    val colors: ItisColors
        @Composable
        get() = LocalItisColors.current

    val typography: ItisTypography
        @Composable
        get() = LocalItisTypography.current

    val shapes: ItisShape
        @Composable
        get() = LocalItisShape.current
}

enum class ItisStyle {
    Purple, Orange, Blue, Red, Green
}

enum class ItisSize {
    Small, Medium, Big
}

enum class ItisCorners {
    Flat, Rounded
}

val LocalItisColors = staticCompositionLocalOf<ItisColors> {
    error("No colors provided")
}

val LocalItisTypography = staticCompositionLocalOf<ItisTypography> {
    error("No font provided")
}

val LocalItisShape = staticCompositionLocalOf<ItisShape> {
    error("No shapes provided")
}
