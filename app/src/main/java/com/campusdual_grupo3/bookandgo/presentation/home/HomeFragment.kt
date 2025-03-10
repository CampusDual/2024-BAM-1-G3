package com.campusdual_grupo3.bookandgo.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.campusdual_grupo3.bookandgo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.composeView?.setContent {
            HomeScreen()

        }
        return binding?.root
    }


}