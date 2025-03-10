package com.campusdual_grupo3.bookandgo.presentation.register.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : Fragment() {

    private lateinit var binding: FragmentPersonalInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        return binding.root
    }
}