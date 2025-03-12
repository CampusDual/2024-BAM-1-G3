package com.campusdual_grupo3.bookandgo.presentation.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.campusdual_grupo3.bookandgo.databinding.FragmentListBinding
import com.campusdual_grupo3.bookandgo.presentation.MainActivity


class ListFragment : Fragment() {
    private var binding: FragmentListBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding?.composeView?.setContent {
            ListScreen(onExperienceClick = { experienceId ->
                (activity as? MainActivity)?.openExperienceDetail(experienceId)
            })

        }
        return binding?.root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }


}