package com.example.myapplication.utils

import com.example.myapplication.data.Enity.Film
import com.example.remote_module.entity.TmdbFilm

object Converter {
    fun convertApiListToDtoList(list: List<com.example.remote_module.entity.TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(
                Film(
                title = it.title,
                poster = it.posterPath,
                description = it.overview,
                rating = it.voteAverage,
                isInFavorites = false
            )
            )
        }
        return result
    }
}