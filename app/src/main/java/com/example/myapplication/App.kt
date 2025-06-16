package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.MainRepository
import com.example.myapplication.domain.Interactor
import com.example.myapplication.data.ApiConstants
import com.example.myapplication.data.TmdbApi
import com.example.myapplication.di.AppComponent
import com.example.myapplication.di.DaggerAppComponent
import com.example.myapplication.di.modules.DatabaseModule
import com.example.myapplication.di.modules.DomainModule
import com.example.myapplication.di.modules.RemoteModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App: Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}

    /*
    lateinit var repo: MainRepository
    lateinit var interactor: Interactor
    lateinit var retrofitService: TmdbApi

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Инициализируем репозиторий
        repo = MainRepository()


        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG){
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofitService = retrofit.create(TmdbApi::class.java)
        interactor = Interactor(repo,retrofitService)
    }

    companion object {
        //Здесь статически хранится ссылка на экземпляр App
        lateinit var instance: App
            //Приватный сеттер, чтобы нельзя было в эту переменную присвоить что-либо другое
            private set
    }
}*/