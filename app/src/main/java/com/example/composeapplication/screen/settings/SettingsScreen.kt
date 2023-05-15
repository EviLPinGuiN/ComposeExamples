package com.example.composeapplication.screen.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composeapplication.R
import com.example.composeapplication.ui.custom.ItisCorners
import com.example.composeapplication.ui.custom.ItisStyle
import com.example.composeapplication.ui.custom.ItisTheme
import com.example.composeapplication.ui.custom.blueDarkPalette
import com.example.composeapplication.ui.custom.blueLightPalette
import com.example.composeapplication.ui.custom.orangeDarkPalette
import com.example.composeapplication.ui.custom.orangeLightPalette
import com.example.composeapplication.ui.custom.purpleDarkPalette
import com.example.composeapplication.ui.custom.purpleLightPalette
import com.example.composeapplication.utils.Click

@Composable
fun SettingsScreen(
    navController: NavController
) {
    val settingsEventBus = LocalSettingsEventBus.current
    val currentSettings = settingsEventBus.currentSettings.collectAsState().value

    Surface(color = ItisTheme.colors.primaryBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                backgroundColor = ItisTheme.colors.primaryBackground,
                elevation = 8.dp
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = ItisTheme.shapes.padding),
                    text = stringResource(id = R.string.screen_settings),
                    color = ItisTheme.colors.primaryText,
                    style = ItisTheme.typography.toolbar
                )
            }

            Row(
                modifier = Modifier.padding(ItisTheme.shapes.padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Dark Theme",
                    color = ItisTheme.colors.primaryText,
                    style = ItisTheme.typography.body
                )
                Checkbox(
                    checked = currentSettings.isDarkMode, onCheckedChange = {
                        settingsEventBus.updateDarkMode(!currentSettings.isDarkMode)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = ItisTheme.colors.tintColor,
                        uncheckedColor = ItisTheme.colors.secondaryText
                    )
                )
            }

            Divider(
                modifier = Modifier.padding(start = ItisTheme.shapes.padding),
                thickness = 0.5.dp,
                color = ItisTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = ItisTheme.colors.secondaryBackground,
                shape = ItisTheme.shapes.cornersStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Shape type", color = ItisTheme.colors.secondaryText)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Card(
                            modifier = Modifier.weight(1f),
                            backgroundColor = ItisTheme.colors.primaryBackground
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp),
                                    text = "Round",
                                    color = ItisTheme.colors.primaryText,
                                    style = ItisTheme.typography.body
                                )
                                Checkbox(
                                    checked = currentSettings.cornerStyle == ItisCorners.Rounded, onCheckedChange = {
                                        settingsEventBus.updateCornerStyle(ItisCorners.Rounded)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = ItisTheme.colors.tintColor,
                                        uncheckedColor = ItisTheme.colors.secondaryText
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Card(
                            modifier = Modifier.weight(1f),
                            backgroundColor = ItisTheme.colors.primaryBackground
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(horizontal = 8.dp),
                                    text = "Flat",
                                    color = ItisTheme.colors.primaryText,
                                    style = ItisTheme.typography.body
                                )
                                Checkbox(
                                    checked = currentSettings.cornerStyle == ItisCorners.Flat, onCheckedChange = {
                                        settingsEventBus.updateCornerStyle(ItisCorners.Flat)
                                    },
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = ItisTheme.colors.tintColor,
                                        uncheckedColor = ItisTheme.colors.secondaryText
                                    )
                                )
                            }
                        }
                    }
                }
            }

            Divider(
                modifier = Modifier.padding(start = ItisTheme.shapes.padding),
                thickness = 0.5.dp,
                color = ItisTheme.colors.secondaryText.copy(
                    alpha = 0.3f
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 8.dp,
                backgroundColor = ItisTheme.colors.secondaryBackground,
                shape = ItisTheme.shapes.cornersStyle
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Tint color", color = ItisTheme.colors.secondaryText)

                    Row(
                        modifier = Modifier
                            .padding(ItisTheme.shapes.padding)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ColorCard(color = if (currentSettings.isDarkMode) purpleDarkPalette.tintColor else purpleLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(ItisStyle.Purple)
                            })
                        ColorCard(color = if (currentSettings.isDarkMode) orangeDarkPalette.tintColor else orangeLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(ItisStyle.Orange)
                            })
                        ColorCard(color = if (currentSettings.isDarkMode) blueDarkPalette.tintColor else blueLightPalette.tintColor,
                            onClick = {
                                settingsEventBus.updateStyle(ItisStyle.Blue)
                            })
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ColorCard(color: Color, onClick: Click) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .size(56.dp, 56.dp),
        backgroundColor = color,
        shape = ItisTheme.shapes.cornersStyle,
        elevation = 3.dp,
    ) {}
}
