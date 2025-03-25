package com.campusdual_grupo3.bookandgo.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import com.campusdual_grupo3.bookandgo.databinding.FragmentHomeBinding
import com.campusdual_grupo3.bookandgo.presentation.MainActivity


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.composeView?.setContent {
            HomeScreen(
                onClickExperience = { experienceId ->
                    (activity as? MainActivity)?.openExperienceDetail(experienceId)
                },
                onProfileClick = {
                    (activity as? MainActivity)?.openProfile()
                }
            )
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (System.currentTimeMillis() - backPressedTime < 2000) {
                requireActivity().finish() // Cierra la app si se presiona dos veces
            } else {
                Toast.makeText(requireContext(), "Presiona de nuevo atrás para salir", Toast.LENGTH_SHORT).show()
                backPressedTime = System.currentTimeMillis()
            }
        }

        return binding?.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
}