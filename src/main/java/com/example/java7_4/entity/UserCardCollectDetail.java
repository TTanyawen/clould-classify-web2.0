package com.example.java7_4.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//new:serial
//implements Serializable {
//private static final long serialVersionUID = 1L;
@Data
@TableName("tb_user_card_collect_detail")
public class UserCardCollectDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long typeId;
    private LocalDateTime collectTime;
    private String classifyImg;

    // Getters and setters by Lombok @Data
}
