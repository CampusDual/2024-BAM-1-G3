package com.campusdual_grupo3.bookandgo.presentation.register

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.databinding.ActivityRegisterBinding
import com.campusdual_grupo3.bookandgo.presentation.navigation.NavigationHelper
import com.campusdual_grupo3.bookandgo.presentation.register.fragments.personalinfo.PersonalInfoFragment
import com.campusdual_grupo3.bookandgo.presentation.register.fragments.preferences.PreferencesFragment
import com.campusdual_grupo3.bookandgo.presentation.register.fragments.termsandconds.TermsAndConditionsFragment
import com.campusdual_grupo3.bookandgo.presentation.register.fragments.uploadpic.UploadPicFragment
import com.campusdual_grupo3.bookandgo.utils.config.AppGlobalConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DURATION = 800L

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private var position: Int = 1

    private lateinit var clipDrawable: ClipDrawable
    private lateinit var animator: ObjectAnimator
    private lateinit var gradientDrawable: GradientDrawable

    private var navigationHelper = NavigationHelper()

    private var currentFragment: Fragment? = null

    private var personalInfoFragment = PersonalInfoFragment()
    private var uploadPicFragment = UploadPicFragment()
    private var preferencesFragment = PreferencesFragment()
    private var termsAndConditionsFragment = TermsAndConditionsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupOnBackPressedObserver()

        setupStepperPos(1)
        openPersonalInfo()

//        lifecycleScope.launch {
//            (2..4).forEach { position ->
//                startLineTransition(position)
//                delay(DURATION)
//                setupStepperPos(position)
//                nextStep()
//            }
//            (3 downTo 1).forEach { position ->
//                delay(DURATION)
//                setupStepperPos(position)
//                prevStep()
//                returnLineTransition(position)
//            }
//        }
    }

    private fun startLineTransition(position: Int) {
        val lineView = when (position) {
            2 -> binding.stepper.lineOne
            3 -> binding.stepper.lineTwo
            else -> binding.stepper.lineThree
        }

        // Establecer el color inicial de la línea a gris
        lineView.setBackgroundColor(Color.WHITE)

        // Crear un ValueAnimator para animar el cambio de color de gris a negro
        val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), Color.WHITE, Color.BLACK)
        colorAnimator.duration = DURATION // Duración de la animación en milisegundos
        colorAnimator.addUpdateListener { animator ->
            val animatedColor = animator.animatedValue as Int
            lineView.setBackgroundColor(animatedColor)
        }

        // Crear un ObjectAnimator para animar la escala X de 0 a 1 (de ancho 0% a 100%)
        val scaleAnimator = ObjectAnimator.ofFloat(lineView, "scaleX", 0f, 1f)
        scaleAnimator.duration = DURATION // Duración de la animación en milisegundos
        scaleAnimator.interpolator = LinearInterpolator() // Interpolador lineal para movimiento uniforme

        // Establecer el punto de pivote en el inicio de la vista (izquierda) para que crezca hacia la derecha
        lineView.pivotX = 0f

        // Iniciar ambas animaciones simultáneamente
        AnimatorSet().apply {
            playTogether(colorAnimator, scaleAnimator)
            start()
        }
    }
    private fun returnLineTransition(position: Int) {
        val lineView = when (position) {
            1 -> binding.stepper.lineOne
            2 -> binding.stepper.lineTwo
            else -> binding.stepper.lineThree
        }

        // Crear un ValueAnimator para animar el cambio de color de gris a negro
        val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), Color.BLACK, Color.WHITE)
        colorAnimator.duration = DURATION // Duración de la animación en milisegundos
        colorAnimator.addUpdateListener { animator ->
            val animatedColor = animator.animatedValue as Int
            lineView.setBackgroundColor(animatedColor)
        }

        // Crear un ObjectAnimator para animar la escala X de 0 a 1 (de ancho 0% a 100%)
        val scaleAnimator = ObjectAnimator.ofFloat(lineView, "scaleX", 1f, 0f)
        scaleAnimator.duration = DURATION // Duración de la animación en milisegundos
        scaleAnimator.interpolator = LinearInterpolator() // Interpolador lineal para movimiento uniforme

        // Establecer el punto de pivote en el inicio de la vista (izquierda) para que crezca hacia la derecha
        lineView.pivotX = 1f

        // Iniciar ambas animaciones simultáneamente
        AnimatorSet().apply {
            playTogether(colorAnimator, scaleAnimator)
            start()
        }
    }

    private fun setupToolbar() {
        this.setSupportActionBar(binding.toolbarRegister)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupOnBackPressedObserver() {
        onBackPressedDispatcher.addCallback {
            prevStep()
            currentFragment = navigationHelper.backStackFragment(this@RegisterActivity, currentFragment)
        }
    }

    private fun setupStepperPos(newPosition: Int) {
        position = newPosition
    }

    private fun prevStep() {
        lifecycleScope.launch {
            setupStepperPos(position - 1)
            when (position) {
                1 -> {
                    binding.stepper.circleTwo.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_profile_pic, null)
                    binding.stepper.circleOne.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_personal_info_selected, null)
                    binding.stepper.lineOne.setBackgroundColor(Color.GRAY)
                }
                2 -> {
                    binding.stepper.circleThree.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_preferences, null)
                    binding.stepper.circleTwo.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_profile_pic_selected, null)
                    binding.stepper.lineTwo.setBackgroundColor(Color.GRAY)

                }
                3 -> {
                    binding.stepper.circleFour.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_done, null)
                    binding.stepper.circleThree.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_preferences_selected, null)
                    binding.stepper.lineThree.setBackgroundColor(Color.GRAY)

                }
            }
            returnLineTransition(position)
        }

    }

    private fun nextStep() {
        lifecycleScope.launch {
            setupStepperPos(position + 1)
            startLineTransition(position)
            delay(DURATION)
            when (position) {
                2 -> {
                    binding.stepper.circleTwo.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_profile_pic_selected, null)
                }
                3 -> {
                    binding.stepper.circleThree.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_preferences_selected, null)
                }
                4 -> {
                    binding.stepper.circleFour.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_step_done_selected, null)
                }
            }
        }
    }

    fun openPersonalInfo() {
        if (currentFragment !is PersonalInfoFragment) {
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                personalInfoFragment, AppGlobalConstants.F_PERSONAL_INFO)
        }
    }

    fun openUploadPicture() {
        nextStep()
        if (currentFragment !is UploadPicFragment) {
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                uploadPicFragment, AppGlobalConstants.F_UPLOAD_PICTURE)
        }
    }
    fun openPreferences() {
        nextStep()
        if (currentFragment !is PreferencesFragment) {
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                preferencesFragment, AppGlobalConstants.F_PREFERENCES)
        }
    }

    fun openTermsandconditions() {
        nextStep()
        if (currentFragment !is TermsAndConditionsFragment) {
            currentFragment = navigationHelper.showFragment(
                this, currentFragment,
                termsAndConditionsFragment, AppGlobalConstants.F_TERMS_AND_CONDITIONS)
        }
    }


}