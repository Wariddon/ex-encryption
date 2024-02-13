import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class asymmatric {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {

        /*
        Introduction

        In this article, we will review how to perform the encryption and decryption of a message using asymmetric keys with Java.
        Asymmetric-key cryptography refers to the process of using two separate keys when encrypting and decrypting a message.
        We call these two keys the PUBLIC KEY and the PRIVATE KEY. Asymmetric cryptography is also referred to as “public-key cryptography.”

        A public key is a key that is provided to the outside world. Anyone who wants to send you a secret message will encrypt the message
        using your public key and then send the ciphertext to you.

        A private key is a key that you do not share with anyone. The private and public keys are mathematically related. So if you receive a
        ciphertext that has been encrypted using your public key, only your private key has the ability to decrypt the ciphertext. You cannot
        both encrypt and decrypt the message with the public key, and the same goes for the private key.

        For this demo, we will be using an RSA encryption scheme to perform the encryption and decryption operations. For a further explanation
        of RSA, click here.

        ref
         - https://gregorycernera.medium.com/encrypting-and-decrypting-a-message-using-asymmetric-keys-with-java-explained-step-by-step-with-54fced36118a
         - https://www.ert.co.th/symmetric-encryption-vs-asymmetric-encryption/
         - https://www.jittagornp.me/blog/public-key-infrastructure/?series=pki
        **/



        // Step 1: Come up with a message we want to encrypt
        byte[] message = "Hello, World!".getBytes();

        // Step 2: Create a KeyGenerator object
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        // Step 3: Initialize the KeyGenerator with a certain keysize
        keyPairGenerator.initialize(512);

        // Step 4: Generate the key pairs
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Step 5: Extract the keys
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Step 6: Create a Cipher object
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");

        // Step 7: Initialize the Cipher object
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Step 8: Give the Cipher our message
        cipher.update(message);

        // Step 9: Encrypt the message
        byte[] ciphertext = cipher.doFinal();

        // Step 10: Print the ciphertext
        System.out.println("message: " + new String(message, "UTF-8"));
        System.out.println("ciphertext: " + new String(ciphertext, "UTF-8"));

        // Step 11: Change the Cipher object's mode
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        // Step 12: Give the Cipher objectour ciphertext
        cipher.update(ciphertext);

        // Step 13: Decrypt the ciphertext
        byte[] decrypted = cipher.doFinal();
        System.out.println("decrypted: " + new String(decrypted, "UTF-8"));
    }

}
