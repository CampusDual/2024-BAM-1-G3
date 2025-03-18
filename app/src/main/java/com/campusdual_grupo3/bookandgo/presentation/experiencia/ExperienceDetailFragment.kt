package com.campusdual_grupo3.bookandgo.presentation.experiencia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.campusdual_grupo3.bookandgo.databinding.FragmentExperienceDetailBinding
import com.campusdual_grupo3.bookandgo.presentation.MainActivity


class ExperienceDetailFragment : Fragment() {
    private var binding: FragmentExperienceDetailBinding? = null
    private var experienceId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            experienceId = it.getInt("experienceId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExperienceDetailBinding.inflate(inflater, container, false)
        binding?.composeView?.setContent {
            ExperienceDetailScreen(experienceId = experienceId!!,
                onBackClick = {
                    (activity as? MainActivity)?.navigateBackStack()
            },
                goToGiftCard = {
                    (activity as? MainActivity)?.goToGift()
                }
            )

        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance(experienceId: Int): ExperienceDetailFragment {
            val fragment = ExperienceDetailFragment()
            val args = Bundle().apply {
                putInt("experienceId", experienceId)
            }

            fragment.arguments = args
            return fragment
        }
    }

}