package com.example.wb_week_7.repository

import com.example.wb_week_7.repository.RemoteDataSource
import com.example.wb_week_7.repository.Repository
import okhttp3.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource) : Repository {
    override fun getListHeroesFromServer(requestLink: String, callback: Callback) {
        remoteDataSource.getListHeroes(requestLink, callback)
    }

    override fun getDataHeroesFromLocalData(fileNameForData: String):String {
      return  localDataSource.getDataHeroes(fileNameForData)
    }

    override fun saveDataHeroesToLocalData(fileNameForData: String, dataHeroes: String) {
        localDataSource.saveDataHeroes(fileNameForData, dataHeroes)
    }
}
