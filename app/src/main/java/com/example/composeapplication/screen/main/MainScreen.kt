package com.example.composeapplication.screen.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composeapplication.R
import com.example.composeapplication.Screen
import com.example.composeapplication.ui.custom.ItisTheme
import com.example.composeapplication.ui.theme.ComposeApplicationTheme
import com.example.composeapplication.utils.Click
import kotlinx.collections.immutable.persistentListOf

data class User(
    val name: String,
    val email: String
)

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(),
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    MainContent(
        viewState = state.value,
        eventHandler = viewModel::event
    )

    MainScreenActions(
        navController = navController,
        viewAction = action
    )
}

@Composable
fun MainContent(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
) {
    LazyColumnSample(viewState, eventHandler)
    ItisAlertDialog(
        viewState.showDialog,
        onConfirm = {
            eventHandler.invoke(MainEvent.OnConfirmDialog)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyColumnSample(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ItisTheme.colors.primaryBackground),
    ) {
        item {
            Text(
                text = "Hello ${viewState.title}!",
                color = ItisTheme.colors.primaryText,
                style = ItisTheme.typography.heading,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = viewState.email,
                onValueChange = {
                    eventHandler.invoke(MainEvent.OnEmailChange(it))
                },
                visualTransformation = if (viewState.isPassword) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                trailingIcon = {
                    ClearIcon(
                        isPassword = viewState.isPassword,
                        onClick = {
                            eventHandler.invoke(MainEvent.OnPassClick)
                        })
                },
                keyboardOptions = KeyboardOptions().copy(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = false,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        eventHandler.invoke(MainEvent.OnButtonClick)
                    },
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = ItisTheme.colors.secondaryBackground,
                    focusedIndicatorColor = ItisTheme.colors.tintColor,
                    textColor = ItisTheme.colors.primaryText
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(viewState.users, key = { it.email }) {
            MyListItem(user = it) {
                Log.e("User:", it.toString())
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                shape = ItisTheme.shapes.cornersStyle,
                colors = ButtonDefaults.buttonColors(
                    containerColor = ItisTheme.colors.tintColor,
                    contentColor = ItisTheme.colors.controlColor
                ),
                onClick = {
                    eventHandler.invoke(MainEvent.OnButtonClick)
                }) {
                Text(
                    text = stringResource(id = R.string.app_name)
                )
            }
        }
    }
}

@Composable
fun MyListItem(
    user: User,
    onClick: (User) -> Unit
) {
    Column(
        Modifier
            .height(56.dp)
            .fillMaxWidth()
            .clickable {
                onClick.invoke(user)
            }
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "My name ${user.name}",
            color = ItisTheme.colors.primaryText,
            style = ItisTheme.typography.body
        )
        Text(
            text = "My email ${user.email}",
            color = ItisTheme.colors.primaryText,
            style = ItisTheme.typography.caption
        )
    }
}

@Composable
private fun ClearIcon(
    isPassword: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            painterResource(id = R.drawable.baseline_access_alarm_24),
            contentDescription = null,
            tint = if (isPassword) Color.Yellow else Color.Red
        )
    }
}

@Composable
private fun ItisAlertDialog(
    show: Boolean,
    onConfirm: Click
) {
    if (show) {
        AlertDialog(
            title = {
                Text(
                    text = "Warning",
                    style = ItisTheme.typography.heading
                )
            },
            text = {
                Text(text = "Description")
            },
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(
                        text = "OK",
                        color = ItisTheme.colors.tintColor
                    )
                }
            },
            onDismissRequest = onConfirm,
        )
    }
}

@Composable
private fun MainScreenActions(
    navController: NavController,
    viewAction: MainAction?
) {
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            MainAction.Navigate -> {
                navController.navigate(Screen.Settings.route)
            }

            MainAction.ShowDialog -> {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    ComposeApplicationTheme {
        MainContent(
            viewState = MainViewState(
                title = "Test",
                email = "voda",
                isPassword = false,
                users = persistentListOf(User("Test", "dsadasdas"))
            ), {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview2() {
    ComposeApplicationTheme {
        MainContent(
            viewState = MainViewState(
                title = "Test2",
                email = "android",
                isPassword = true,
                users = persistentListOf(User("Test", "dsadasdas"))
            ), {}
        )
    }
}