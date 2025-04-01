package com.example.java7_4.service.impl;


import com.example.java7_4.entity.CloudCard;
import com.example.java7_4.entity.CloudType;
import com.example.java7_4.mapper.CloudCardMapper;
import com.example.java7_4.mapper.CloudTypeMapper;
import com.example.java7_4.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CloudCardService {
    @Autowired
    private CloudCardMapper cloudCardMapper;

    public CloudCard getCloudCardByCloudName(String cloudName) {
        return cloudCardMapper.getCloudCardByCloudName(cloudName);
    }
}