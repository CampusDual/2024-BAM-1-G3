package com.campusdual_grupo3.bookandgo.presentation.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.campusdual_grupo3.bookandgo.databinding.FragmentFavoritesBinding
import com.campusdual_grupo3.bookandgo.presentation.MainActivity


class FavoritesFragment : Fragment() {
    private var binding: FragmentFavoritesBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding?.composeView?.setContent {
            FavoritesScreen(
                goToExperienceDetail = { experienceId ->
                    (activity as? MainActivity)?.openExperienceDetail(experienceId)
                },
                goToGiftCard = {
                    (activity as? MainActivity)?.goToGift()
                }
            )
        }
        return binding?.root
    }



}