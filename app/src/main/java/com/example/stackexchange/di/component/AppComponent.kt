package com.example.stackexchange.di.component

import com.example.stackexchange.di.module.AppModule
import com.example.stackexchange.di.module.NetworkModule
import com.example.stackexchange.di.module.RepositoryModule
import com.example.stackexchange.di.module.ViewModelModule
import com.example.stackexchange.ui.view.fragment.SearchFragment
import com.example.stackexchange.ui.view.fragment.UserDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelModule::class,
    RepositoryModule::class,
    NetworkModule::class])
interface AppComponent {
    fun inject(fragment: SearchFragment)
    fun inject(fragment: UserDetailsFragment)
}