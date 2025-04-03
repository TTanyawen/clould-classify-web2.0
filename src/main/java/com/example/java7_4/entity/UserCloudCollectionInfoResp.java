package com.example.java7_4.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserCloudCollectionInfoResp {
    private Long typeId;
    private String typeName;
    private String typeNameEn;
    private String typeInfo;
    private Long typePoint;

    private Long collectNum;
    private Boolean isCollected;
}
