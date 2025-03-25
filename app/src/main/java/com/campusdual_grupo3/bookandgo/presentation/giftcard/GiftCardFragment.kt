package com.campusdual_grupo3.bookandgo.presentation.giftcard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.campusdual_grupo3.bookandgo.databinding.FragmentGiftcardBinding
import com.campusdual_grupo3.bookandgo.presentation.MainActivity


class GiftCardFragment : Fragment() {
    private var binding: FragmentGiftcardBinding? = null
    private var experienceId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            experienceId = it.getInt("experienceId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGiftcardBinding.inflate(inflater, container, false)


        binding?.composeView?.setContent {
            GiftCardScreen(
                onClickGiftCard = { experienceId ->
                    (activity as? MainActivity)?.openGiftCardDetail(experienceId)
                },
                experienceId = experienceId,
                onProfileClick = {
                    (activity as? MainActivity)?.openProfile()
                },
                onLogoClick = {
                    (activity as? MainActivity)?.openHome()
                }
            )
        }
        return binding?.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }

}