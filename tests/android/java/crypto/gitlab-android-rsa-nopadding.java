// License: LGPL-3.0 License (c) find-sec-bugs
// source: https://github.com/find-sec-bugs/find-sec-bugs/blob/master/findsecbugs-samples-java/src/test/java/testcode/crypto/RsaNoPadding.java
// hash: a7694d0

package crypto;

import javax.crypto.Cipher;

/**
 * Code sample taken from : http://cwe.mitre.org/data/definitions/780.html
 */
public class RsaNoPadding {

    public void rsaCipherOk() throws Exception {
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding");
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA");
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/ECB/OAEPWithMD5AndMGF1Padding", "BC");
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance("AES/GCM/NoPadding");
    }

    public void rsaCipherWeak() throws Exception {
        // ruleid: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/NONE/NoPadding");
        // ruleid: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/NONE/NoPadding", "BC");
        // ruleid: gitlab-android-rsa-nopadding
        Cipher.getInstance("RSA/ECB/NoPadding");
    }

    public void dataflowCipherWeak() throws Exception {
        String cipher1 = null;
        // ok: gitlab-android-rsa-nopadding
        Cipher.getInstance(cipher1);

        String cipher2 = "RSA/NONE/NoPadding";
        // ruleid: gitlab-android-rsa-nopadding
        Cipher.getInstance(cipher2);

        String cipher3 = "RSA/ECB/NoPadding";
        // ruleid: gitlab-android-rsa-nopadding
        Cipher.getInstance(cipher3);
    }
}
