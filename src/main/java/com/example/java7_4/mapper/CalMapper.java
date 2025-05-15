package com.example.java7_4.mapper;


import com.example.java7_4.entity.CalEntity;
import com.example.java7_4.entity.CloudType;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CalMapper {
    void save(CalEntity calEntity);

    void  deleteById(Long id);

    CalEntity selectAllByCalTime(LocalDateTime calTime);
}
