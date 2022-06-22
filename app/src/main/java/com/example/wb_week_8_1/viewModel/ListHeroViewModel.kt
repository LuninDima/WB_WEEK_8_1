package com.example.wb_week_8_1.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wb_week_8_1.repository.LocalDataSource
import com.example.wb_week_8_1.repository.RemoteDataSource
import com.example.wb_week_8_1.repository.Repository
import com.example.wb_week_8_1.repository.RepositoryImpl
import com.example.wb_week_8_1.utils.checkIsExistFileLocalDataHeroes
import com.example.wb_week_8_1.utils.convertStringTOListHeroes
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import okio.IOException

private const val SEVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "ошибка запроса на сервер"
private const val CORRUPTED_DATA = "неполные данные"
private const val FILE_NAME_FOR_DATA = "dataHeroes1"

class ListHeroViewModel(
    private val listHeroLiveData: MutableLiveData<AppStateList> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource(), LocalDataSource()),
) : ViewModel() {

    fun getLiveData() = listHeroLiveData

    fun getDataHeroes(requestLink: String) {
        listHeroLiveData.value = AppStateList.Loading

        if (checkIsExistFileLocalDataHeroes(FILE_NAME_FOR_DATA)) {
            getDataHeroesFromLocalDataSource()
        } else {
            getListHeroFromRemoteServer(requestLink)
        }
    }

    private fun getDataHeroesFromLocalDataSource() {
        listHeroLiveData.postValue(
            setData(repository.getDataHeroesFromLocalData(FILE_NAME_FOR_DATA))
        )
    }

    fun getListHeroFromRemoteServer(requestLink: String) {
        repository.getListHeroesFromServer(requestLink, callback)
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
                    setData(serverResponse)
                } else {
                    AppStateList.Error(Throwable(SEVER_ERROR))
                }
            )
        }
    }

    private fun setData(serverResponse: String): AppStateList {
        val listHeroes = convertStringTOListHeroes(serverResponse)
        return if (listHeroes == null) {
            AppStateList.Error(Throwable(CORRUPTED_DATA))
        } else {
            if (!checkIsExistFileLocalDataHeroes(FILE_NAME_FOR_DATA)) {
                repository.saveDataHeroesToLocalData(FILE_NAME_FOR_DATA, serverResponse)
            }
            AppStateList.Success(listHeroes)
        }
    }
}