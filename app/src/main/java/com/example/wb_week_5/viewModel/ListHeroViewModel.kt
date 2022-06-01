package com.example.wb_week_5.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wb_week_5.model.Hero
import com.example.wb_week_5.repository.RemoteDataSource
import com.example.wb_week_5.repository.Repository
import com.example.wb_week_5.repository.RepositoryImpl
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException

private const val SEVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "ошибка запроса на сервер"
private const val CORRUPTED_DATA = "неполные данные"

class ListHeroViewModel(
    private val listHeroLiveData: MutableLiveData<AppStateList> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource())
) : ViewModel() {
    fun getLiveData() = listHeroLiveData

    fun getListHeroFromRemoteServer(request: String) {
        listHeroLiveData.value = AppStateList.Loading
        repository.getHeroFromServer(request, callback)
    }

    private val callback = object : Callback {
        override fun onFailure(call: Call, e: java.io.IOException) {
            listHeroLiveData.postValue(
                AppStateList.Error(Throwable(e?.message ?: REQUEST_ERROR))
            )
        }

        @Throws(IOException::class)
        override fun onResponse(call: Call, response: Response) {
            val serverResponse: String? = response.body?.string()
            listHeroLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppStateList.Error(Throwable(SEVER_ERROR))
                }
            )
        }

        private fun checkResponse(serverResponse: String): AppStateList {
            var listHeroes: List<Hero>?
            val moshi: Moshi = Moshi.Builder().build()
            val listType = Types.newParameterizedType(List::class.java, Hero::class.java)
            val jsonAdapter: JsonAdapter<List<Hero>> = moshi.adapter(listType)

            listHeroes = jsonAdapter.fromJson(serverResponse)

            return if (listHeroes == null) {
                AppStateList.Error(Throwable(CORRUPTED_DATA))
            } else {
                AppStateList.Success(listHeroes)
            }
        }
    }
}