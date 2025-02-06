package com.example.java7_4.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long postId;
    private Long userId;
    private String postImgPath;
    private String postText;
    private Long postLike;
    private String userProfilePath; // 额外的字段，用于存储用户头像
    private String userName;
    // Getters 和 Setters
}

