package com.campusdual_grupo3.bookandgo.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.campusdual_grupo3.bookandgo.databinding.FragmentProfileBinding
import com.campusdual_grupo3.bookandgo.presentation.MainActivity
import com.campusdual_grupo3.bookandgo.presentation.login.LoginActivity


class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding?.composeView?.setContent {
            ProfileScreen(
                onBackClick = {
                    (activity as? MainActivity)?.openHome()
                },
                onLogoutSuccess = {
                    navigateToLogin() // Redirige al login tras logout
                }
            )
        }
        return binding?.root
    }
    
    private fun navigateToLogin() {
        // Aquí puedes abrir tu pantalla de login como sea necesario.
        // Por ejemplo, si tu LoginActivity es la pantalla inicial:
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
