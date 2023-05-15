package com.example.composeapplication.screen.main

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Immutable
data class MainViewState(
    val title: String = "",
    val email: String = "",
    val isPassword: Boolean = false,
    val users: PersistentList<User> = persistentListOf(
        User(name = "test", email = "test@test.com"),
        User(name = "test1", email = "test1@test.com"),
        User(name = "test2", email = "test2@test.com"),
        User(name = "test3", email = "test3@test.com"),
        User(name = "test4", email = "test4@test.com"),
    ),
    val showDialog: Boolean = false,
    val navigateToSettings: Boolean? = null
)

sealed interface MainEvent {

    data class OnEmailChange(val email: String) : MainEvent
    data class OnUserClick(val user: User) : MainEvent
    object OnPassClick : MainEvent
    object OnButtonClick : MainEvent
    object OnConfirmDialog : MainEvent
}

sealed interface MainAction {

    object Navigate : MainAction

    object ShowDialog: MainAction
}

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<MainAction?>()
    val action: SharedFlow<MainAction?>
        get() = _action.asSharedFlow()

    fun event(mainEvent: MainEvent) {
        when (mainEvent) {
            MainEvent.OnButtonClick -> onButtonClick()
            MainEvent.OnPassClick -> onPassClick()
            is MainEvent.OnEmailChange -> onEmailChange(mainEvent)
            is MainEvent.OnUserClick -> TODO()
            MainEvent.OnConfirmDialog -> onConfirmDialog()
        }
    }

    private fun onButtonClick() {
        viewModelScope.launch {
//            _state.emit(
//                _state.value.copy(
//                    showDialog = true
//                )
//            )
            _action.emit(MainAction.Navigate)
        }
    }

    private fun onConfirmDialog() {
        viewModelScope.launch {
            _state.emit(
                _state.value.copy(
                    showDialog = false
                )
            )
        }
    }

    private fun onPassClick() {
        _state.tryEmit(
            _state.value.copy(
                isPassword = !_state.value.isPassword
            )
        )
    }

    private fun onEmailChange(event: MainEvent.OnEmailChange) {
        _state.tryEmit(
            _state.value.copy(
                email = event.email
            )
        )
    }
}