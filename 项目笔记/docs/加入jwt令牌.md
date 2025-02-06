# 总结
- 拦截的一个顺序
	- 将网页加载和网页数据请求分开来
	- 网页间的跳转就专门设置controller来做
	- 1. 进入网页
	- 2. 判断有无jwt，无则跳转登录页
	- 3. 发送获取数据的请求
	- 4. 后端校验jwt是否正确，正确则继续执行controller方法，用统一的Result封装controller执行信息以及数据，返回给前端；jwt校验失败则不执行controller方法，返回一个401错误
	- 5. 拿到数据就用javascript对html做数据动态填充
# jwt
- 三部分
	- 头
		- typ
			- 种类
		- alg
			- 加密算法
	- 载荷
		- 放一些实际的东西，比如说用户名密码
	- 签名
		- 对以上两者的加密后的


# 简单使用(生成jwt令牌和解析jwt令牌过程)
```
<dependency>  
    <groupId>io.jsonwebtoken</groupId>  
    <artifactId>jjwt</artifactId>  
    <version>0.9.1</version>  
</dependency>  
<dependency>  
    <groupId>javax.xml.bind</groupId>  
    <artifactId>jaxb-api</artifactId>  
    <version>2.3.0</version>  
</dependency>  
  
<dependency>  
    <groupId>com.sun.xml.bind</groupId>  
    <artifactId>jaxb-impl</artifactId>  
    <version>2.3.0</version>  
</dependency>  
<dependency>  
    <groupId>com.sun.xml.bind</groupId>  
    <artifactId>jaxb-core</artifactId>  
    <version>2.3.0</version>  
</dependency>
```



```java
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
```


# 融入项目
- claims
	- userId
- 签名
	- 密钥


- 登录的时候
	- 构造claim
	- 生成token
	- 使用一个UserLoginVO把用户id，token装入
- 设置一个拦截器
	- JwtTokenUserInterceptor
- 在配置类将拦截器注册