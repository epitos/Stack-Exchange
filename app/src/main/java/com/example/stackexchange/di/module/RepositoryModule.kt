package com.example.stackexchange.di.module

import com.example.stackexchange.data.api.ApiService
import com.example.stackexchange.data.repository.StackExchangeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService) = StackExchangeRepository(apiService)
}