package com.qdreamcaller.creativemindstask.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.qdreamcaller.creativemindstask.data.api.ApiHelper
import com.qdreamcaller.creativemindstask.db.DataBase
import com.qdreamcaller.creativemindstask.model.Repo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.experimental.property.inject


class MainRepository(private val apiHelper: ApiHelper) : KoinComponent {

    private val dataBase by inject<DataBase>()

    suspend fun getRepo(page: Int, per_page: Int) = apiHelper.getRepos(page, per_page)

    suspend fun saveRepos(repos: List<Repo>) {
        try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                for (repo in repos) {
                    dataBase.repoDao().insert(repo)
                }
            }
        } catch (e: Exception) {
            Log.e("dataBase", e.toString())
        }
    }

    suspend fun clearRepo() {
        try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                dataBase.repoDao().clear()
            }
        } catch (e: Exception) {
            Log.e("dataBase", e.toString())

        }


    }

    suspend fun search(keyWord:String): List<Repo> {
       return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                dataBase.repoDao().filter(keyWord)
            }
        } catch (e: Exception) {
            Log.e("dataBase", e.toString())
           return emptyList<Repo>()

        }


    }
}