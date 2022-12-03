package com.inweapp.mavericksfundamentals.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.inweapp.mavericksfundamentals.core.StoreBaseFragment
import com.inweapp.mavericksfundamentals.databinding.FragmentProfileBinding

/**
 * Created by sajon on 12/3/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
class ProfileFragment: StoreBaseFragment<FragmentProfileBinding>() {
    override fun getBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(layoutInflater, viewGroup, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}