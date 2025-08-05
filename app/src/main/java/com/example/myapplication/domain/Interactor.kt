package com.example.myapplication.domain
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import com.example.myapplication.data.Enity.Film
import com.example.myapplication.data.MainRepository
import com.example.myapplication.data.PreferenceProvider
import com.example.myapplication.utils.Converter
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


class Interactor(
    private val repo: MainRepository,
    private val retrofitService: com.example.remote_module.TmdbApi,
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
            page)
            .subscribeOn(Schedulers.io())
            .map {
                Converter.convertApiListToDtoList(it.tmdbFilms)
            }
            .subscribeBy(
                onError = {
                    progressBarState.onNext(false)
                },
                onNext = {
                    progressBarState.onNext(false)
                    repo.putToDb(it)
                }
            )
    }

fun getSearchResultFromApi(search: String): Observable<List<Film>> =
    retrofitService.getFilmFromSearch("8e37e135348b88280fe470c96b7f0f9f", "ru-RU", search, 1)
        .map {
            Converter.convertApiListToDtoList(it.tmdbFilms)
        }


fun saveDefaultCategoryToPreferences(category: String) {
    preferences.saveDetaultCategory(category)
}

fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

fun getFilmsFromDB(): Observable<List<Film>> = repo.getAllFromDB()

}
