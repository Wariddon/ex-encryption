import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class jittagornp_example_JWTExample {


    public static void main(String[] args) throws NoSuchAlgorithmException {

        //Generate Keypair
        final KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048);

        final KeyPair keyPair = keyPairGenerator.genKeyPair();

        final RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        final RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //======================================================================

        final Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1L);
        claims.put("name", "จิตกร พิทักษ์เมธากุล");
        claims.put("authorities", Arrays.asList("MANAGE_USER"));

        final String token = sign(claims, Algorithm.RSA256(null, privateKey), 30);
        final Map<String, Object> output = verify(token, Algorithm.RSA256(publicKey, null));

        System.out.println("claims => " + claims);
        System.out.println("token => " + token);
        System.out.println("output => " + output);
    }

    public static String sign(final Map<String, Object> claims, final Algorithm algorithm, final int expiresMinutes) {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime expires = now.plusMinutes(expiresMinutes);
        return JWT.create()
                .withIssuedAt(toDate(now))
                .withExpiresAt(toDate(expires))
                .withClaim("user", claims)
                .sign(algorithm);
    }

    public static Map<String, Object> verify(final String token, final Algorithm algorithm) {
        return JWT.require(algorithm)
                .build()
                .verify(token)
                .getClaims()
                .get("user")
                .asMap();
    }

    private static Date toDate(final LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
