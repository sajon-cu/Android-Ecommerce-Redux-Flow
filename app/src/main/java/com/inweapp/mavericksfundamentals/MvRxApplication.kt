package com.inweapp.mavericksfundamentals

import android.app.Application
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
@HiltAndroidApp
class MvRxApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initializing mavericks
        Mavericks.initialize(this)
    }
}