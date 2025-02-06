package com.example.java7_4.service.impl;


import com.example.java7_4.entity.CloudType;

import com.example.java7_4.mapper.CloudTypeMapper;
import com.example.java7_4.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudTypeService {
    @Autowired
    private CloudTypeMapper cloudTypeMapper;




    @Autowired
    private UserMapper userMapper;
    public CloudType getCloudTypeById(Long typeId) {
        return cloudTypeMapper.selectById(typeId);
    }

    public List<CloudType> getCloudTypes() {
        return cloudTypeMapper.selectAll();
    }









}