// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Signature;

public class WeakMessageDigest {

    public static void weakDigestMoreSig() throws NoSuchProviderException, NoSuchAlgorithmException {
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD5", "SUN");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD4", "SUN");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD2", "SUN");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD5");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD4");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD2");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD5", new ExampleProvider());
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD4", new ExampleProvider());
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("MD2", new ExampleProvider());
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA", "SUN");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA", new ExampleProvider());
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA1", "SUN");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA1", new ExampleProvider());
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA-1", "SUN");
        // ruleid: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA-1", new ExampleProvider());
        // ok: gitlab-android-weak-message-digest
        MessageDigest.getInstance("sha-384","SUN"); //OK!
        // ok: gitlab-android-weak-message-digest
        MessageDigest.getInstance("SHA-512", "SUN"); //OK!

        // ruleid: gitlab-android-weak-message-digest
        Signature.getInstance("MD5withRSA");
        // ruleid: gitlab-android-weak-message-digest
        Signature.getInstance("SHA1withRSA", new ExampleProvider());
        // ok: gitlab-android-weak-message-digest
        Signature.getInstance("SHA256withRSA"); //OK
        Signature.getInstance("uncommon name", ""); //OK
    }

    static class ExampleProvider extends Provider {
        protected ExampleProvider() {
            super("example", 1.0, "");
        }
    }
}
