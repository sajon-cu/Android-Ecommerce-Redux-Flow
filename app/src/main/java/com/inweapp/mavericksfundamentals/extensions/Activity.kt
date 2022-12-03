package com.inweapp.mavericksfundamentals.extensions

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by sajon on 11/17/22
 * Copyright (c) 2022 syftet ltd. All rights reserved.
 * sajon@syftet.com
 * Last modified $file.lastModified
 */
private fun <T: Fragment> AppCompatActivity.replaceFragment(container: ViewGroup, fragment: Class<T>) {
    supportFragmentManager.beginTransaction()
        .replace(container.id, fragment, null)
        .addToBackStack(null)
        .commit()
}

private fun <T: Fragment> AppCompatActivity.addFragment(container: ViewGroup, fragment: Class<T>) {
    supportFragmentManager.beginTransaction()
        .add(container.id, fragment, null, null)
        .commit()
}