//package com.example.java7_4.config;
//
//
//import com.example.java7_4.properties.JwtProperties;
//import com.example.java7_4.util.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class JwtConfig {
//
//    @Autowired
//    private JwtProperties jwtProperties;
//
//
//
//    @Bean
//    public JwtUtils jwtUtils() {
//        return new JwtUtils(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl());
//    }
//}
//
