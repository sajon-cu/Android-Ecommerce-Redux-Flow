package com.inweapp.mavericksfundamentals.hilt

import com.inweapp.mavericksfundamentals.redux.ApplicationState
import com.inweapp.mavericksfundamentals.redux.Store
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
@Module
@InstallIn(SingletonComponent::class)
object ApplicationStoreModule {
    @Provides
    @Singleton
    fun provideApplicationStateStore(): Store<ApplicationState> {
        return Store(ApplicationState())
    }
}