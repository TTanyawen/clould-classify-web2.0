package com.example.java7_4.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeEditUpdateReqDTO implements Serializable {
    private Long userId;
    private String userName;
    private String userPassword;
    private String userProfilePath;
}
