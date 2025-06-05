package com.sneha.khanu.composetodolist.foundation.extension

import com.wisnu.khanu.composetodolist.model.Credential
import org.junit.Assert
import org.junit.Test

class CredentialExtTest {

    @Test
    fun isLoggedIn() {
        val credential = Credential("token-123")

        Assert.assertTrue(credential.isLoggedIn())
    }

    @Test
    fun isNotLoggedIn() {
        val credential = Credential("")

        Assert.assertFalse(credential.isLoggedIn())
    }

}
