package com.example.java7_4.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserPointsListRespDto {
    private Long userId;
    private String userName;
    private String userProfilePath;
    private Long userPoints;
}
