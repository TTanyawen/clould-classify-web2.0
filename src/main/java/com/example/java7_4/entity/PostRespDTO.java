package com.example.java7_4.entity;

import com.example.java7_4.entity.Enum.TagEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long postId;
    private Long userId;
    private List<String> postImgPaths;
    private String postText;
    private Long postLike;
    private String userProfilePath; // 额外的字段，用于存储用户头像
    private String userName;
    private String createTime;
    // Getters 和 Setters

    private Long postCollect;

    private Boolean isLiked;
    private Boolean isCollected;
    private List<String> postTags;
}

