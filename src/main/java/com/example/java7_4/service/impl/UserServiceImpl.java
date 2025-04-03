package com.example.java7_4.service.impl;


import com.example.java7_4.entity.PostCollectionRespDTO;
import com.example.java7_4.entity.PostDTO;
import com.example.java7_4.entity.PostRespDTO;
import com.example.java7_4.entity.User;
import com.example.java7_4.exception.FailLoginException;
import com.example.java7_4.exception.FailRegisterException;
import com.example.java7_4.mapper.UserMapper;
import com.example.java7_4.result.PageResult;
import com.example.java7_4.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<PostCollectionRespDTO> getCollection(Long userId) {
        List<PostCollectionRespDTO> postCollectionRespDTOS=userMapper.getCollection(userId);
//        postCollectionRespDTOS.get()
        for(PostCollectionRespDTO postCollectionRespDTO:postCollectionRespDTOS){
            //解析postImgPath
            String[] paths = postCollectionRespDTO.getPostImgPath().split("@_@");
            List<String> pathList = new ArrayList<>();
            for (String path : paths) {
                if (!path.isEmpty()) {
                    pathList.add(path);
                }
            }
            postCollectionRespDTO.setPostImgPaths(pathList);
        }
        return postCollectionRespDTOS;
    }

    public User getUserById(Long userId) {
        return userMapper.selectById(userId);
    }

    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    public User getUserByUserName(String userName) {
        return userMapper.selectByName(userName);
    }

    public User login(String userName, String userPassword){
        User user = userMapper.selectByName(userName);
        if(user != null && user.getUserPassword().equals(userPassword)){

            return user;
        }else{
            System.out.println("login错误");
            throw new FailLoginException("无此用户和密码的组合");
//            return null;
        }
    }


    public int save(User user1) {
        int count=0;
        try{
            count=userMapper.insert(user1);
        }catch(Exception e){
            e.printStackTrace();
            throw new FailRegisterException("向数据库保存数据失败了！");
        }



        return count;
    }


    @Override
    public void updateById(User user) {
        log.info("logTag::updateById-userid:{}-username-{}-userpath-{}",user.getUserId(),user.getUserName(),user.getUserProfilePath());
        userMapper.updateById(user);
    }

    public PageResult getUsersPage_pageHelper(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        Page<User> pageInfo =  userMapper.selectMyPage();
        PageResult pageResult=new PageResult(pageInfo.getTotal(),pageInfo.getResult());
        return pageResult;
    }
}
