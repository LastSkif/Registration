package com.example.registration.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.registration.domain.entity.User
import com.example.registration.domain.usecase.InsertUserUseCase
import java.text.SimpleDateFormat

const val NAME_ERROR = "Имя должно быть длиннее двух символов"
const val FAMILY_ERROR = "Минимум три символа"
const val BIRTH_DATE_ERROR = "Формат — дд.мм.гггг"
const val PASSWORD_CONFIRM_ERROR = "Пароли не совпадают"
const val EMPTY_PASSWORD_ERROR = "Поле не должно быть пустым"
const val EMPTY_PASSWORD_CONFIRM_ERROR = "Подтвердите пароль"
const val EMPTY_NAME_ERROR = "Имя не должно быть пустым"
const val EMPTY_FAMILY_ERROR = "Фамилия не должна быть пустой"
const val NO_ERROR = ""

class RegistrationFragmentViewModel(
    private val router: UserRouter,
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel() {

    val nameLiveData = MutableLiveData<String>()
    val familyLiveData = MutableLiveData<String>()
    val birthDateLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordConfirmLiveData = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<String>()

    fun openMainPage() {
        errorLiveData.value = NO_ERROR
        router.openMainPage()
    }

    suspend fun registration(name: String, family: String) {
        insertUserUseCase.invoke(User(name, family))
    }

    fun validateName() {
        when {
            nameLiveData.value == null -> errorLiveData.value = EMPTY_NAME_ERROR
            nameLiveData.value!!.length < 3 -> errorLiveData.value = NAME_ERROR
            else -> errorLiveData.value = NO_ERROR
        }
    }

    fun validateFamily() {
        when {
            familyLiveData.value == null -> errorLiveData.value = EMPTY_FAMILY_ERROR
            familyLiveData.value!!.length < 3 -> errorLiveData.value = FAMILY_ERROR
            else -> errorLiveData.value = NO_ERROR
        }
    }

    fun validateBirthDate() {
        when (birthDateLiveData.value) {
            null -> errorLiveData.value = BIRTH_DATE_ERROR
            else -> errorLiveData.value = NO_ERROR
        }
    }

    fun validatePassword() {
        when {
            passwordLiveData.value == null -> errorLiveData.value = EMPTY_PASSWORD_ERROR
            passwordLiveData.value.toString() != passwordConfirmLiveData.value.toString() -> errorLiveData.value =
                PASSWORD_CONFIRM_ERROR
            else -> errorLiveData.value = NO_ERROR
        }
    }

    fun validatePasswordConfirm() {
        when {
            passwordConfirmLiveData.value == null -> errorLiveData.value =
                EMPTY_PASSWORD_CONFIRM_ERROR
            passwordLiveData.value.toString() != passwordConfirmLiveData.value.toString() -> errorLiveData.value =
                PASSWORD_CONFIRM_ERROR
            else -> errorLiveData.value = NO_ERROR
        }
    }

    fun dateFormat(date: Long) {
        val format = SimpleDateFormat("dd.MM.yyyy")
        val finalDate = String.format(format.format(date))
        birthDateLiveData.value = finalDate
    }
}