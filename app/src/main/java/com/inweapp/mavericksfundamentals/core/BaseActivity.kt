package com.inweapp.mavericksfundamentals.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MavericksView

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity(), MavericksView {
    protected lateinit var views: VB

    override fun invalidate() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = getBinding()
        setContentView(views.root)
    }

    /*==================
    * OPEN METHODS
    * ================== */
    abstract fun getBinding(): VB
}