package com.example.java7_4;

import io.jsonwebtoken.*;
import org.junit.Test;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;

import java.util.Date;
import java.util.UUID;

public class jwtTest {
    private long time=1000*60*60*24;
    private String signature="admin";
    @Test
    public void jwt(){
        JwtBuilder jwtBuilder= Jwts.builder();
        String jwtToken=jwtBuilder
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .claim("username","tyw")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.println(jwtToken);
    }

    @Test
    public void parse(){
        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InR5dyIsInJvbGUiOiJhZG1pbiIsInN1YiI6ImFkbWluLXRlc3QiLCJleHAiOjE3MzY4NTc1NzAsImp0aSI6IjgzYTEwNmFhLWRiY2QtNGVkMy05Y2UyLWQ4YzE1Y2QzNjAxNiJ9.vITpD9eBthGnqJ1FZGiyFEVr8a0hMv-RUl2HatBwymo";
        JwtParser jwtParser=Jwts.parser();
        Jws<Claims> claimsJws=jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims=claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());
    }
}
