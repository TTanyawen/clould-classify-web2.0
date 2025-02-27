package com.example.java7_4.service.impl;


import com.example.java7_4.entity.MeEditEntity;
import com.example.java7_4.entity.MeEditQueryRepDTO;
import com.example.java7_4.entity.MeEditUpdateReqDTO;
import com.example.java7_4.mapper.MeEditMapper;
import com.example.java7_4.service.MeEditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeEditImpl implements MeEditService {
    @Autowired
    private MeEditMapper meEditMapper;

    public MeEditQueryRepDTO getMeInfoById(Long userId) {
        MeEditQueryRepDTO meEditQueryRepDTO=new MeEditQueryRepDTO();
        BeanUtils.copyProperties(meEditMapper.selectById(userId),meEditQueryRepDTO);
        return meEditQueryRepDTO;
    }



    @Override
    public int updateMeInfoById(MeEditUpdateReqDTO reqDto) {
        MeEditEntity meEditEntity=new MeEditEntity();
        BeanUtils.copyProperties(reqDto,meEditEntity);
        return meEditMapper.updateMeInfoById(meEditEntity);
    }


}
