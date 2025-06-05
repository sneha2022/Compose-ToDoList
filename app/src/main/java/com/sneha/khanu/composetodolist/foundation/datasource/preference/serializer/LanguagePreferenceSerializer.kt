package com.sneha.khanu.composetodolist.foundation.datasource.preference.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object LanguagePreferenceSerializer : Serializer<com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference> {

    override val defaultValue: com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference = com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference {
        try {
            return com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference.parseFrom(input)
        } catch (exception: IOException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override suspend fun writeTo(t: com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference, output: OutputStream) = t.writeTo(output)

}
