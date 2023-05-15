package com.example.composeapplication.screen.settings

import com.example.composeapplication.ui.custom.ItisCorners
import com.example.composeapplication.ui.custom.ItisSize
import com.example.composeapplication.ui.custom.ItisStyle

data class CurrentSettings(
    val isDarkMode: Boolean,
    val textSize: ItisSize,
    val paddingSize: ItisSize,
    val cornerStyle: ItisCorners,
    val style: ItisStyle,
)
