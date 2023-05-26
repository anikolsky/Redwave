package com.omtorney.redwave.di

import com.omtorney.redwave.domain.repository.Repository
import com.omtorney.redwave.domain.usecase.ClearCache
import com.omtorney.redwave.domain.usecase.GetComments
import com.omtorney.redwave.domain.usecase.GetPostDetails
import com.omtorney.redwave.domain.usecase.GetPosts
import com.omtorney.redwave.domain.usecase.LoadCachedPosts
import com.omtorney.redwave.domain.usecase.UpdatePost
import com.omtorney.redwave.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases {
        return UseCases(
            clearCache = ClearCache(repository),
            getComments = GetComments(repository),
            getPostDetails = GetPostDetails(repository),
            getPosts = GetPosts(repository),
            loadCachedPosts = LoadCachedPosts(repository),
            updatePost = UpdatePost(repository)
        )
    }
}
