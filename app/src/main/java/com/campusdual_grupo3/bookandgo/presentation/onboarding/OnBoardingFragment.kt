package com.campusdual_grupo3.bookandgo.presentation.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.campusdual_grupo3.bookandgo.databinding.FragmentOnboardingBinding

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(imageResource: Int): OnBoardingFragment {
            val fragment = OnBoardingFragment()
            val args = Bundle().apply {
                putInt("image_resource", imageResource)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            binding.ivImage.setImageResource(it.getInt("image_resource"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}