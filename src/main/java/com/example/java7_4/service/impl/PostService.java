package com.example.java7_4.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.entity.Post;

import com.example.java7_4.entity.PostCollection;
import com.example.java7_4.entity.PostDTO;
import com.example.java7_4.mapper.PostCollectionMapper;
import com.example.java7_4.mapper.PostMapper;
import com.example.java7_4.result.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostCollectionMapper postCollectionMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    public int save(Post post) {
        return postMapper.save(post);
    }
    public List<Post> getPosts() {
        return postMapper.selectAll();
    }

    public List<PostDTO> getMePosts(Long userId) {
        return postMapper.selectMePostsWithUserAvatar(userId);
    }


    public List<PostDTO> getPostsWithUserAvatar() {
        return postMapper.selectPostsWithUserAvatar();
    }
    public PageResult getPostsWithUserAvatar(int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        Page<PostDTO> pageInfo=postMapper.selectPagedPostsWithUserAvatar();
        return new PageResult(pageInfo.getTotal(),pageInfo.getResult());
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

    public List<PostDTO> getSearchedPosts(String searchText) {
        return postMapper.getSearchedPosts(searchText);
    }

    public PageResult getPagedSearchedPosts(String searchText,int currentPage,int pageSize) {
        PageHelper.startPage(currentPage,pageSize);
        Page<PostDTO> pageInfo=postMapper.getPagedSearchedPosts(searchText);
        return new PageResult(pageInfo.getTotal(),pageInfo.getResult());
    }

    public PostDTO getPostByPostId(Long postId) {
        return postMapper.getPostByPostId(postId);
    }

    public Long incrementPostCollect(Long postId) {
        // 查询当前的post
        Post post = postMapper.selectById(postId);
        if (post != null) {
            // 增加/取消点赞
            Long userId=BaseContext.getCurrentId();
            String key= RedisKeyConstants.COLLECT_POST +userId+":"+postId;
            // 1. 判断缓存是否有key
            Boolean hasCollected = redisTemplate.hasKey(key);

            // 增加一条收藏记录
            PostCollection postCollection=new PostCollection();
            postCollection.setUserId(BaseContext.getCurrentId());
            postCollection.setPostId(postId);
            postCollection.setCollectTime(LocalDateTime.now());
            if (hasCollected != null && hasCollected) {
                // 1.1 有key->取消点赞
                // Decrement the like count
                post.setPostCollect(post.getPostCollect() - 1);
                postMapper.updatePostCollectById(post.getPostId(),post.getPostCollect()); // Update the like count in the database
                redisTemplate.delete(key); // Remove the like from Redis

                postCollectionMapper.delete(postCollection);
            } else {
                // 1.2 没有->增加点赞
                post.setPostCollect(post.getPostCollect() + 1);
                postMapper.updatePostCollectById(post.getPostId(),post.getPostCollect()); // Update the like count in the database
                redisTemplate.opsForValue().set(key, 1); // Add the like to Redis

                postCollectionMapper.save(postCollection);
            }



            return post.getPostCollect();
        }
        throw new RuntimeException("Post not found");
    }
}

