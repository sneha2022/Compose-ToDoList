package com.sneha.khanu.composetodolist.features.login.ui

import androidx.core.util.PatternsCompat
import androidx.lifecycle.viewModelScope
import com.wisnu.foundation.coreviewmodel.StatefulViewModel
import com.wisnu.khanu.composetodolist.features.login.data.ILoginEnvironment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    loginEnvironment: ILoginEnvironment
) : StatefulViewModel<LoginState, LoginEffect, LoginAction, ILoginEnvironment>(LoginState(), loginEnvironment) {

    override fun dispatch(action: LoginAction) {
        when (action) {
            is LoginAction.ChangePassword -> onPasswordChange(action.value)
            is LoginAction.ChangeEmail -> onUserNameChange(action.value)
            is LoginAction.ClickLogin, LoginAction.ClickImePasswordDone -> login()
        }
    }

    private fun login() {
        viewModelScope.launch {
            flow { emit(state.value.canLogin()) }
                .filter { it }
                .onEach { setState { copy(showEmailInvalidError = !isValidEmail()) } }
                .filter { state.value.isValidEmail() }
                .collect {
                    environment.login(state.value.email, state.value.password)
                        .collect { setEffect(LoginEffect.NavigateToDashboard) }
                }
        }
    }

    private fun onUserNameChange(email: String) {
        viewModelScope.launch {
            setState { copy(email = email, showEmailInvalidError = false) }
        }
    }

    private fun onPasswordChange(password: String) {
        viewModelScope.launch {
            setState { copy(password = password) }
        }
    }
}

fun LoginState.canLogin() = email.isNotBlank() && password.isNotBlank()

fun LoginState.isValidEmail() = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()




