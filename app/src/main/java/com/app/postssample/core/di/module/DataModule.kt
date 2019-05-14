package com.app.postssample.core.di.module

import com.app.postssample.data.repository.PostRepository
import com.app.postssample.data.repository.PostRepositoryImpl
import com.app.postssample.data.service.PostApi
import dagger.Module
import dagger.Provides

@Module
class DataModule{
    @Provides
    fun provideRepository(api: PostApi): PostRepository {
        return PostRepositoryImpl(api)
    }
}