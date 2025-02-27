package com.example.java7_4.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.entity.Post;

import com.example.java7_4.entity.PostDTO;
import com.example.java7_4.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private RedisTemplate redisTemplate;

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
        if (post != null) {
            // 增加/取消点赞
            Long userId=BaseContext.getCurrentId();
            String key= RedisKeyConstants.LIKE_POST +userId+":"+postId;
            // 1. 判断缓存是否有key
            Boolean hasLiked = redisTemplate.hasKey(key);

            if (hasLiked != null && hasLiked) {
                // 1.1 有key->取消点赞
                // Decrement the like count
                post.setPostLike(post.getPostLike() - 1);
                postMapper.updateById(post.getPostId(),post.getPostLike()); // Update the like count in the database
                redisTemplate.delete(key); // Remove the like from Redis
            } else {
                // 1.2 没有->增加点赞
                post.setPostLike(post.getPostLike() + 1);
                postMapper.updateById(post.getPostId(),post.getPostLike()); // Update the like count in the database
                redisTemplate.opsForValue().set(key, 1); // Add the like to Redis
            }
            return post.getPostLike();
        }
        throw new RuntimeException("Post not found");
    }
}

