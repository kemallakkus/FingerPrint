package com.example.fingerprint.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.example.fingerprint.ui.theme.FingerPrintTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fingerPrintAuthenticator = FingerPrintAuthenticator(this)
        setContent {
            FingerPrintTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    val activity = LocalContext.current as FragmentActivity
                    var message by remember {
                        mutableStateOf("")
                    }
                    TextButton(onClick = {
                        fingerPrintAuthenticator.promptFingerPrintAuth(
                            title = "Login",
                            subtitle = "Use your fingerprint or face id to login",
                            negativeButtonText = "Cancel",
                            fragmentActivity = activity,
                            onSuccess = {
                                message = "Success"
                            },
                            onFailed = {
                                message = "Wrong fingerprint or face id"
                            },
                            onError = { _, errorMessage ->
                                message = "Cancel"
                            }
                        )
                    }) {
                        Text(text = "Login with fingerprint or face id")
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = message)
                }
            }
        }
    }
}