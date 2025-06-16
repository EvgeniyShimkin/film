package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.data.Enity.Film
import com.example.myapplication.domain.Interactor
import java.util.concurrent.Executors
import javax.inject.Inject
import kotlin.math.truncate

class HomeFragmentViewModel : ViewModel() {
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()

@Inject
    lateinit var interactor: Interactor

    val filmsListLiveData: LiveData<List<Film>>


    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmsFromDB()
        getFilms()
    }
    fun getFilms(){
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
           //     filmsListLiveData.postValue(films)
            }

            override fun onFailure() {
                showProgressBar.postValue(false)
                }

        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}