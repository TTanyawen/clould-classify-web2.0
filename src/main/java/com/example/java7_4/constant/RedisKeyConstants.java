package com.example.java7_4.constant;

public final class RedisKeyConstants {
    // 防止实例化
    private RedisKeyConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // 数据库相关常量
    public static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "password";


}
