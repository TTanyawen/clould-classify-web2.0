package com.example.java7_4.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostCollectionRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long postId;
    private Long userId;
    private String postImgPath;
    private String postText;
    private Long postLike;
    private String userProfilePath; // 额外的字段，用于存储用户头像
    private String userName;

    private List<String> postImgPaths;//解析后的
    private LocalDateTime collectTime;
    private LocalDateTime createTime;

    private Long postCollect;
    // Getters 和 Setters
}

