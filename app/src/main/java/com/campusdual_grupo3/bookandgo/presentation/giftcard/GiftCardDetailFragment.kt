package com.campusdual_grupo3.bookandgo.presentation.giftcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.campusdual_grupo3.bookandgo.databinding.FragmentGiftcardDetailBinding
import com.campusdual_grupo3.bookandgo.presentation.MainActivity


class GiftCardDetailFragment : Fragment() {
    private var binding: FragmentGiftcardDetailBinding? = null
    private var giftcardId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            giftcardId = it.getInt("giftcardId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGiftcardDetailBinding.inflate(inflater, container, false)
        binding?.composeView?.setContent {
            GiftCardDetailScreen (
                experienceId = giftcardId!!,
                onBackClick = {
                    // Fix: Use navigateBackStack instead of opening a new screen
                    (activity as? MainActivity)?.navigateBackStack()
                },
                goToGiftCard = {
                    // Don't navigate to the same screen again - this was creating a loop
                    // Instead, navigate to a different screen or perform an action
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
        fun newInstance(giftcardId: Int): GiftCardDetailFragment {
            val fragment = GiftCardDetailFragment()
            val args = Bundle().apply {
                putInt("giftcardId", giftcardId)
            }

            fragment.arguments = args
            return fragment
        }
    }

}