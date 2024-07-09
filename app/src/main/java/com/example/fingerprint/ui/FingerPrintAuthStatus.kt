package com.example.fingerprint.ui

enum class FingerPrintAuthStatus(val id: Int) {
    READY(1),
    NOT_AVAILABLE(-1),
    TEMPORARILY_UNAVAILABLE(-2),
    AVAILABLE_BUT_NOT_ENROLLED(-3)
}