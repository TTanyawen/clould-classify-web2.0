package com.example.java7_4.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tb_post_collection")
public class PostCollection {
    private Long postId;
    private Long userId;
    private LocalDateTime collectTime;
}
