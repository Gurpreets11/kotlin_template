package com.preet.kotlintemplate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.preet.kotlintemplate.base.BaseViewModel
import com.preet.kotlintemplate.utils.AgeUtil
import com.preet.kotlintemplate.utils.FieldType
import com.preet.kotlintemplate.utils.PasswordStrength
import com.preet.kotlintemplate.utils.ValidationResult

class SignupViewModel : BaseViewModel() {

    private val _validationState = MutableLiveData<ValidationResult>()
    val validationState: LiveData<ValidationResult> = _validationState

    fun validateForm(
        name: String,
        email: String,
        mobile: String,
        password: String,
        confirmPassword: String,
        dob: String,
        genderId: Int,
        height: String,
        weight: String
    ) {

        when {
            name.isEmpty() ->
                _validationState.value =
                    ValidationResult.Error("Enter name", FieldType.NAME)

            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                _validationState.value =
                    ValidationResult.Error("Enter valid email", FieldType.EMAIL)

            !isValidMobile(mobile) ->
                _validationState.value =
                    ValidationResult.Error("Enter valid 10-digit mobile", FieldType.MOBILE)

            !isStrongPassword(password) ->
                _validationState.value =
                    ValidationResult.Error(
                        "Password must contain 1 uppercase, 1 number & 6+ chars",
                        FieldType.PASSWORD
                    )

            !isPasswordMatch(password , confirmPassword) ->
                _validationState.value =
                    ValidationResult.Error("Passwords do not match", FieldType.PASSWORD)


            dob.isEmpty() ->
                _validationState.value =
                    ValidationResult.Error("Select DOB", FieldType.DOB)

            !AgeUtil.isAdult(dob) ->
                _validationState.value =
                    ValidationResult.Error("You must be at least 18 years old", FieldType.DOB)

            genderId == -1 ->
                _validationState.value =
                    ValidationResult.Error("Select gender", FieldType.GENDER)

            height.isEmpty() ->
                _validationState.value =
                    ValidationResult.Error("Enter height", FieldType.HEIGHT)

            weight.isEmpty() ->
                _validationState.value =
                    ValidationResult.Error("Enter weight", FieldType.WEIGHT)

            else ->
                _validationState.value = ValidationResult.Success
        }
    }

    private fun isValidMobile(mobile: String): Boolean {
        return mobile.matches(Regex("^[6-9]\\d{9}\$"))
    }

    private fun isStrongPassword(password: String): Boolean {
        return password.matches(
            Regex("^(?=.*[A-Z])(?=.*\\d).{6,}\$")
        )
    }


    private val _passwordStrength = MutableLiveData<PasswordStrength>()
    val passwordStrength: LiveData<PasswordStrength> = _passwordStrength

    fun evaluatePasswordStrength(password: String) {

        var score = 0

        if (password.length >= 6) score++
        if (password.matches(Regex(".*[A-Z].*"))) score++
        if (password.matches(Regex(".*\\d.*"))) score++
        if (password.matches(Regex(".*[@#\$%^&+=].*"))) score++

        when (score) {
            0, 1 -> _passwordStrength.value = PasswordStrength.WEAK
            2, 3 -> _passwordStrength.value = PasswordStrength.MEDIUM
            4 -> _passwordStrength.value = PasswordStrength.STRONG
        }
    }


      private fun isPasswordMatch(password: String, confirm: String) : Boolean {



        if (confirm.isEmpty()) {
            //binding.tilConfirmPassword.error = null
            return false;
        }

        if (password == confirm) {
//            binding.tilConfirmPassword.error = null
            return true;
        } else {
//            binding.tilConfirmPassword.error = "Passwords do not match"
            return false;
        }
    }

}