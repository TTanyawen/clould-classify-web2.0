package com.example.java7_4.util;

import com.example.java7_4.properties.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    @Autowired
    private JwtProperties jwtProperties;
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        System.out.print("createJWT-secretKey:"+secretKey);

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(nowMillis + ttlMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey);

        return builder.compact();
    }

    public static Claims parseJWT(String token, String secretKey) {
        System.out.println("~~~parseJWT~~~");
        System.out.println("token:"+token);
        System.out.println("secretKey:"+secretKey);

        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


}