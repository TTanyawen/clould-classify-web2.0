package com.example.java7_4.interceptor;

import com.example.java7_4.constant.JwtClaimsConstant;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.properties.JwtProperties;
import com.example.java7_4.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        System.out.println(handler.getClass());

        String uri = request.getRequestURI();
        System.out.println(uri);
        System.out.println("URI: " + request.getRequestURI());
        System.out.println("Authorization: " + request.getHeader("Authorization"));
        System.out.println("Content-Type:"+request.getHeader("Content-Type"));

        String requestType = request.getHeader("X-Request-Type");
        System.out.println("X-Request-Type:"+requestType);

        System.out.println("@@@@1@@@@");
        if ("/forum2".equals(uri)&& !"document".equals(requestType)) {
            System.out.print("forum绕过了拦截器");
            return true;
        }
        System.out.println("@@@@2@@@@");
        // 判断是否是跳转请求（例如，Content-Type 不为 'application/json'）
//        if (request.getHeader("Content-Type") == null||!request.getHeader("Content-Type").equals("application/json")) {
//            // 如果是跳转请求或无Content-Type，则跳过拦截器
//            return true;
//        }
        // 静态资源放行
        if (uri.startsWith("/js/") || uri.startsWith("/css/") || uri.startsWith("/images/")||uri.startsWith("/static/")) {
            System.out.println("静态资源，直接放行");
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            System.out.println("当前拦截到的不是动态方法，直接放行");
            return true;
        }


        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token == null || token.isEmpty()) {
            System.out.println("token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try {
            System.out.println("secretKey");
            String secretKey=jwtProperties.getUserSecretKey();
            System.out.println(secretKey);



            Claims claims=JwtUtil.parseJWT(token.replace("Bearer ", ""), jwtProperties.getUserSecretKey());
            Long userId=Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            BaseContext.setCurrentId(userId);

            System.out.println("通过检查");

            return true;
        } catch (Exception e) {
            System.out.println("没有通过检查");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}