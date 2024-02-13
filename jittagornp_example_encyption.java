import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class jittagornp_example_encyption {

    public static void main(String[] args) throws Exception {

        //Generate Keypair
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048);

        final KeyPair keyPair = keyPairGenerator.genKeyPair();

        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();
        //======================================================================

        final String message = "Hello World";

        final byte[] encryptedBytes = encrypt(message.getBytes(), publicKey);
        final String encrypted = new String(encryptedBytes);
        final String encryptedBase64 = Base64.getEncoder().withoutPadding().encodeToString(encryptedBytes);

        final byte[] decryptedDecodeBytes = Base64.getDecoder().decode(encryptedBase64);
        final byte[] decryptedBytes = decrypt(decryptedDecodeBytes, privateKey);
        final String decrypted = new String(decryptedBytes);

        System.out.println("message => " + message);
        System.out.println("");
        System.out.println("Encrypt");
        System.out.println("encryptedBytes.length => " + encryptedBytes.length);
        System.out.println("encrypted => " + encrypted);
        System.out.println("encryptedBase64 => " + encryptedBase64);
        System.out.println("");
        System.out.println("Decrypt");
        System.out.println("decryptedDecodeBytes.length => " + decryptedDecodeBytes.length);
        System.out.println("decryptedBytes.length => " + decryptedBytes.length);
        System.out.println("decrypted => " + decrypted);

    }

    public static byte[] encrypt(final byte[] data, final PublicKey publicKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException {
        final Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(final byte[] data, final PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        final Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
}
