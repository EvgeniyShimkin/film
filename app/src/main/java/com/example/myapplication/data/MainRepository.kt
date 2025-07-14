package com.example.myapplication.data

import android.content.ContentValues
import android.database.Cursor
import androidx.lifecycle.LiveData
import com.example.myapplication.data.DAO.FilmDao
import com.example.myapplication.data.Enity.Film
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors

class MainRepository (private val filmDao: FilmDao) {

    fun putToDb(films: List <Film>) {
        Executors.newSingleThreadExecutor().execute{
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): Flow<List<Film>> = filmDao.getCachedFilms()


}









