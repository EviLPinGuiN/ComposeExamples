package com.example.composeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.example.composeapplication.screen.settings.LocalSettingsEventBus
import com.example.composeapplication.screen.settings.SettingsEventBus
import com.example.composeapplication.ui.custom.ItisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val settingsEventBus = remember { SettingsEventBus() }

            derivedStateOf {

            }
            val currentSettings = settingsEventBus.currentSettings.collectAsState().value

            ItisTheme(
                style = currentSettings.style,
                darkTheme = currentSettings.isDarkMode,
                corners = currentSettings.cornerStyle,
                textSize = currentSettings.textSize,
                paddingSize = currentSettings.paddingSize
            ) {
                CompositionLocalProvider(
                    LocalSettingsEventBus provides settingsEventBus
                ) {
                    ItisNavHost()
                }
            }
        }
    }
}