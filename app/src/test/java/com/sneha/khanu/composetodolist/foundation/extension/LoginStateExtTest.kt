package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.features.login.ui.LoginState
import com.wisnu.khanu.composetodolist.features.login.ui.canLogin
import com.wisnu.khanu.composetodolist.features.login.ui.isValidEmail
import org.junit.Assert
import org.junit.Test

class LoginStateExtTest {

    @Test
    fun canLogin() {
        val state = LoginState(
            email = "qwe",
            password = "123"
        )

        Assert.assertTrue(state.canLogin())
    }

    @Test
    fun canNotLogin() {
        val state = LoginState(
            email = " ",
            password = " "
        )

        Assert.assertFalse(state.canLogin())
    }

    @Test
    fun validEmail() {
        val state = LoginState(
            email = "wisnu@gmail.com",
            password = " "
        )

        Assert.assertTrue(state.isValidEmail())
    }

    @Test
    fun inValidEmail() {
        val state = LoginState(
            email = "wisnugmail.com",
            password = " "
        )

        Assert.assertFalse(state.isValidEmail())
    }

}
