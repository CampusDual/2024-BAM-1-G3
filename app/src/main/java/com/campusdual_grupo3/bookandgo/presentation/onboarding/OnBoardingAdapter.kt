package com.campusdual_grupo3.bookandgo.presentation.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.campusdual_grupo3.bookandgo.R

class OnBoardingAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3 // Número de fragments

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnBoardingFragment.newInstance(R.drawable.ic_onboarding_1)
            1 -> OnBoardingFragment.newInstance(R.drawable.ic_onboarding_2)
            2 -> OnBoardingFragment.newInstance(R.drawable.ic_onboarding_3)
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}