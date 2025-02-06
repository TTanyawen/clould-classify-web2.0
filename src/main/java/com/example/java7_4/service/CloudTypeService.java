package com.example.java7_4.service;




import com.example.java7_4.entity.CloudType;

import com.example.java7_4.mapper.CloudTypeMapper;
import com.example.java7_4.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CloudTypeService {
    CloudType getCloudTypeById(Long typeId);
    List<CloudType> getCloudTypes() ;

}
