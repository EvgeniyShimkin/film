package com.example.myapplication.di

import com.example.myapplication.di.modules.DatabaseModule
import com.example.myapplication.di.modules.DomainModule
import com.example.remote_module.RemoteModule
import com.example.myapplication.viewmodel.HomeFragmentViewModel
import com.example.myapplication.viewmodel.SettingsFragmentViewModel
import com.example.remote_module.RemoteProvider
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(dependencies = [RemoteProvider::class],  modules = [DatabaseModule::class, DomainModule::class])

interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}