package com.example.java7_4.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//new:serial
//implements Serializable {
//private static final long serialVersionUID = 1L;
@Data
@TableName("tb_cloud_card")
public class CloudCard implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long typeId;
    private String typeName;
    private String typeNameEn;
    private String typeInfo;
    private Long typePoint;
    // Getters and setters by Lombok @Data
}
