package com.example.registration.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.example.registration.R
import com.example.registration.databinding.FragmentRegistrationBinding
import com.example.registration.presentation.*
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private val viewModel: RegistrationFragmentViewModel by viewModel()

    private var _binding: FragmentRegistrationBinding? = null
    val binding: FragmentRegistrationBinding
        get() = _binding ?: throw IllegalArgumentException("Binding cannot be null")

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private var confirm: Boolean = false

    private fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentRegistrationBinding = FragmentRegistrationBinding.inflate(inflater, container, false)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = getBinding(inflater, container)
        setListeners()
        setDatePicker()
        setObservers()
        return binding.root
    }

    private fun setDatePicker() {
        val datePickerDialogBuilder = MaterialDatePicker.Builder.datePicker()
            .setTitleText(R.string.calendar_title)
            .build()
        binding.datePickButton.setOnClickListener {
            if (!datePickerDialogBuilder.isAdded) {
                datePickerDialogBuilder.show(
                    childFragmentManager,
                    getString(R.string.calendar_title)
                )
                datePickerDialogBuilder.addOnPositiveButtonClickListener {
                    val date = datePickerDialogBuilder.selection
                    date.let {
                        viewModel.dateFormat(date!!)
                    }
                }
            }
        }
    }

    private fun setListeners() {
        with(binding) {
            registrationButton.setOnClickListener {
                if (confirm &&
                    nameEditor.text != null &&
                    familyEditor.text != null &&
                    birthDateEditor.text != null &&
                    passwordEditor.text != null &&
                    passwordConfirmEditor.text != null
                ) {
                    viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
                        viewModel.registration(
                            viewModel.nameLiveData.value.toString(),
                            viewModel.familyLiveData.value.toString()
                        )
                    }
                    viewModel.openMainPage()
                }
            }
            nameEditor.doOnTextChanged { text, _, _, _ ->
                viewModel.nameLiveData.value = text.toString()
                viewModel.validateName()
            }
            familyEditor.doOnTextChanged { text, _, _, _ ->
                viewModel.familyLiveData.value = text.toString()
                viewModel.validateFamily()
            }
            birthDateEditor.doOnTextChanged { text, _, _, _ ->
                viewModel.birthDateLiveData.value = text.toString()
                viewModel.validateBirthDate()
            }
            passwordEditor.doOnTextChanged { text, _, _, _ ->
                viewModel.passwordLiveData.value = text.toString()
                viewModel.validatePassword()
            }
            passwordConfirmEditor.doOnTextChanged { text, _, _, _ ->
                viewModel.passwordConfirmLiveData.value = text.toString()
                viewModel.validatePasswordConfirm()
            }
        }
    }

    private fun setObservers() {
        viewModel.nameLiveData.observe(viewLifecycleOwner) {
            if (it != binding.nameEditor.text.toString())
                binding.nameEditor.setText(it)
        }
        viewModel.familyLiveData.observe(viewLifecycleOwner) {
            if (it != binding.familyEditor.text.toString())
                binding.familyEditor.setText(it)
        }
        viewModel.birthDateLiveData.observe(viewLifecycleOwner) {
            if (it != binding.birthDateEditor.text.toString())
                binding.birthDateEditor.setText(it)
        }
        viewModel.passwordLiveData.observe(viewLifecycleOwner) {
            if (it != binding.passwordEditor.text.toString())
                binding.passwordEditor.setText(it)
        }
        viewModel.passwordConfirmLiveData.observe(viewLifecycleOwner) {
            if (it != binding.passwordConfirmEditor.text.toString())
                binding.passwordConfirmEditor.setText(it)
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            when (it) {
                EMPTY_NAME_ERROR -> binding.nameTitle.error = it
                NAME_ERROR -> binding.nameTitle.error = it
                EMPTY_FAMILY_ERROR -> binding.familyTitle.error = it
                FAMILY_ERROR -> binding.familyTitle.error = it
                EMPTY_PASSWORD_ERROR -> binding.passwordTitle.error = it
                EMPTY_PASSWORD_CONFIRM_ERROR -> binding.passwordConfirmTitle.error = it
                PASSWORD_CONFIRM_ERROR -> {
                    binding.passwordTitle.error = it
                    binding.passwordConfirmTitle.error = it
                }
                BIRTH_DATE_ERROR -> binding.birthDateTitle.error = it
                NO_ERROR -> {
                    binding.nameTitle.error = null
                    binding.familyTitle.error = null
                    binding.birthDateTitle.error = null
                    binding.passwordTitle.error = null
                    binding.passwordConfirmTitle.error = null
                }
            }
            confirm = when (it) {
                NO_ERROR -> true
                else -> false
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}