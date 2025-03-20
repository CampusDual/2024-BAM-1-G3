package com.campusdual_grupo3.bookandgo.presentation.register.fragments.personalinfo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.campusdual_grupo3.bookandgo.databinding.FragmentPersonalInfoBinding
import com.campusdual_grupo3.bookandgo.presentation.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalInfoFragment : Fragment() {

    private lateinit var binding: FragmentPersonalInfoBinding
    private val viewModel: PersonalInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)

        initObservers()
        initListeners()

        return binding.root

    }

    private fun initListeners() {
        with(binding) {
            this?.tiName?.doOnTextChanged() { text, start, before, count ->
                viewModel?.setUsername(text.toString())
            }
            this?.tiPhone?.doOnTextChanged { text, start, before, count ->
                viewModel?.setPhone(text.toString())
            }
            this?.tiAddress?.doOnTextChanged { text, start, before, count ->
                viewModel?.setAddress(text.toString())
            }
            this?.tiEmail?.doOnTextChanged { text, start, before, count ->
                viewModel?.setEmail(text.toString())
            }
            this?.tiPass?.doOnTextChanged { text, start, before, count ->
                viewModel?.setPassword(text.toString())
            }
            this?.tiZipcode?.doOnTextChanged { text, start, before, count ->
                viewModel?.setZipcode(text.toString())
            }
            this?.btnRegister?.setOnClickListener {
                if (viewModel.error.value == true ||
                    viewModel.errorPhone.value == true || 
                    viewModel.errorAddress.value == true || 
                    viewModel.errorZipcode.value == true || 
                    viewModel.errorEmail.value == true || 
                    viewModel.errorPassword.value == true) {
                    Toast.makeText(requireContext(), "Por favor, corrige los errores antes de continuar.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Código existente para manejar el registro
                val bundle = Bundle()
                bundle.putString("NAME", binding.tiName.text?.toString() ?: "")
                bundle.putString("EMAIL", binding.tiEmail.text?.toString() ?: "")
                bundle.putString("PASSWORD", binding.tiPass.text?.toString() ?: "")
                bundle.putString("PHONE", binding.tiPhone.text?.toString() ?: "")
                bundle.putString("ADDRESS", binding.tiAddress.text?.toString() ?: "")
                bundle.putString("ZIPCODE", binding.tiZipcode.text?.toString() ?: "")

                if (binding.tiName.text.isNullOrEmpty() ||
                    binding.tiEmail.text.isNullOrEmpty() || 
                    binding.tiPass.text.isNullOrEmpty() || 
                    binding.tiPhone.text.isNullOrEmpty() || 
                    binding.tiAddress.text.isNullOrEmpty() || 
                    binding.tiZipcode.text.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Todos los campos son obligatorios.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                // Solo navegar si no hay errores
                activity?.intent?.putExtras(bundle)
                (activity as? RegisterActivity)?.openUploadPicture()
            }
        }
    }

    private fun initObservers() {
        viewModel?.error?.observe(viewLifecycleOwner) { hasError ->
            binding?.tiNameLayout?.error =
                if (hasError) "Campo obligatorio: El máximo de carácteres permitidos es 50." else null
            binding?.tiNameLayout?.isErrorEnabled = hasError
        }
        viewModel?.errorPhone?.observe(viewLifecycleOwner) { hasError ->
            binding?.tiPhoneLayout?.error = if (hasError) "Campo obligatorio: El teléfono debe estar correcto." else null
            binding?.tiPhoneLayout?.isErrorEnabled = hasError
        }
        viewModel?.errorAddress?.observe(viewLifecycleOwner) { hasError ->
            binding?.tiAddressLayout?.error = if (hasError) "Campo obligatorio: La dirección debe estar correcta." else null
            binding?.tiAddressLayout?.isErrorEnabled = hasError
        }
        viewModel?.errorZipcode?.observe(viewLifecycleOwner) { hasError ->
            binding?.tiZipcodeLayout?.error = if (hasError) "Campo obligatorio: El código postal debe estar correcto." else null
            binding?.tiZipcodeLayout?.isErrorEnabled = hasError
        }
        viewModel?.errorEmail?.observe(viewLifecycleOwner) { hasError ->
            binding?.tiEmailLayout?.error = if (hasError) "Campo obligatorio: Formato de email inválido." else null
            binding?.tiEmailLayout?.isErrorEnabled = hasError
        }
        viewModel?.errorPassword?.observe(viewLifecycleOwner) { hasError ->
            binding?.tiPassLayout?.error = if (hasError) "Campo obligatorio: La contraseña debe tener 1 mayus., 1 número y 1 signo." else null
            binding?.tiPassLayout?.isErrorEnabled = hasError
        }
        viewModel?.isRegisteredSuccess?.observe(viewLifecycleOwner) { isSuccess ->
            Toast.makeText(
                this.requireContext(),
                if (isSuccess) "Registro exitoso!" else "Error en el registro, usuario ya registrado o incorrecto",
                Toast.LENGTH_LONG
            ).show()
            if (isSuccess) {
                // Aquí puedes manejar la navegación si es necesario
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}