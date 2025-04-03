package com.example.java7_4.service.impl;


import com.example.java7_4.entity.CloudCard;
import com.example.java7_4.entity.CloudType;
import com.example.java7_4.entity.UserCardCollect;
import com.example.java7_4.entity.UserCardCollectDetail;
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

    public List<UserCardCollect> getUserCardCollectsByUserId(Long userId) {
        return cloudCardMapper.getUserCardCollectsByUserId(userId);
    }

    public List<UserCardCollectDetail> getUserCardCollectDetailsByUserId(Long userId) {
        return cloudCardMapper.getUserCardCollectDetailsByUserId(userId);
    }

    public UserCardCollect getUserCardCollectsByUserIdAndTypeId(Long userId,Long typeId) {
        return cloudCardMapper.getUserCardCollectsByUserIdAndTypeId(userId,typeId);
    }

    public void saveUserCardCollect(UserCardCollect userCardCollect) {
        cloudCardMapper.saveUserCardCollect(userCardCollect);
    }

    public void saveUserCardCollectDetail(UserCardCollectDetail userCardCollectDetail) {
        cloudCardMapper.saveUserCardCollectDetail(userCardCollectDetail);

    }

    public void updateUserCardCollectByUserIdAndTypeId(UserCardCollect userCardCollect) {
        cloudCardMapper.updateUserCardCollectByUserIdAndTypeId(userCardCollect);

    }
}