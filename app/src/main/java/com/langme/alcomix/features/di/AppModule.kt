package com.langme.alcomix.features.di

import com.langme.alcomix.features.domain.usecases.CalculateAlcoholUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCalculateAlcoholUseCase(): CalculateAlcoholUseCase {
        return CalculateAlcoholUseCase()
    }
}