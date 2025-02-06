package com.example.java7_4.service;

import com.example.java7_4.entity.User;
import com.example.java7_4.result.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

public interface UserService {

    User getUserById(Long userId);
    List<User> getAllUsers();
    User login(String userName, String userPassword);
    User getUserByUserName(String userName);
    int save(User user1);

    PageResult getUsersPage_pageHelper(int page, int pageSize) ;
}
