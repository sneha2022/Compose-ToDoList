package com.sneha.khanu.composetodolist.features.splash.di

import com.sneha.khanu.composetodolist.features.splash.data.ISplashEnvironment
import com.sneha.khanu.composetodolist.features.splash.data.SplashEnvironment

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SplashModule {

    @Binds
    abstract fun provideEnvironment(
        environment: SplashEnvironment
    ): ISplashEnvironment

}
