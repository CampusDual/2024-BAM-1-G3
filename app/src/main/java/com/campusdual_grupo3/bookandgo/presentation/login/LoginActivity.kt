package com.campusdual_grupo3.bookandgo.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.databinding.ActivityLoginBinding
import com.campusdual_grupo3.bookandgo.presentation.home.HomeActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding?.root)

        initObservers()
        initListeners()

    }

    private fun initObservers() {
        viewModel?.isLoggingSuccess?.observe(this) { isSuccess ->
            if (isSuccess) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.account_not_found), Toast.LENGTH_LONG
                ).show()
            }

        }
        viewModel?.isLoggingFormatValid?.observe(this) { isFormatValid ->
            if (!isFormatValid) {
                Toast.makeText(this, getString(R.string.wrong_data), Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun initListeners() {
        binding?.textLoginMail?.doOnTextChanged { text, _, _, _ ->
            viewModel?.setEmail(text.toString())
        }
        binding?.textLoginPassword?.doOnTextChanged { text, _, _, _ ->
            viewModel?.setPassword(text.toString())
        }
        binding?.btnLgsLogin?.setOnClickListener {
            viewModel?.login()

        }
        binding?.inLoginForgotPW?.setOnClickListener { _ ->
            viewModel?.recoverPassword()

            val dialog = MaterialAlertDialogBuilder(this)
                .setTitle(
                    binding?.textLoginMail?.text.toString() +
                            getString(R.string.forgot_password_toast_msg)
                )
                .setPositiveButton(getString(R.string.btn_close)) { dialog, _ ->
                    dialog.dismiss()
                }

            // Listener para cuando se cierra el diálogo (se ejecuta al hacer clic en "Cerrar" o fuera del diálogo)
            dialog.setOnDismissListener {
                binding?.textLoginMail?.text?.clear()
                binding?.textLoginPassword?.text?.clear()
            }

            dialog.show()
        }

    }


}

