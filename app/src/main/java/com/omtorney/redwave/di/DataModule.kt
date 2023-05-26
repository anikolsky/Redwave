package com.omtorney.redwave.di

import android.content.Context
import androidx.room.Room
import com.omtorney.redwave.BuildConfig
import com.omtorney.redwave.data.local.AppDatabase
import com.omtorney.redwave.data.local.PostDao
import com.omtorney.redwave.data.remote.FeedApi
import com.omtorney.redwave.data.remote.MyCustomAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Redwave_Database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePostDao(appDatabase: AppDatabase): PostDao {
        return appDatabase.postDao()
    }

    @Provides
    @Singleton
    fun provideFeedApi(): FeedApi {
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
}
