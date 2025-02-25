package com.campusdual_grupo3.bookandgo.presentation.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.campusdual_grupo3.bookandgo.R
// import com.campusdual_grupo3.bookandgo.data.datasource.local.preferences.AppPreferences
import com.campusdual_grupo3.bookandgo.databinding.ActivityOnboardingBinding
import com.campusdual_grupo3.bookandgo.presentation.home.HomeActivity
import com.campusdual_grupo3.bookandgo.presentation.welcome.WelcomeActivity
import com.campusdual_grupo3.bookandgo.utils.animations.Animations
import dagger.hilt.android.AndroidEntryPoint
// import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    private var oldView: View? = null

    /*
    @Inject
    lateinit var appPreferences: AppPreferences

     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        if (appPreferences.getIsOnboardingCompleted()) {
            navigateToMainActivity()
        }

         */

        // Configurar el ViewPager con el adapter
        val onBoardingAdapter = OnBoardingAdapter(this)
        binding.viewPager.adapter = onBoardingAdapter

        // Configurar los bullets
        updateBullets(0)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateBullets(position)
                updateTitleAndDescription(position)
            }
        })

        // Configurar botones Skip y Next
        binding.btnSkip.setOnClickListener {
            // navigateToMainActivity() // Saltar directamente a MainActivity
            navigateToWelcomeActivity()
        }

        binding.btnNext.setOnClickListener {
            if (binding.viewPager.currentItem < onBoardingAdapter.itemCount - 1) {
                binding.viewPager.currentItem += 1
            } else {
                // navigateToMainActivity()
                navigateToWelcomeActivity()
            }
        }

        // Inicializar título y descripción
        updateTitleAndDescription(0)
    }

    private fun updateBullets(position: Int) {
        binding.bullet1.isSelected = position == 0
        binding.bullet2.isSelected = position == 1
        binding.bullet3.isSelected = position == 2

        oldView?.let {
            Animations.animateWidthChange(
                it, 30, 15, 400
            )
        }

        oldView = when (position) {
            0 -> binding.bullet1
            1 -> binding.bullet2
            else -> binding.bullet3
        }

        oldView?.let {
            Animations.animateWidthChange(
                it, 15, 30, 400
            )
        }
    }

    private fun updateTitleAndDescription(position: Int) {
        when (position) {
            0 -> {
                binding.tvTitle.text = getString(R.string.ob_title_1)
                binding.tvDescription.text = getString(R.string.ob_subtitle_1)
            }

            1 -> {
                binding.tvTitle.text = getString(R.string.ob_title_2)
                binding.tvDescription.text = getString(R.string.ob_subtitle_2)
            }

            2 -> {
                binding.tvTitle.text = getString(R.string.ob_title_3)
                binding.tvDescription.text = getString(R.string.ob_subtitle_3)
            }
        }
    }

    /*private fun navigateToMainActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }*/

    private fun navigateToWelcomeActivity() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }
}
