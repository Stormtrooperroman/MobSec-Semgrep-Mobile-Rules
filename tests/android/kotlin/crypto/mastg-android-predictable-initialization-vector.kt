import java.security.SecureRandom
import java.util.Random
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

fun staticIvFromString(strKey: String, plainText: String): ByteArray {
    // ruleid:mastg-android-predictable-initialization-vector
    val bytesIV = "foo".toByteArray(Charsets.UTF_8)

    val iv = IvParameterSpec(bytesIV)
    val skeySpec = SecretKeySpec(strKey.toByteArray(Charsets.UTF_8), "AES")

    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
    return cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
}

fun staticIvFromArray(strKey: String, plainText: String): ByteArray {
    // ruleid:mastg-android-predictable-initialization-vector
    val bytesIV1 = byteArrayOf(
        0x01, 0x02, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
    )

    val iv = IvParameterSpec(bytesIV1)
    val skeySpec = SecretKeySpec(strKey.toByteArray(Charsets.UTF_8), "AES")

    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
    return cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
}

class CbcClass1 {

    private val random = Random()

    fun applyCBC(strKey: String, plainText: String): ByteArray {
        // ruleid:mastg-android-predictable-initialization-vector
        val bytesIV = ByteArray(16)
        random.nextBytes(bytesIV)

        val iv = IvParameterSpec(bytesIV)
        val skeySpec = SecretKeySpec(strKey.toByteArray(Charsets.UTF_8), "AES")

        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
        return bytesIV + cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
    }
}

class CbcClass2 {

    private val random = SecureRandom()

    fun applyCBC(strKey: String, plainText: String): ByteArray {
        // ok:mastg-android-predictable-initialization-vector
        val bytesIV = ByteArray(16)
        random.nextBytes(bytesIV)

        val iv = IvParameterSpec(bytesIV)
        val skeySpec = SecretKeySpec(strKey.toByteArray(Charsets.UTF_8), "AES")

        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv)
        return bytesIV + cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
    }
}