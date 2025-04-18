package com.example.java7_4.entity;

//import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String userName;
    private String userPassword;
    private String userProfilePath;
    private Long userPoints;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}
