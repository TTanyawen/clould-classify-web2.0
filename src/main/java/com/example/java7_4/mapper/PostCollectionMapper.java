package com.example.java7_4.mapper;


import com.example.java7_4.annotation.AutoFill;
import com.example.java7_4.entity.Post;
import com.example.java7_4.entity.PostCollection;
import com.example.java7_4.entity.PostCollectionRespDTO;
import com.example.java7_4.entity.User;
import com.example.java7_4.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostCollectionMapper {
    int save(PostCollection postCollection);

    void delete(PostCollection postCollection);


}
