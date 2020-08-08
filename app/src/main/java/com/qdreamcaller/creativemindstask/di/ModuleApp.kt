package com.qdreamcaller.creativemindstask.di

import com.qdreamcaller.creativemindstask.data.api.ApiHelper
import com.qdreamcaller.creativemindstask.data.api.RetrofitBuilder
import com.qdreamcaller.creativemindstask.data.repository.MainRepository
import com.qdreamcaller.creativemindstask.db.DataBase
import com.qdreamcaller.creativemindstask.ui.main.viewModel.MainViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { DataBase.getInstance(get()) }
    factory { RetrofitBuilder.apiService }
    factory { ApiHelper(get()) }
    single { MainRepository(get()) }

}
val viewModelModule = module {
    viewModel { MainViewModel(get()) }

}