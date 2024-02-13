import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class jittagornp_example_javaKeyPair  {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

        /**
         ref
            - https://www.jittagornp.me/blog/what-is-public-key-private-key/?series=pki
         * */

        //Generate Keypair
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048);

        final KeyPair keyPair = keyPairGenerator.genKeyPair();

        final PublicKey publicKey = keyPair.getPublic();
        final PrivateKey privateKey = keyPair.getPrivate();

        final String base64PublicKey = convertToBase64PublicKey(publicKey.getEncoded());
        final String base64PrivateKey = convertToBase64PrivateKey(privateKey.getEncoded());

        System.out.println("Generating Keypair");
        System.out.println("");
        System.out.println("publicKey");
        System.out.println("publicKey.algorithm => " + publicKey.getAlgorithm());
        System.out.println("publicKey.format => " + publicKey.getFormat());
        System.out.println("publicKey.encoded.length => " + publicKey.getEncoded().length);
        System.out.println(base64PublicKey);
        System.out.println("======================================================");

        System.out.println("privateKey");
        System.out.println("privateKey.algorithm => " + privateKey.getAlgorithm());
        System.out.println("privateKey.format => " + privateKey.getFormat());
        System.out.println("privateKey.encoded.length => " + privateKey.getEncoded().length);
        System.out.println(base64PrivateKey);
        System.out.println("======================================================");

        System.out.println("Reading Keypair");
        System.out.println("");

        final PublicKey aPublicKey = convertToPublicKey(base64PublicKey);
        final PrivateKey aPrivateKey = convertToPrivateKey(base64PrivateKey);

        //Do something...
    }

    private static String convertToBase64PublicKey(final byte[] bytes) {
        return new StringBuilder()
                .append("-----BEGIN PUBLIC KEY-----")
                .append("\n")
                .append(Base64.getEncoder().encodeToString(bytes))
                .append("\n")
                .append("-----END PUBLIC KEY-----")
                .toString();
    }

    private static String convertToBase64PrivateKey(final byte[] bytes) {
        return new StringBuilder()
                .append("-----BEGIN PRIVATE KEY-----")
                .append("\n")
                .append(Base64.getEncoder().encodeToString(bytes))
                .append("\n")
                .append("-----END PRIVATE KEY-----")
                .toString();
    }

    private static String removeKeyBlock(final String key){
        return key
                .replaceAll("-----BEGIN.*KEY-----", "")
                .replaceAll("-----END.*KEY-----", "")
                .replaceAll("\\s+", "");
    }

    private static PublicKey convertToPublicKey(final String base64PublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final String realKey = removeKeyBlock(base64PublicKey);
        final byte[] keyBytes = Base64.getDecoder().decode(realKey);
        final X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    private static PrivateKey convertToPrivateKey(final String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final String realKey = removeKeyBlock(base64PrivateKey);
        final byte[] keyBytes = Base64.getDecoder().decode(realKey);
        final PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

}
