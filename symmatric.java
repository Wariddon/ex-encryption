import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class symmatric {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        /*
        Introduction
        Symmetric encryption, also known as secret-key encryption,
        is a type of encryption where the same key is used for encryption and decryption.
        This encryption method is fast and efficient, making it suitable for encrypting large amounts of data.
        The most commonly used symmetric encryption algorithm is the Advanced Encryption Standard (AES).

        Java provides strong support for symmetric encryption with the javax.crypto package, which includes classes such as SecretKey,
        Cipher, and KeyGenerator.

        Symmetric Encryption in Java
        Java's Cipher class in the javax.crypto package provides the functionality of a cryptographic cipher for encryption and decryption.
        It forms the core of the Java Cryptographic Extension (JCE) framework.

        In Java, the Cipher class provides the functionality for symmetric encryption,
        while the KeyGenerator class is used to generate secret keys for symmetric encryption.

        ref
         - https://www.tutorialspoint.com/symmetric-encryption-cryptography-in-java
         - https://www.ert.co.th/symmetric-encryption-vs-asymmetric-encryption/
         - https://www.jittagornp.me/blog/public-key-infrastructure/?series=pki
        **/


        // Generate key
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        // Original message
        String originalMessage = "Hello, world!";

        // Create Cipher instance and initialize it to ENCRYPT_MODE
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Encrypt the message
        byte[] encryptedMessage = cipher.doFinal(originalMessage.getBytes(StandardCharsets.UTF_8));

        // Convert the encrypted message to Base64 encoded string
        String encodedMessage = Base64.getEncoder().encodeToString(encryptedMessage);

        System.out.println("Original Message: " + originalMessage);
        System.out.println("Encrypted Message: " + encodedMessage);

        // Reinitialize the cipher to DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the message
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encodedMessage));

        System.out.println("Decrypted Message: " + new String(decryptedMessage, StandardCharsets.UTF_8));

    }

}
