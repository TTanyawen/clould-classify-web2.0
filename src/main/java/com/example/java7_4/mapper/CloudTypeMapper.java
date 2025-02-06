package com.example.java7_4.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java7_4.entity.CloudType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CloudTypeMapper {
    CloudType selectById(Long typeId);

    List<CloudType> selectAll();
}
