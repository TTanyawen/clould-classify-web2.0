package com.example.java7_4.service;

import com.example.java7_4.entity.MeEditQueryRepDTO;
import com.example.java7_4.entity.MeEditUpdateReqDTO;

public interface MeEditService {

    MeEditQueryRepDTO getMeInfoById(Long userId);

    int updateMeInfoById(MeEditUpdateReqDTO reqDto);
}
