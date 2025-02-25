package com.campusdual_grupo3.bookandgo.presentation.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.campusdual_grupo3.bookandgo.databinding.ActivityWelcomeBinding
import com.campusdual_grupo3.bookandgo.presentation.login.LoginActivity

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar clic en el botón Login
        binding.btnWsLogin.setOnClickListener {
            // Lógica para navegar a la pantalla de inicio de sesión
            navigateToLoginActivity()
        }

        // Configurar clic en el botón Register
        binding.btnRegister.setOnClickListener {
            // Lógica para navegar a la pantalla de registro
        }
    }

    private fun navigateToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}