package com.example.alcomix.features.calcul.di

import com.example.alcomix.features.calcul.domain.usecases.CalculateAlcoholUseCase
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