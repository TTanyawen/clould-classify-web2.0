package com.example.java7_4.constant;

public final class RedisKeyConstants {
    // 防止实例化
    private RedisKeyConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // key相关常量
    public static final String CLOUD_TYPES = "types";
    public static final String CLOUD_POSTS = "posts";
    public static final String POSTS = "posts";
    public static final String COMMENTS = "comments";

    public static final String CLOUD_TYPE = "type:";
    public static final String LIKE_POST = "like:post:";
    public static final String LIKE_COMMENT = "like:comment:";



}
