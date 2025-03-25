package com.campusdual_grupo3.bookandgo.presentation.register.fragments.uploadpic

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.campusdual_grupo3.bookandgo.databinding.FragmentUploadPicBinding
import com.campusdual_grupo3.bookandgo.presentation.register.RegisterActivity


class UploadPicFragment : Fragment() {

    private lateinit var binding: FragmentUploadPicBinding
    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            image = it.toString()
            binding.ivUpload.setImageURI(it) // Aqui gestionamos la imagen seleccionada
        }
    }

    private var name = ""
    private var email = ""
    private var password = ""
    private var phone = ""
    private var address = ""
    private var image = ""
    private var zipcode = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUploadPicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.intent?.extras?.apply {
            name = getString("NAME", "")
            email = getString("EMAIL", "")
            password = getString("PASSWORD", "")
            phone = getString("PHONE", "")
            address = getString("ADDRESS","")
            image = getString("IMAGE", "")
            zipcode = getString("ZIPCODE", "")

        }

        binding.button.setOnClickListener{
//            Dentro del clickListener:
            selectImageLauncher.launch("image/*")
        }
        binding.btnRegister.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("NAME", name)
            bundle.putString("EMAIL", email)
            bundle.putString("PASSWORD", password)
            bundle.putString("PHONE", phone)
            bundle.putString("ADDRESS", address)
            bundle.putString("IMAGE", image)
            bundle.putString("ZIPCODE", zipcode)


            activity?.intent?.putExtras(bundle)
            (activity as? RegisterActivity)?.openPreferences()
        }
    }
}



