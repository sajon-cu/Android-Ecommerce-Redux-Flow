package com.inweapp.mavericksfundamentals

import android.os.Bundle
import com.inweapp.mavericksfundamentals.core.BaseActivity
import com.inweapp.mavericksfundamentals.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}