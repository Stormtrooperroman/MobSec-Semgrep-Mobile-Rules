package org.owasp.mastestapp;

import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Log;
import java.security.Key;
import java.security.KeyStore;
import java.util.concurrent.CountDownLatch;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;

/* compiled from: MastgTest.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\rH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/owasp/mastestapp/MastgTest;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "shouldRunInMainThread", "", "getShouldRunInMainThread", "()Z", "mainHandler", "Landroid/os/Handler;", "secretToken", "", "KEY_NAME", "ANDROID_KEYSTORE", "encryptedToken", "", "encryptionIv", "generateSecretKey", "", "getSecretKey", "Ljavax/crypto/SecretKey;", "getCipherForEncryption", "Ljavax/crypto/Cipher;", "getCipherForDecryption", "mastgTest", "app_debug"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MastgTest {
    public static final int $stable = 8;
    private final String ANDROID_KEYSTORE;
    private final String KEY_NAME;
    private final Context context;
    private byte[] encryptedToken;
    private byte[] encryptionIv;
    private final Handler mainHandler;
    private final String secretToken;
    private final boolean shouldRunInMainThread;

    public MastgTest(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.secretToken = "7xK9mP2qR5tY8wZ3aB6cD0eF";
        this.KEY_NAME = "biometric_secret_key";
        this.ANDROID_KEYSTORE = "AndroidKeyStore";
    }

    public final boolean getShouldRunInMainThread() {
        return this.shouldRunInMainThread;
    }

    private final void generateBadSecretKey() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", this.ANDROID_KEYSTORE);
        // ruleid: mastg-android-biometric-event-bound
        keyGenerator.init(new KeyGenParameterSpec.Builder(this.KEY_NAME, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setUserAuthenticationRequired(false).setInvalidatedByBiometricEnrollment(false).setUserAuthenticationParameters(86400, 0).setUserAuthenticationValidityDurationSeconds(86400).build());
        keyGenerator.generateKey();
    }
    private final void generateSecretKey() {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", this.ANDROID_KEYSTORE);
        // ok: mastg-android-biometric-event-bound
        keyGenerator.init(new KeyGenParameterSpec.Builder(this.KEY_NAME, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setUserAuthenticationRequired(true).setInvalidatedByBiometricEnrollment(false).setUserAuthenticationParameters(86400, 0).setUserAuthenticationValidityDurationSeconds(86400).build());
        keyGenerator.generateKey();
    }
}