package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.App
import com.example.myapplication.domain.Film
import com.example.myapplication.domain.Interactor

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    private var interactor: Interactor = App.instance.interactor
    init {

        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }
}