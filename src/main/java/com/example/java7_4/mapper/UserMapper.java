package com.example.java7_4.mapper;


import com.example.java7_4.annotation.AutoFill;
import com.example.java7_4.entity.User;
import com.example.java7_4.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(Long userId);

    @AutoFill(value = OperationType.UPDATE)
    int updateById(User user);

    List<User> selectAll();

    User selectByName(String userName);

    @AutoFill(value = OperationType.INSERT)
    int insert(User user);

    Page<User> selectMyPage();
}
