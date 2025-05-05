package com.example.myapplication.domain

import com.example.myapplication.data.MainRepository

class Interactor (val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}