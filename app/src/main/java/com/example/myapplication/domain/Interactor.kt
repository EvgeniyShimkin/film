package com.example.myapplication.domain

import io.reactivex.rxjava3.core.Observable
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
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response

class Interactor(
    private val repo: MainRepository,
    private val retrofitService: TmdbApi,
    private val preferences: PreferenceProvider
) {

    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    /*
                "8e37e135348b88280fe470c96b7f0f9f",
                "ru-RU",
      */

    fun getFilmsFromApi(page: Int) {
        progressBarState.onNext(true)

        retrofitService.getFilms(
            getDefaultCategoryFromPreferences(),
            "8e37e135348b88280fe470c96b7f0f9f",
            "ru-RU",
            page
        ).enqueue(object : Callback<TmdbResults> {
            override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                val list = Converter.convertApiListToDtoList(response.body()?.tmdbFilms)
                //Кладем фильмы в бд
                Completable.fromSingle<List<Film>> { repo.putToDb(list) }
                    .subscribeOn(Schedulers.io())
                    .subscribe()
                progressBarState.onNext(false)
            }

            override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                //В случае провала выключаем ProgressBar
                scope.launch {
                    progressBarState.onNext(false)
                }
            }
        })
    }
    fun getSearchResultFromApi(search: String): Observable<List<Film>> = retrofitService.getFilmFromSearch("8e37e135348b88280fe470c96b7f0f9f", "ru-RU", search, 1)
        .map {
            Converter.convertApiListToDtoList(it.tmdbFilms)
        }


























    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDetaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmsFromDB(): Observable<List<Film>> = repo.getAllFromDB()

}
