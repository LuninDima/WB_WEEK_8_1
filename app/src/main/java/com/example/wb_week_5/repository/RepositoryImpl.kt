package com.example.wb_week_5.repository

import okhttp3.Callback

class RepositoryImpl(private val remoteDataSource: RemoteDataSource) : Repository {
    override fun getHeroFromServer(requestLink: String, callback: Callback) {
        remoteDataSource.getListHero(requestLink, callback)
    }
}
