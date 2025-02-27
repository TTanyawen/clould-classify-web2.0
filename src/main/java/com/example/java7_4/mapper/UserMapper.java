package com.example.java7_4.mapper;


import com.example.java7_4.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(Long userId);

    int updateById(User user);

    List<User> selectAll();

    User selectByName(String userName);
    int insert(User user);
    Page<User> selectMyPage();
}
