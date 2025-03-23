package com.campusdual_grupo3.bookandgo.presentation.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants.F_FAVORITES
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants.F_GIFT_CARD
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants.F_HOME
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants.F_LIST
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants.F_PROFILE
import java.lang.ref.WeakReference
import java.util.Objects

class NavigationHelper {

    private val stack = mutableListOf<WeakReference<Fragment>>()

    /*
    fun showFragment(
        activity: AppCompatActivity,
        currentFragment: Fragment?,
        futureFragment: Fragment,
        tag: String
    ): Fragment {

        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (tag == F_HOME || tag == F_FAVORITES || tag == F_LIST || tag == F_GIFT_CARD|| tag == F_PROFILE ) {
            clearStack()
            if (fragmentManager.findFragmentByTag(tag) != null) {
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment)
                    fragmentManager.findFragmentByTag(tag)?.let { fragment ->
                        fragmentTransaction.show(
                            fragment
                        )
                    }
                }
            } else {
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment)
                }
                fragmentTransaction.add(R.id.fragment_container, futureFragment, tag)
            }
        } else {
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment)
                stack.add(WeakReference(currentFragment))
            }
            fragmentManager.findFragmentByTag(tag)?.let { fragment ->
                fragmentTransaction.show(
                    fragment
                )
            } ?: run {
                fragmentTransaction.add(R.id.fragment_container, futureFragment, tag)
            }
        }

        fragmentTransaction
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        return futureFragment
    }

    */
    fun showFragment(
        activity: AppCompatActivity,
        currentFragment: Fragment?,
        futureFragment: Fragment,
        tag: String
    ): Fragment {

        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (tag == F_HOME || tag == F_FAVORITES || tag == F_LIST || tag == F_GIFT_CARD || tag == F_PROFILE) {
            clearStack()
            if (fragmentManager.findFragmentByTag(tag) != null) {
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment)
                    fragmentManager.findFragmentByTag(tag)?.let { fragment ->
                        fragmentTransaction.show(fragment)
                    }
                }
            } else {
                if (currentFragment != null) {
                    fragmentTransaction.hide(currentFragment)
                }
                fragmentTransaction.add(R.id.fragment_container, futureFragment, tag)
            }
        } else {
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment)
                stack.add(WeakReference(currentFragment))
            }
            fragmentManager.findFragmentByTag(tag)?.let { fragment ->
                fragmentTransaction.show(fragment)
            } ?: run {
                fragmentTransaction.add(R.id.fragment_container, futureFragment, tag)
            }
        }

        fragmentTransaction
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        return futureFragment
    }


    fun backStackFragment(activity: AppCompatActivity, currentFragment: Fragment?): Fragment? {

        val fragmentManager = activity.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        var lastFragment: Fragment? = null

        if (stack.isEmpty()) {
            activity.finish()
        } else {
            lastFragment = fragmentManager.findFragmentByTag(stack[stack.size - 1].get()?.tag)
            if (lastFragment != null) {
                fragmentTransaction.remove(currentFragment!!)
                fragmentTransaction.show(Objects.requireNonNull(lastFragment))
                fragmentTransaction
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .commit()
                stack.removeAt(stack.size - 1)
            }
        }
        return lastFragment
    }

    private fun clearStack() {
        val iterator = stack.iterator()

        while (iterator.hasNext()) {
            val fragment = iterator.next().get()
            if (fragment != null)
                if (fragment.tag != null)
                    iterator.remove()
        }
    }
}