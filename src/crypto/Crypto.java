package crypto;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Janusz on 2017-05-28.
 */
public class Crypto {

        private static final String ALGO = "DESede";
        private static DESedeKeySpec keySpec;
        private static SecretKeyFactory keyFactory;
        private static SecretKey key;
        private static String plainKey = "1234567890azertyuiopqsdf";

        public Crypto() {
            try {
                setComponents();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void setComponents() throws Exception {
            byte[] keyBytes = plainKey.getBytes("ASCII");
            keySpec = new DESedeKeySpec(keyBytes);
            keyFactory = SecretKeyFactory.getInstance(ALGO);
            key = keyFactory.generateSecret(keySpec);
        }

        // Used for encrypting names
        /*public byte[] encrypt(String plainText) throws Exception {

            byte[] byteText = plainText.getBytes("ASCII");
            Cipher cipher = Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(byteText);
            return encrypted;
        }*/

        public String decrypt(byte[] encryptedText) {
            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance(ALGO);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            }
            try {
                cipher.init(Cipher.DECRYPT_MODE, key);
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            }
            byte[] decrypted = new byte[0];
            try {
                decrypted = cipher.doFinal(encryptedText);
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }
            String plainDecrypted = null;
            try {
                plainDecrypted = new String(decrypted, "ASCII");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return plainDecrypted;
        }
}
