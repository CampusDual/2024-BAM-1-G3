package com.campusdual_grupo3.bookandgo.presentation.register.fragments.preferences

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.campusdual_grupo3.bookandgo.R
import com.campusdual_grupo3.bookandgo.databinding.FragmentPreferencesBinding
import com.campusdual_grupo3.bookandgo.domain.entities.CategoryEntity
import com.campusdual_grupo3.bookandgo.presentation.register.RegisterActivity
import com.campusdual_grupo3.bookandgo.presentation.register.fragments.DropdownAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PreferencesFragment : Fragment() {

    private lateinit var binding: FragmentPreferencesBinding
    private val viewModel: PreferencesViewModel by viewModels()

    private var preferencesAdapter: DropdownAdapter? = null

    private var hasToShowPreferences: Boolean = false

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
        binding = FragmentPreferencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapters()
        initObservers()
        initListeners()
        viewModel.loadCategories()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.uiState
                .map { it.preferences }
                .collect { preferences ->
                    preferencesAdapter?.submitList(preferences)
                }
        }

        lifecycleScope.launch {
            viewModel.uiState
                .map { it.isLoading }
                .collect { isLoading ->
                }
        }
    }

    private fun initListeners() {

        activity?.intent?.extras?.apply {
            name = getString("NAME", "")
            email = getString("EMAIL", "")
            password = getString("PASSWORD", "")
            phone = getString("PHONE", "")
            address = getString("ADDRESS","")
            image = getString("IMAGE", "")
            zipcode = getString("ZIPCODE", "")
        }

        binding.imagePreferedExp.setOnClickListener {
            hasToShowPreferences = !hasToShowPreferences
            binding.recyclerPreferences.isVisible = hasToShowPreferences
            binding.imagePreferedExp.setImageResource(
                if (hasToShowPreferences) {
                    R.drawable.ic_arrow_up_black
                } else {
                    R.drawable.ic_arrow_down_black
                }
            )
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
            val preferences = viewModel.preferencesSelected.joinToString(",")
            bundle.putString("PREFERENCES", preferences)

            activity?.intent?.putExtras(bundle)
            (activity as? RegisterActivity)?.openTermsandconditions()
        }
    }

    private fun initAdapters() {
        preferencesAdapter = DropdownAdapter { preference ->
            viewModel.onSelectPreference(preference)
        }
        binding.recyclerPreferences.adapter = preferencesAdapter

    }
}