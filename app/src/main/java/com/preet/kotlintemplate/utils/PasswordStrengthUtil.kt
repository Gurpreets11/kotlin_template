package com.preet.kotlintemplate.utils

object PasswordStrengthUtil {

    enum class Strength {
        WEAK, MEDIUM, STRONG
    }

    fun evaluate(password: String): Strength {

        var score = 0

        if (password.length >= 6) score++
        if (password.matches(Regex(".*[A-Z].*"))) score++
        if (password.matches(Regex(".*\\d.*"))) score++
        if (password.matches(Regex(".*[@#\$%^&+=].*"))) score++

        return when (score) {
            0, 1 -> Strength.WEAK
            2, 3 -> Strength.MEDIUM
            else -> Strength.STRONG
        }
    }
}