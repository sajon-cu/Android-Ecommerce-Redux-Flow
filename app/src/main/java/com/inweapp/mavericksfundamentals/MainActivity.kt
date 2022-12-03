package com.inweapp.mavericksfundamentals

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.inweapp.mavericksfundamentals.core.StoreBaseActivity
import com.inweapp.mavericksfundamentals.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : StoreBaseActivity<ActivityMainBinding>() {
    override fun getBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.productListFragment,
                R.id.profileFragment
            )
        )
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController, appBarConfiguration)

        NavigationUI.setupWithNavController(views.bottomNavView, navController)
    }
}