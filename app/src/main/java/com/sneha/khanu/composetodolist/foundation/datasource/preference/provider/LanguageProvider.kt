package com.sneha.khanu.composetodolist.foundation.datasource.preference.provider

import androidx.datastore.core.DataStore
import com.wisnu.khanu.composetodolist.foundation.datasource.preference.mapper.toLanguage
import com.wisnu.khanu.composetodolist.foundation.datasource.preference.mapper.toLanguagePreference
import com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference
import com.wisnu.khanu.composetodolist.foundation.di.DiName
import com.wisnu.khanu.composetodolist.model.Language
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class LanguageProvider @Inject constructor(
    @Named(DiName.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val languageDataStore: DataStore<com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference>,
) {

    fun getLanguage(): Flow<Language> {
        return languageDataStore.data.map { it.language.toLanguage() }
            .catch { emit(Language.ENGLISH) }
            .flowOn(dispatcher)
    }

    suspend fun setLanguage(data: Language) {
        withContext(dispatcher) {
            languageDataStore.updateData {
                com.sneha.khanu.composetodolist.foundation.datasource.preference.model.UserLanguagePreference
                    .newBuilder()
                    .setLanguage(
                        data.toLanguagePreference()
                    ).build()
            }
        }
    }

}
