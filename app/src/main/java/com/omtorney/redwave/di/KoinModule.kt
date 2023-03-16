package com.omtorney.redwave.di

import com.omtorney.redwave.BuildConfig
import com.omtorney.redwave.data.RepositoryImpl
import com.omtorney.redwave.data.remote.FeedApi
import com.omtorney.redwave.domain.GetFeed
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.presentation.detail.EntryViewModel
import com.omtorney.redwave.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

val appModule = module {
    single<Repository> { RepositoryImpl(get()) } // singleOf(::RepositoryImpl) { bind<Repository>() }
    viewModel { HomeViewModel(getFeed = get()) } // viewModelOf(::HomeViewModel)
    viewModel { EntryViewModel(savedStateHandle = get()) } // viewModelOf(::EntryViewModel)
    single { GetFeed(repository = get()) }
    single { provideRedditApi() }
}

fun provideRedditApi(): FeedApi {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
        .create(FeedApi::class.java)
}