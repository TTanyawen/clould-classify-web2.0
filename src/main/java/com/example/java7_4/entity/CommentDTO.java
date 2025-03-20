package com.example.java7_4.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String commentId;
    private String userId;
    private String postId;
    private String commentText;
    private Integer commentLike;
    private String userName;

    private LocalDateTime createTime;
}
