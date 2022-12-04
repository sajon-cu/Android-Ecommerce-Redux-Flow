package com.inweapp.mavericksfundamentals

import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.airbnb.epoxy.Carousel
import com.inweapp.mavericksfundamentals.core.StoreBaseActivity
import com.inweapp.mavericksfundamentals.databinding.ActivityMainBinding
import com.inweapp.mavericksfundamentals.redux.ApplicationState
import com.inweapp.mavericksfundamentals.redux.Store
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : StoreBaseActivity<ActivityMainBinding>() {
    override fun getBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    @Inject lateinit var appState: Store<ApplicationState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.productListFragment,
                R.id.cartFragment,
                R.id.profileFragment
            )
        )
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController, appBarConfiguration)

        NavigationUI.setupWithNavController(views.bottomNavView, navController)

        Carousel.setDefaultGlobalSnapHelperFactory(null)

        appState.stateFlow.map {
            it.inCartProductIds.size
        }.distinctUntilChanged().asLiveData().observe(this) { numberOfProductInCart ->
            views.bottomNavView.getOrCreateBadge(R.id.cartFragment).apply {
                number = numberOfProductInCart
                isVisible = numberOfProductInCart > 0
            }
        }

    }
}