package com.example.java7_4.mapper;


import com.example.java7_4.annotation.AutoFill;
import com.example.java7_4.entity.MeEditEntity;
import com.example.java7_4.entity.MeEditQueryRepDTO;
import com.example.java7_4.entity.MeEditUpdateReqDTO;
import com.example.java7_4.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeEditMapper {
    MeEditEntity selectById(Long userId);

    @AutoFill(value = OperationType.UPDATE)
    int updateMeInfoById(MeEditEntity meEditEntity);
}
