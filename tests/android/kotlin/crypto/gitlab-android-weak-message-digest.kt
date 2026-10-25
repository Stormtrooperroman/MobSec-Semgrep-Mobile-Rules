// License: LGPL-3.0 License (c) find-sec-bugs
package crypto

import java.security.*

@Suppress("DEPRECATION")
object WeakMessageDigest {
    @Throws(NoSuchProviderException::class, NoSuchAlgorithmException::class)
    fun weakDigestMoreSig() {
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD5", "SUN")
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD4", "SUN")
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD2", "SUN")
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD5")
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD4")
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD2")
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD5", ExampleProvider())
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD4", ExampleProvider())
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD2", ExampleProvider())
        MessageDigest.getInstance("SHA", "SUN")
        MessageDigest.getInstance("SHA", ExampleProvider())
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA1", "SUN")
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA1", ExampleProvider())
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA-1", "SUN")
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA-1", ExampleProvider())
        MessageDigest.getInstance("sha-384", "SUN") // OK!
        MessageDigest.getInstance("SHA-512", "SUN") // OK!

        // ruleid: gitlab-android-weak-message-digest
        Signature.getInstance("MD5withRSA")
        // ruleid: gitlab-android-weak-message-digest
        Signature.getInstance("MD2withDSA", "X")
        // ruleid: gitlab-android-weak-message-digest
        Signature.getInstance("SHA1withRSA", ExampleProvider())
        Signature.getInstance("SHA256withRSA") // OK
        Signature.getInstance("uncommon name", "") // OK
    }

    internal class ExampleProvider : Provider("example", 1.0, "")
}