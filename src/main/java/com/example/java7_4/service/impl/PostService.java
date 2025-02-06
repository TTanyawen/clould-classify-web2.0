package com.example.java7_4.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java7_4.entity.Post;

import com.example.java7_4.entity.PostDTO;
import com.example.java7_4.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public List<Post> getPosts() {
        return postMapper.selectAll();
    }

    public List<PostDTO> getMePosts(Long userId) {
        return postMapper.selectMePostsWithUserAvatar(userId);
    }


    public List<PostDTO> getPostsWithUserAvatar() {
        return postMapper.selectPostsWithUserAvatar();
    }
    public Long incrementPostLike(Long postId) {
        // 查询当前的post
        Post post = postMapper.selectById(postId);
        System.out.println("incrementPostLike1");
        if (post != null) {
            // 增加点赞数
            post.setPostLike(post.getPostLike() + 1);
            System.out.println("incrementPostLike2");
            postMapper.updateById(post.getPostId(),post.getPostLike());
            System.out.println("incrementPostLike3");
            return post.getPostLike();
        }
        throw new RuntimeException("Post not found");
    }
}


