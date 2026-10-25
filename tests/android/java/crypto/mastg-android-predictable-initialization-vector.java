// ruleid:mastg-android-predictable-initialization-vector
byte[] bytesIV = "foo".getBytes("UTF-8");

    IvParameterSpec iv = new IvParameterSpec(bytesIV);
    SecretKeySpec skeySpec = new SecretKeySpec(strKey.getBytes("UTF-8"), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv); 
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));



// ruleid:mastg-android-predictable-initialization-vector
byte[] bytesIV1 ={
0x01,0x02,0x00,0x00,0x00,0x00,0x00,0x00
};

    IvParameterSpec iv = new IvParameterSpec(bytesIV1);
    SecretKeySpec skeySpec = new SecretKeySpec(strKey.getBytes("UTF-8"), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv); 
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));

public class CbcClass1 {

  Random random = new Random();

  public String applyCBC(String strKey, String plainText) {
    // ruleid:mastg-android-predictable-initialization-vector
    byte[] bytesIV = new byte[16];
    random.nextBytes(bytesIV);
    IvParameterSpec iv = new IvParameterSpec(bytesIV);
    SecretKeySpec skeySpec = new SecretKeySpec(strKey.getBytes("UTF-8"), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
    return DatatypeConverter.printBase64Binary(bytesIV)
            + ";" + DatatypeConverter.printBase64Binary(encryptedBytes);
  }
}

public class CbcClass2 {

  SecureRandom random = new SecureRandom();

  public String applyCBC(String strKey, String plainText) {
    byte[] bytesIV = new byte[16];
    random.nextBytes(bytesIV);

    // ok:mastg-android-predictable-initialization-vector
    IvParameterSpec iv = new IvParameterSpec(bytesIV);
    SecretKeySpec skeySpec = new SecretKeySpec(strKey.getBytes("UTF-8"), "AES");

    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
    byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
    return DatatypeConverter.printBase64Binary(bytesIV)
            + ";" + DatatypeConverter.printBase64Binary(encryptedBytes);
  }
}