package com.example.registration.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.domain.entity.User
import com.example.registration.domain.usecase.GetUserNamesUseCase
import kotlinx.coroutines.launch

class MainPageFragmentViewModel(
    private val getUserNamesUseCase: GetUserNamesUseCase
) : ViewModel() {

    val userLiveData = MutableLiveData<User>()

    fun getNames() {
        viewModelScope.launch {
            userLiveData.value = getUserNamesUseCase.invoke()
        }
    }
}