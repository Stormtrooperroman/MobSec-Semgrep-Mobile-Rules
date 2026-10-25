// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/crypto/RsaNoPadding.java
// hash: a7694d0
package crypto

import javax.crypto.Cipher

/**
 * Code sample taken from : http://cwe.mitre.org/data/definitions/780.html
 */
class RsaNoPadding {
    @Throws(Exception::class)
    fun rsaCipherOk() {
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding")
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA")
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding", "BC")
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance("AES/GCM/NoPadding")
    }

    @Throws(Exception::class)
    fun rsaCipherWeak() {
        // ruleid: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/NONE/NoPadding")
        // ruleid: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/NONE/NoPadding", "BC")
        // ruleid: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/ECB/NoPadding")
    }

    @Throws(Exception::class)
    fun dataflowCipherWeak() {
        // ok: gitlab-android-rsa-nopadding
        val cipher1: String? = null
        Cipher.getInstance(cipher1)
        // ruleid: gitlab-android-rsa-nopadding
        val cipher2 = "RSA/NONE/NoPadding"
        Cipher.getInstance(cipher2)
        // ruleid: gitlab-android-rsa-nopadding
        val cipher3 = "RSA/ECB/NoPadding"
        Cipher.getInstance(cipher3)
    }
}