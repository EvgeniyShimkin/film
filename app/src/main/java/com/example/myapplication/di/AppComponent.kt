package com.example.myapplication.di

import com.example.myapplication.di.modules.DatabaseModule
import com.example.myapplication.di.modules.DomainModule
import com.example.myapplication.di.modules.RemoteModule
import com.example.myapplication.viewmodel.HomeFragmentViewModel
import com.example.myapplication.viewmodel.SettingsFragmentViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RemoteModule::class, DatabaseModule::class, DomainModule::class])

interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}