package com.bridgelabz.onboarding.util;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;

import java.security.Key;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        // 1. Generate a new 512-bit key suitable for HS512
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        // 2. Base64-encode the raw bytes
        String base64Key = Encoders.BASE64.encode(key.getEncoded());

        // 3. Print it in a form you can copy/paste
        System.out.println("==== COPY THIS ENTIRE LINE INTO application.properties ====");
        System.out.println("jwt.secret=" + base64Key);
        System.out.println("jwt.expiration=3600000");
    }
}
