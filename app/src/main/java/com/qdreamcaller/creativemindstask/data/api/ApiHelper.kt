package com.qdreamcaller.creativemindstask.data.api


class ApiHelper (private val apiService: ApiService) {
    suspend fun getRepos( page:Int,per_page:Int) = apiService.getRepo(page,per_page)

}