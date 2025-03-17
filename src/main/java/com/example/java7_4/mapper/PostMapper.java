package com.example.java7_4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java7_4.entity.Post;
import com.example.java7_4.entity.PostDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface PostMapper {
    Post selectById(Long postId);
    List<Post> selectAll();

    //查找当前用户的所有post，并带有用户信息
    List<PostDTO> selectMePostsWithUserAvatar(Long userId);


    //查找当前所有用户的post，并带有用户信息
    List<PostDTO> selectPostsWithUserAvatar();

    //查找当前所有用户的post，并带有用户信息
    Page<PostDTO> selectPagedPostsWithUserAvatar();
    int updateById(@Param("postId") Long postId,@Param("postLike") Long postLike);

    int save(Post post);

    List<PostDTO> getSearchedPosts(String searchText);
    Page<PostDTO> getPagedSearchedPosts(String searchText);
}