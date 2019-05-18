package com.app.postssample.core.di.module

import com.app.postssample.data.repository.PostRepository
import com.app.postssample.data.repository.PostRepositoryImpl
import com.app.postssample.data.service.PostService
import dagger.Module
import dagger.Provides

@Module
class DataModule{
    @Provides
    fun provideRepository(service: PostService): PostRepository {
        return PostRepositoryImpl(service)
    }
}