package com.preet.kotlintemplate.ui

import android.animation.ObjectAnimator
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.preet.kotlintemplate.base.BaseActivity
import com.preet.kotlintemplate.databinding.ActivitySignupBinding
import com.preet.kotlintemplate.utils.FieldType
import com.preet.kotlintemplate.utils.PasswordStrength
import com.preet.kotlintemplate.utils.PasswordStrengthUtil
import com.preet.kotlintemplate.utils.ValidationResult
import com.preet.kotlintemplate.viewmodel.SignupViewModel
import java.util.Calendar


class SignupActivity :
    BaseActivity<ActivitySignupBinding>(ActivitySignupBinding::inflate) {

    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignupBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun setupUI() {

        binding.etPassword.addTextChangedListener {

//            val password = it.toString()
//            viewModel.evaluatePasswordStrength(password)

            val password = it.toString()
            val strength = PasswordStrengthUtil.evaluate(password)

            updatePasswordStrengthUI(strength)
        }

        binding.etConfirmPassword.addTextChangedListener {
            checkPasswordMatch()
        }
        binding.etDob.setOnClickListener {
            showDatePicker()
        }

        binding.btnSubmit.setOnClickListener {
            clearErrors()

            viewModel.validateForm(
                name = binding.etName.text.toString().trim(),
                email = binding.etEmail.text.toString().trim(),
                mobile = binding.etMobile.text.toString().trim(),
                password = binding.etPassword.text.toString().trim(),
                confirmPassword = binding.etConfirmPassword.text.toString().trim(),
                dob = binding.etDob.text.toString().trim(),
                genderId = binding.rgGender.checkedRadioButtonId,
                height = binding.etHeight.text.toString().trim(),
                weight = binding.etWeight.text.toString().trim()
            )
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.tvGoToLogin.setOnClickListener {

            finish() // go back to login
        }
    }

    override fun observeViewModel() {

        viewModel.passwordStrength.observe(this) { strength ->

            when (strength) {

                PasswordStrength.WEAK -> {
                    binding.tvPasswordStrength.text = "Weak"
                    binding.tvPasswordStrength.setTextColor(Color.RED)
//                    binding.progressPasswordStrength.progress = 25
                    ObjectAnimator.ofInt(
                        binding.progressPasswordStrength,
                        "progress",
                        binding.progressPasswordStrength.progress,
                        25
                    ).setDuration(300).start()
                    binding.progressPasswordStrength.progressTintList =
                        ColorStateList.valueOf(Color.RED)
                }

                PasswordStrength.MEDIUM -> {
                    binding.tvPasswordStrength.text = "Medium"
                    binding.tvPasswordStrength.setTextColor(Color.parseColor("#FFA500"))
//                    binding.progressPasswordStrength.progress = 60
                    ObjectAnimator.ofInt(
                        binding.progressPasswordStrength,
                        "progress",
                        binding.progressPasswordStrength.progress,
                        60
                    ).setDuration(300).start()
                    binding.progressPasswordStrength.progressTintList =
                        ColorStateList.valueOf(Color.parseColor("#FFA500"))
                }

                PasswordStrength.STRONG -> {
                    binding.tvPasswordStrength.text = "Strong"
                    binding.tvPasswordStrength.setTextColor(Color.GREEN)
                    //binding.progressPasswordStrength.progress = 100
                    ObjectAnimator.ofInt(
                        binding.progressPasswordStrength,
                        "progress",
                        binding.progressPasswordStrength.progress,
                        100
                    ).setDuration(300).start()
                    binding.progressPasswordStrength.progressTintList =
                        ColorStateList.valueOf(Color.GREEN)
                }
            }
        }

        // We’ll add ViewModel later
        viewModel.validationState.observe(this) { result ->

            when (result) {

                is ValidationResult.Success -> {
                    toast("Signup Successful")
                }

                is ValidationResult.Error -> {
                    handleError(result)
                }
            }
        }
    }
    private fun handleError(error: ValidationResult.Error) {

        when (error.field) {

            FieldType.NAME -> binding.etName.error = error.message
            FieldType.EMAIL -> binding.etEmail.error = error.message
            FieldType.MOBILE -> binding.etMobile.error = error.message
            FieldType.PASSWORD -> binding.etPassword.error = error.message
            FieldType.CONFIRM_PASSWORD -> binding.etConfirmPassword.error = error.message
            FieldType.DOB -> binding.etDob.error = error.message
            FieldType.GENDER -> toast(error.message)
            FieldType.HEIGHT -> binding.etHeight.error = error.message
            FieldType.WEIGHT -> binding.etWeight.error = error.message
        }
    }

    private fun clearErrors() {
        binding.etName.error = null
        binding.etEmail.error = null
        binding.etMobile.error = null
        binding.etPassword.error = null
        binding.etConfirmPassword.error = null
        binding.etDob.error = null
        binding.etHeight.error = null
        binding.etWeight.error = null
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()

        DatePickerDialog(
            this,
            { _, year, month, day ->
                binding.etDob.setText("$day/${month + 1}/$year")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    private fun updatePasswordStrengthUI(
        strength: PasswordStrengthUtil.Strength
    ) {

        when (strength) {

            PasswordStrengthUtil.Strength.WEAK -> {
                binding.tvPasswordStrength.text = "Weak"
                binding.tvPasswordStrength.setTextColor(Color.RED)
//                binding.progressPasswordStrength.progress = 25
                ObjectAnimator.ofInt(
                    binding.progressPasswordStrength,
                    "progress",
                    binding.progressPasswordStrength.progress,
                    25
                ).setDuration(300).start()
                binding.progressPasswordStrength.progressTintList =
                    ColorStateList.valueOf(Color.RED)
            }

            PasswordStrengthUtil.Strength.MEDIUM -> {
                binding.tvPasswordStrength.text = "Medium"
                binding.tvPasswordStrength.setTextColor(Color.parseColor("#FFA500"))
//                binding.progressPasswordStrength.progress = 60
                ObjectAnimator.ofInt(
                    binding.progressPasswordStrength,
                    "progress",
                    binding.progressPasswordStrength.progress,
                    60
                ).setDuration(300).start()
                binding.progressPasswordStrength.progressTintList =
                    ColorStateList.valueOf(Color.parseColor("#FFA500"))
            }

            PasswordStrengthUtil.Strength.STRONG -> {
                binding.tvPasswordStrength.text = "Strong"
                binding.tvPasswordStrength.setTextColor(Color.GREEN)
//                binding.progressPasswordStrength.progress = 100
                ObjectAnimator.ofInt(
                    binding.progressPasswordStrength,
                    "progress",
                    binding.progressPasswordStrength.progress,
                    100
                ).setDuration(300).start()
                binding.progressPasswordStrength.progressTintList =
                    ColorStateList.valueOf(Color.GREEN)
            }
        }
    }


    private fun checkPasswordMatch() {

        val password = binding.etPassword.text.toString()
        val confirm = binding.etConfirmPassword.text.toString()

        if (confirm.isEmpty()) {
            binding.tilConfirmPassword.error = null
            return
        }

        if (password == confirm) {
            binding.tilConfirmPassword.error = null
        } else {
            binding.tilConfirmPassword.error = "Passwords do not match"
        }
    }
}