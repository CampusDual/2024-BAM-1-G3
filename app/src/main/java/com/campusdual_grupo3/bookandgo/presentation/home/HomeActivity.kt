package com.campusdual_grupo3.bookandgo.presentation.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.campusdual_grupo3.bookandgo.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    /*
    @Inject
    lateinit var appPreferences: AppPreferences

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        /*
        if (!appPreferences.getIsOnboardingCompleted()) {
            appPreferences.setIsOnboardingCompleted(true)
        }
        */

    }
}