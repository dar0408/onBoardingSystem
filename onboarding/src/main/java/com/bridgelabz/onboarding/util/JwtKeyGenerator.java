package com.bridgelabz.onboarding.util;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;

import java.security.Key;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Encoders.BASE64.encode(key.getEncoded());

        System.out.println("==== COPY THIS ENTIRE LINE INTO application.properties ====");
        System.out.println("jwt.secret=" + base64Key);
        System.out.println("jwt.expiration=3600000");
    }
}
