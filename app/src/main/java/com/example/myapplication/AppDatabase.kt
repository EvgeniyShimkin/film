package com.example.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.DAO.FilmDao
import com.example.myapplication.data.Enity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}