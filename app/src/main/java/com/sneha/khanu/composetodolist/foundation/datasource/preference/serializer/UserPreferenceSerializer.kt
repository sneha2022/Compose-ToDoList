package com.sneha.khanu.composetodolist.foundation.datasource.preference.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserPreference
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object UserPreferenceSerializer : Serializer<com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserPreference> {

    override val defaultValue: com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserPreference = com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserPreference.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserPreference {
        try {
            return com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserPreference.parseFrom(input)
        } catch (exception: IOException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override suspend fun writeTo(t: com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserPreference, output: OutputStream) = t.writeTo(output)

}
