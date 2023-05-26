package com.omtorney.redwave.di

import androidx.room.Room
import com.omtorney.redwave.BuildConfig
import com.omtorney.redwave.data.RepositoryImpl
import com.omtorney.redwave.data.local.AppDatabase
import com.omtorney.redwave.data.local.PostDao
import com.omtorney.redwave.data.remote.FeedApi
import com.omtorney.redwave.data.remote.MyCustomAdapter
import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.domain.usecase.*
import com.omtorney.redwave.presentation.detail.EntryDetailViewModel
import com.omtorney.redwave.presentation.home.HomeViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    /** Retrofit */
    single { provideRedditApi() }

    /** Room */
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "Redwave_Database"
        ).build()
    }
    single<PostDao> {
        get<AppDatabase>().postDao()
    }

    /** Repository */
    single<Repository> { RepositoryImpl(get(), get()) } // singleOf(::RepositoryImpl) { bind<Repository>() }

    /** UseCases */
    single { CachePosts(repository = get()) }
    single { ClearCache(repository = get()) }
    single { GetComments(repository = get()) }
    single { GetPostDetails(repository = get()) }
    single { GetPosts(repository = get()) }
    single { LoadCachedPosts(repository = get()) }
    single { UpdatePost(repository = get()) }

    /** ViewModels */
    viewModel { HomeViewModel(get(), get(), get(), get(), get()) } // viewModelOf(::HomeViewModel)
    viewModel { EntryDetailViewModel(get(), get(), get()) }
}

fun provideRedditApi(): FeedApi {
    val moshi = Moshi.Builder()
        .add(object : Any() {}.javaClass, MyCustomAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(FeedApi::class.java)
}
