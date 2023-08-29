package com.arkangel.ulessonsharedlibrary.utils

data class ApiResponse<T>(
    val value: T?,
    val error: Throwable? = null
) {
    val hasError: Boolean
        get() = value == null
}
