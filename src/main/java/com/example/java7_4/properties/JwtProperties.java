package com.example.java7_4.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.jwt")
@Data
public class JwtProperties {
    /**
     * 用户生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;
}
