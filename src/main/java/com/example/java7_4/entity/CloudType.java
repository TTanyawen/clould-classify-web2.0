package com.example.java7_4.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

//new:serial
//implements Serializable {
//private static final long serialVersionUID = 1L;
@Data
@TableName("tb_cloudtype")
public class CloudType implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long typeId;
    private String typeName;
    private String typeNameEn;
    private String typeInfo;
    private Long imgMax;
    // Getters and setters by Lombok @Data
}
