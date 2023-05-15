package com.example.composeapplication.screen.settings

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.composeapplication.ui.custom.ItisCorners
import com.example.composeapplication.ui.custom.ItisSize
import com.example.composeapplication.ui.custom.ItisStyle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsEventBus {

    private val _currentSettings: MutableStateFlow<CurrentSettings> = MutableStateFlow(
        CurrentSettings(
            isDarkMode = true,
            cornerStyle = ItisCorners.Rounded,
            style = ItisStyle.Orange,
            textSize = ItisSize.Medium,
            paddingSize = ItisSize.Medium
        )
    )
    val currentSettings: StateFlow<CurrentSettings> = _currentSettings

    fun updateDarkMode(isDarkMode: Boolean) {
        _currentSettings.value = _currentSettings.value.copy(isDarkMode = isDarkMode)
    }

    fun updateCornerStyle(corners: ItisCorners) {
        _currentSettings.value = _currentSettings.value.copy(cornerStyle = corners)
    }

    fun updateStyle(style: ItisStyle) {
        _currentSettings.value = _currentSettings.value.copy(style = style)
    }
}

val LocalSettingsEventBus = staticCompositionLocalOf {
    SettingsEventBus()
}