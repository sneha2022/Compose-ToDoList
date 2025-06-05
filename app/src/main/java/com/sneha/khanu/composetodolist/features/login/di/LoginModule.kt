package com.sneha.khanu.composetodolist.features.login.di

import com.wisnu.khanu.composetodolist.features.login.data.ILoginEnvironment
import com.wisnu.khanu.composetodolist.features.login.data.LoginEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginModule {

    @Binds
    abstract fun provideEnvironment(
        environment: LoginEnvironment
    ): ILoginEnvironment

}
