package com.example.registration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.registration.R
import com.example.registration.databinding.FragmentMainPageBinding
import com.example.registration.presentation.MainPageFragmentViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageFragment : Fragment(R.layout.fragment_main_page) {

    private val viewModel: MainPageFragmentViewModel by viewModel()

    private var _binding: FragmentMainPageBinding? = null
    val binding: FragmentMainPageBinding
        get() = _binding ?: throw IllegalArgumentException("Binding cannot be null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = getBinding(inflater, container)
        viewModel.getNames()
        binding.helloButton.setOnClickListener {
            val userName = viewModel.userLiveData.value?.name.toString()
            val userFamily = viewModel.userLiveData.value?.family.toString()
            val helloMassage = "Hello, $userName $userFamily!"
            MaterialAlertDialogBuilder(requireContext())
                .setMessage(helloMassage)
                .setPositiveButton("OK", null)
                .show()
        }
        return binding.root
    }

    private fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMainPageBinding = FragmentMainPageBinding.inflate(inflater, container, false)

}