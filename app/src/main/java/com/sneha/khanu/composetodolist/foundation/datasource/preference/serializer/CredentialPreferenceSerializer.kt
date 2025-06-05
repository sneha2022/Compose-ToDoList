package com.sneha.khanu.composetodolist.foundation.datasource.preference.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.sneha.khanu.composetodolist.foundation.datasource.preference.model.CredentialPreference
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object CredentialPreferenceSerializer : Serializer<com.sneha.khanu.composetodolist.foundation.datasource.preference.model.CredentialPreference> {

    override val defaultValue: com.sneha.khanu.composetodolist.foundation.datasource.preference.model.CredentialPreference = com.sneha.khanu.composetodolist.foundation.datasource.preference.model.CredentialPreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): com.sneha.khanu.composetodolist.foundation.datasource.preference.model.CredentialPreference {
        try {
            return com.sneha.khanu.composetodolist.foundation.datasource.preference.model.CredentialPreference.parseFrom(input)
        } catch (exception: IOException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override suspend fun writeTo(t: com.sneha.khanu.composetodolist.foundation.datasource.preference.model.CredentialPreference, output: OutputStream) = t.writeTo(output)

}
