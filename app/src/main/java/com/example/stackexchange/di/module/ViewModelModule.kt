package com.example.stackexchange.di.module

import com.example.stackexchange.data.repository.StackExchangeRepository
import com.example.stackexchange.ui.viewmodel.SearchViewModel
import com.example.stackexchange.ui.viewmodel.UserDetailViewModel
import com.example.stackexchange.utils.NetworkHelper
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun provideSearchViewModel(repository: StackExchangeRepository, networkHelper: NetworkHelper)
    = SearchViewModel(repository, networkHelper)

    @Provides
    fun provideUserDetailViewModel(repository: StackExchangeRepository,
                                   networkHelper: NetworkHelper) =
        UserDetailViewModel(repository, networkHelper)
}