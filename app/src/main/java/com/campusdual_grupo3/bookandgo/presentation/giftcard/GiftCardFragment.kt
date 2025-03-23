package com.campusdual_grupo3.bookandgo.presentation.giftcard


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.campusdual_grupo3.bookandgo.data.datasource.remote.giftcard.dto.GiftCardScreenDto
import com.campusdual_grupo3.bookandgo.databinding.FragmentGiftcardBinding
import com.campusdual_grupo3.bookandgo.presentation.MainActivity


class GiftCardFragment : Fragment() {
    private var binding: FragmentGiftcardBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentGiftcardBinding.inflate(inflater, container, false)

        val giftCardData = GiftCardScreenDto(
            titleScreen = "Regala una experiencia",
            experienceImage = "https://naturayaventura.com/sites/default/files/styles/slideshow_slides/public/actividad/91/imatges/kayak-canoa-rio-ebro-miravet.jpg?itok=ZKANODDK", // URL o recurso de imagen
            experienceName = "KAYAK EN EL RÍO EBRO",
            onPayClick = { /* Lógica para proceder al pago */ }
        )

        binding?.composeView?.setContent {
            GiftCardScreen(giftCard = giftCardData)
        }
        return binding?.root
    }


}