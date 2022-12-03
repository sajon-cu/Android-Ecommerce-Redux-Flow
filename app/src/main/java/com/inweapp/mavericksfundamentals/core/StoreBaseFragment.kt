package com.inweapp.mavericksfundamentals.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.airbnb.mvrx.MavericksView

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
abstract class StoreBaseFragment<VB: ViewBinding>: Fragment() {
    private var _binding: VB? = null
    protected val views: VB
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(inflater, container)
        return views.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    /*==================
    * OPEN METHODS
    * ================== */
    abstract fun getBinding(layoutInflater: LayoutInflater, viewGroup: ViewGroup?): VB
}