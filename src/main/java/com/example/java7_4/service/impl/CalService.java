package com.example.java7_4.service.impl;


import com.example.java7_4.entity.CalEntity;
import com.example.java7_4.entity.CloudCard;
import com.example.java7_4.entity.UserCardCollect;
import com.example.java7_4.entity.UserCardCollectDetail;
import com.example.java7_4.mapper.CalMapper;
import com.example.java7_4.mapper.CloudCardMapper;
import com.example.java7_4.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private CalMapper calMapper;

    public void calTodayData() {
        //如果存在先删掉
        CalEntity calEntityExist = calMapper.selectAllByCalTime(LocalDateTime.now());
        if(null != calEntityExist){
            calMapper.deleteById(calEntityExist.getId());
        }

        CalEntity calEntity = postMapper.calTodayData();
        calMapper.save(calEntity);
    }




}