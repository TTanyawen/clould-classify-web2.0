package com.example.java7_4.mapper;


import com.example.java7_4.entity.CloudCard;
import com.example.java7_4.entity.CloudType;
import com.example.java7_4.entity.UserCardCollect;
import com.example.java7_4.entity.UserCardCollectDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CloudCardMapper {
    CloudType selectById(Long typeId);

    List<CloudType> selectAll();

    CloudCard getCloudCardByCloudName(String cloudName);

    List<UserCardCollect> getUserCardCollectsByUserId(Long userId);

    List<UserCardCollectDetail> getUserCardCollectDetailsByUserId(Long userId);

    UserCardCollect getUserCardCollectsByUserIdAndTypeId(Long userId, Long typeId);

    void saveUserCardCollect(UserCardCollect userCardCollect);

    void saveUserCardCollectDetail(UserCardCollectDetail userCardCollectDetail);

    void updateUserCardCollectByUserIdAndTypeId(UserCardCollect userCardCollect);
}
