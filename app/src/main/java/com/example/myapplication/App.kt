package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent
import com.example.myapplication.di.modules.DatabaseModule
import com.example.myapplication.di.modules.DomainModule
import com.example.remote_module.DaggerRemoteComponent
import com.example.remote_module.RemoteModule

class App: Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        val remoteComponent = DaggerRemoteComponent.create()
        dagger = DaggerAppComponent.builder()
            .remoteProvider(remoteComponent)
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }


    companion object {
        lateinit var instance: App
            private set
    }
}
