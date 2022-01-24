package com.qdreamcaller.creativemindstask.ui.main.viewModel


import androidx.lifecycle.*
import com.qdreamcaller.creativemindstask.data.networkModel.response.RepoResponse

import com.qdreamcaller.creativemindstask.ui.main.repository.MainRepository
import com.qdreamcaller.creativemindstask.model.Repo
import com.qdreamcaller.creativemindstask.util.Resource
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private val repos = MutableLiveData<Resource<List<Repo>>>()
    fun getRepos(): LiveData<Resource<List<Repo>>> {
        return repos
    }

    fun getReposFromWebServices(page: Int) {
        viewModelScope.launch {
            repos.postValue(Resource.loading(data = null))
            if (page == 0) //clear database
                mainRepository.clearRepo()

            try {
                val response = mainRepository.getRepo(page, 10)
                if (response.isNotEmpty())//save in local dataBase
                    mainRepository.saveRepos(parseRepoResponse(response))

                repos.postValue(Resource.success(data = parseRepoResponse(response)))
            } catch (exception: Exception) {
                repos.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!$exception"
                    )
                )
            }
        }
    }

    fun filterDataFromLocal(keyWord: String) {
        viewModelScope.launch {
            try {
                val response = mainRepository.search(keyWord)
                repos.postValue(Resource.success(data = response))
            } catch (exception: Exception) {
                repos.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!$exception"
                    )
                )
            }
        }
    }

    private fun parseRepoResponse(repos: List<RepoResponse>): List<Repo> {

        val data = ArrayList<Repo>()
        for (repo in repos) {

            with(data) {
                add(
                    Repo(
                        repo.id, repo.name, repo.fullName, repo.description, repo.fork
                        , repo.url, repo.owner.login, repo.owner.url
                    )
                )
            }
        }
        return data
    }
}