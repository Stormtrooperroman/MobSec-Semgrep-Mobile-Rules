package org.owasp.mastestapp

import android.content.Context
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricManager.Authenticators.*
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.os.Handler
import android.os.Looper
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Log
import androidx.annotation.RequiresApi
import java.security.KeyStore
import java.util.concurrent.CountDownLatch
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

// SUMMARY: This sample demonstrates event-bound biometric authentication where authenticate() is called without CryptoObject, making it vulnerable to bypass.

class MastgTest(private val context: Context) {

    // Run in background thread - we'll post UI operations to main thread
    val shouldRunInMainThread = false

    private val mainHandler = Handler(Looper.getMainLooper())

    // Secret token (simulating an API key or sensitive data)
    private val secretToken = "7xK9mP2qR5tY8wZ3aB6cD0eF"


    private fun generateBadSecretKey() {
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE
        )
        keyGenerator.init(
            // ruleid: mastg-android-biometric-event-bound
            KeyGenParameterSpec.Builder(
                KEY_NAME,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setUserAuthenticationRequired(false)
                .setInvalidatedByBiometricEnrollment(false)
                .setUserAuthenticationParameters(86400, 0)
                .setUserAuthenticationValidityDurationSeconds(86400)
                .build()
        )
        keyGenerator.generateKey()
    }

    private fun generateSecretKey() {
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE
        )
        keyGenerator.init(
            KeyGenParameterSpec.Builder(
                KEY_NAME,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setUserAuthenticationRequired(true)
                .setInvalidatedByBiometricEnrollment(false)
                .setUserAuthenticationParameters(86400, 0)
                .setUserAuthenticationValidityDurationSeconds(86400)
                .build()
        )
        keyGenerator.generateKey()
    }

}