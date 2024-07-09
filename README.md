# Fingerprint Authentication Project

This project provides fingerprint authentication functionality in an Android application. It allows users to log in using their fingerprints.

## Technologies Used

- Kotlin
- Android Jetpack Compose
- AndroidX Biometric Library

### Initializing the Fingerprint Authenticator

```kotlin
implementation("androidx.biometric:biometric:1.2.0")
```

To initialize the fingerprint authenticator, use the `FingerPrintAuthenticator` class. You can check if the device is suitable for fingerprint authentication using the `isFingerPrintAuthAvailable` function.

```kotlin
val fingerPrintAuthenticator = FingerPrintAuthenticator(context)
val status = fingerPrintAuthenticator.isFingerPrintAuthAvailable()
```

### Fingerprint Authentication Prompt

To start the fingerprint authentication prompt, use the `promptFingerPrintAuth` function. This function takes callbacks that are called when authentication is successful, fails, or an error occurs.

```kotlin
fingerPrintAuthenticator.promptFingerPrintAuth(
    title = "Login",
    subtitle = "Use your fingerprint or face ID to login",
    negativeButtonText = "Cancel",
    fragmentActivity = activity,
    onSuccess = {
        message = "Success"
    },
    onFailed = {
        message = "Wrong fingerprint or face ID"
    },
    onError = { _, errorMessage ->
        message = "Canceled"
    }
)
```

### `FingerPrintAuthStatus` Enum

The FingerPrintAuthStatus enum class indicates the status of fingerprint authentication.

- `READY`: Fingerprint authentication is ready.
- `NOT_AVAILABLE`: Fingerprint authentication hardware is not available.
- `TEMPORARILY_UNAVAILABLE`: Fingerprint authentication is temporarily unavailable.
- `AVAILABLE_BUT_NOT_ENROLLED`: Fingerprint authentication is available but no fingerprint or face ID is enrolled.

<p align="center">
  <img src="https://github.com/kemallakkus/FingerPrint/assets/105845393/f63ea469-513a-42f0-84f9-07182411cbb9" alt="Photo 1" width="200"/>
  <img src="https://github.com/kemallakkus/FingerPrint/assets/105845393/cae2a8dd-9bd1-4900-995f-5c399e206540" alt="Photo 2" width="200"/>
  <img src="https://github.com/kemallakkus/FingerPrint/assets/105845393/9517891b-cdce-4182-bbeb-4417510e16f2" alt="Photo 3" width="200"/>
  <img src="https://github.com/kemallakkus/FingerPrint/assets/105845393/cda6835d-af0c-4854-bfa5-ce837c316d8a" alt="Photo 4" width="200"/>
  <img src="https://github.com/kemallakkus/FingerPrint/assets/105845393/a5a1bbbb-dc85-4ba7-a830-6a65253207d7" alt="Photo 4" width="200"/>
</p>



