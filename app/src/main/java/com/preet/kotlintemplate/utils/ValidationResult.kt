package com.preet.kotlintemplate.utils

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String, val field: FieldType) : ValidationResult()
}

enum class FieldType {
    NAME,
    EMAIL,
    MOBILE,
    PASSWORD,
    CONFIRM_PASSWORD,
    DOB,
    GENDER,
    HEIGHT,
    WEIGHT
}