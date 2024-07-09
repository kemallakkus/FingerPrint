package com.example.fingerprint.ui

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity

class FingerPrintAuthenticator(
    private val context: Context,
) {
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private lateinit var biometricPrompt: BiometricPrompt
    private val biometricManager = BiometricManager.from(context)

    fun isFingerPrintAuthAvailable(): FingerPrintAuthStatus {
        return when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> FingerPrintAuthStatus.READY
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> FingerPrintAuthStatus.NOT_AVAILABLE
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> FingerPrintAuthStatus.TEMPORARILY_UNAVAILABLE
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> FingerPrintAuthStatus.AVAILABLE_BUT_NOT_ENROLLED
            else -> FingerPrintAuthStatus.NOT_AVAILABLE
        }
    }

    fun promptFingerPrintAuth(
        title: String,
        subtitle: String,
        negativeButtonText: String,
        fragmentActivity: FragmentActivity,
        onSuccess: (result: BiometricPrompt.AuthenticationResult) -> Unit,
        onFailed: () -> Unit,
        onError: (errorCode: Int, errorMessage: String) -> Unit,
    ) {
        when (isFingerPrintAuthAvailable()) {
            FingerPrintAuthStatus.NOT_AVAILABLE -> {
                onError(FingerPrintAuthStatus.NOT_AVAILABLE.id, "Not available for this device.")
                return
            }

            FingerPrintAuthStatus.TEMPORARILY_UNAVAILABLE -> {
                onError(FingerPrintAuthStatus.TEMPORARILY_UNAVAILABLE.id, "Not available at this moment.")
                return
            }

            FingerPrintAuthStatus.AVAILABLE_BUT_NOT_ENROLLED -> {
                onError(
                    FingerPrintAuthStatus.AVAILABLE_BUT_NOT_ENROLLED.id,
                    "You should add a fingerprint or a face id first"
                )
                return
            }

            else -> Unit
        }

        biometricPrompt = BiometricPrompt(
            fragmentActivity,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess(result)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    onError(errorCode, errorCode.toString())
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    onFailed()
                }
            }
        )

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setNegativeButtonText(negativeButtonText)
            .build()
        biometricPrompt.authenticate(promptInfo)
    }
}