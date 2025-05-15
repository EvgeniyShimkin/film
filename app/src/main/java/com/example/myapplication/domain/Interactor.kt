package com.example.myapplication.domain

import retrofit2.Call
import retrofit2.Callback
import com.example.myapplication.data.MainRepository
import com.example.myapplication.data.TmdbApi
import com.example.myapplication.data.Enity.TmdbResults
import com.example.myapplication.utils.Converter
import com.example.myapplication.viewmodel.HomeFragmentViewModel
import retrofit2.Response

class Interactor (private val repo: MainRepository, private val retrofitService: TmdbApi) {

    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms("8e37e135348b88280fe470c96b7f0f9f", "ru-RU", page).enqueue(object : Callback<TmdbResults>{

            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                //При успехе мы вызываем метод передаем onSuccess и в этот коллбэк список фильмов
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }
}
