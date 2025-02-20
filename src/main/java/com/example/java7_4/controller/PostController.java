package com.example.java7_4.controller;

import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.impl.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/post")
@Tag(name="用户接口文档")
public class PostController {

    @Autowired
    private PostService postService;



    @Autowired
    private RedisTemplate redisTemplate;
    @PostMapping("/likePost/{postId}")
    public Result<Long> likePost(@RequestHeader("Authorization") String authorization, @PathVariable("postId") Long postId) {
        try {
            Long newLikeCount = postService.incrementPostLike(postId);// 增加/取消点赞
            redisTemplate.delete(RedisKeyConstants.POSTS);//因为数据库点赞数更新了，删除Redis里头的posts缓存
            return Result.success(newLikeCount);
        } catch (Exception e) {
            return Result.error("Failed to like the post");
        }
    }
}

