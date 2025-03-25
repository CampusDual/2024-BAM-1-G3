package com.campusdual_grupo3.bookandgo.presentation


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.databinding.ActivityMainBinding
import com.campusdual_grupo3.bookandgo.presentation.experiencia.ExperienceDetailFragment
import com.campusdual_grupo3.bookandgo.presentation.favorites.FavoritesFragment
import com.campusdual_grupo3.bookandgo.presentation.home.HomeFragment
import com.campusdual_grupo3.bookandgo.presentation.listing.ListFragment
import com.campusdual_grupo3.bookandgo.presentation.navigation.NavigationHelper
import com.campusdual_grupo3.bookandgo.presentation.profile.ProfileFragment
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var navigationHelper = NavigationHelper()
    private var currentFragment: Fragment? = null


    private var homeFragment = HomeFragment()
    private var favoritesFragment = FavoritesFragment()
    private var listFragment = ListFragment()
    private var profileFragment = ProfileFragment()
    private var giftCardFragment = HomeFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openHome()

        initListener()

    }

    private fun initListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> openHome()
                R.id.navigation_favorites-> openFavorites()
                R.id.navigation_list-> openList()
                R.id.navigation_giftCard-> openGiftCard()
            }
            true
        }
    }

    fun openHome() {
        if (currentFragment !is HomeFragment) {
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                homeFragment, AppGlobalConstants.F_HOME)
        }
    }

    private fun openFavorites() {
        if (currentFragment !is FavoritesFragment) {
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                favoritesFragment, AppGlobalConstants.F_FAVORITES)
        }
    }


    private fun openList(){
        if (currentFragment !is ListFragment){
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                listFragment, AppGlobalConstants.F_LIST)
        }

    }

    fun goToGift() {
        binding.bottomNavigation.selectedItemId = R.id.navigation_giftCard
    }

    private fun openGiftCard() {
        if (currentFragment !is HomeFragment) {
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                homeFragment, AppGlobalConstants.F_HOME
            )
        }
    }

    fun openExperienceDetail(experienceId: Int) {
        val experienceDetailFragment = ExperienceDetailFragment.newInstance(experienceId)
        currentFragment = navigationHelper.showFragment(
            this, currentFragment,
            experienceDetailFragment, AppGlobalConstants.F_EXPERIENCE_DETAIL
        )
    }
    fun openProfile() {
        if (currentFragment !is ProfileFragment) {
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                profileFragment, AppGlobalConstants.F_PROFILE)
        }
    }

    fun navigateBackStack() {
        currentFragment = navigationHelper.backStackFragment(this, currentFragment)
    }
}