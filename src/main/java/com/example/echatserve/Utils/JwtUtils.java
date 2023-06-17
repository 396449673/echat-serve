package com.example.echatserve.Utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Date;

public class JwtUtils {
    public static String createToken(String userId,String ipAddress, String cikiName,String password) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,240);
        Date expiresDate = nowTime.getTime();
        return JWT.create().withAudience(userId)
                .withIssuedAt(new Date())
                .withExpiresAt(expiresDate)
                .withClaim("ipAddress", ipAddress)
                .withClaim("cikiName", cikiName)
                .sign(Algorithm.HMAC256(userId+password));
    }

    public static void verifyToken(String token, String secret) throws TokenUnavailable {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new TokenUnavailable();
        }
    }

    public static String getAudience(String token) throws TokenUnavailable {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new TokenUnavailable();
        }
        return audience;
    }

    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }
}


