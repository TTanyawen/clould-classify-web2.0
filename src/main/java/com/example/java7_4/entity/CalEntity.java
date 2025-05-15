package com.example.java7_4.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("tb_cal")
public class CalEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    private LocalDateTime calTime;
    private Long postNum;
}
