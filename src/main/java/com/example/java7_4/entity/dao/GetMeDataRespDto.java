package com.example.java7_4.entity.dao;

import com.example.java7_4.entity.PostRespDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMeDataRespDto {
    private List<PostRespDTO> postRespDTOs;
    private Long userId;
    private String userProfilePath;
    private String userName;
    private Long userPoints;
}
