package com.example.myapplication.domain

import androidx.lifecycle.LiveData
import com.example.myapplication.data.Enity.Film
import retrofit2.Call
import retrofit2.Callback
import com.example.myapplication.data.MainRepository
import com.example.myapplication.data.TmdbApi
import com.example.myapplication.data.Enity.TmdbResults
import com.example.myapplication.data.PreferenceProvider
import com.example.myapplication.utils.Converter
import com.example.myapplication.viewmodel.HomeFragmentViewModel
import retrofit2.Response

class Interactor(
    private val repo: MainRepository,
    private val retrofitService: TmdbApi,
    private val preferences: PreferenceProvider
) {

    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {


        retrofitService.getFilms(
            getDefaultCategoryFromPreferences(),
            "8e37e135348b88280fe470c96b7f0f9f",
            "ru-RU",
            page
        ).enqueue(object : Callback<TmdbResults> {

            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                val list =Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                list.forEach {
                    repo.putToDb(list)

                }

                callback.onSuccess()
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure()
            }
        })
    }

    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDetaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): LiveData<List<Film>> = repo.getAllFromDB()

}
