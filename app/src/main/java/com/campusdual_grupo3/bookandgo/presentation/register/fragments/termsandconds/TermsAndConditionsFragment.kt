package com.campusdual_grupo3.bookandgo.presentation.register.fragments.termsandconds

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.campusdual_grupo3.bookandgo.databinding.FragmentTermsAndConditionsBinding
import com.campusdual_grupo3.bookandgo.domain.entities.UserEntity
import com.campusdual_grupo3.bookandgo.domain.usecases.user.UserUseCase
import com.campusdual_grupo3.bookandgo.presentation.MainActivity
import com.campusdual_grupo3.bookandgo.presentation.home.HomeFragment
import com.campusdual_grupo3.bookandgo.presentation.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsAndConditionsFragment : Fragment() {

    private lateinit var binding: FragmentTermsAndConditionsBinding
    private val viewModel: TermsAndConditionsViewModel by viewModels()

    private var name = ""
    private var email = ""
    private var password = ""
    private var phone = ""
    private var address = ""
    private var image = ""
    private var zipcode = ""
    private var preferences = emptyList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTermsAndConditionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadDataFromIntent()
        initObservers()
        initListeners()
    }

    private fun loadDataFromIntent() {
        activity?.intent?.extras?.apply {
            name = getString("NAME", "")
            email = getString("EMAIL", "")
            password = getString("PASSWORD", "")
            phone = getString("PHONE", "")
            address = getString("ADDRESS", "")
            image = getString("IMAGE", "")
            zipcode = getString("ZIPCODE", "")
            preferences = getString("PREFERENCES", "").split(",").toList()
        }
    }

    private fun initListeners() {
        binding.textTerms.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(
                    Uri.parse("https://drive.google.com/uc?export=download&id=1pDE8NQ-stvDihHnDrDkKJWn1ufKpEL-h"),
                    "application/pdf"
                )
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            try {
                requireContext().startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    requireContext(),
                    "No se encontró la aplicación para abrir el documento",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) { // Manejo de errores adicional
                Toast.makeText(
                    requireContext(),
                    "Error al abrir el documento: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        // Habilitar el botón solo si la checkbox está marcada
        binding.checkboxTerms.setOnCheckedChangeListener { _, isChecked ->
            binding.btnRegister.isEnabled = isChecked
        }

        binding.btnRegister.setOnClickListener {
            if (!binding.checkboxTerms.isChecked) {
                Toast.makeText(requireContext(), "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = UserEntity(
                name = this.name,
                email = this.email,
                password = this.password,
                phone = this.phone,
                address = this.address,
                image = this.image,
                zipcode = this.zipcode,
                preferences = this.preferences,
                id = null,
                isActive = null,
                role = null,
                token = null,
                createdAt = null,
                updatedAt = null
            )
            viewModel.register(user)
        }
    }



    private fun initObservers() {
        viewModel?.isRegisteredSuccess?.observe(viewLifecycleOwner) { isSuccess ->
            Toast.makeText(
                this.requireContext(),
                if (isSuccess) "Registro exitoso!" else "Error en el registro, usuario ya registrado o incorrecto",
                Toast.LENGTH_LONG
            ).show()
            if (isSuccess) {
                requireActivity().startActivity(
                    Intent(
                        this.requireContext(),
                        MainActivity::class.java
                    )
                )
                requireActivity().finish()
            }
        }
    }

}