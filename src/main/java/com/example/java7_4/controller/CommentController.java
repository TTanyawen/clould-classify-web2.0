package com.example.java7_4.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.entity.*;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.impl.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
@Tag(name="用户接口文档")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/likeComment/{commentId}")
    public Result<Long> likeComment(@RequestHeader("Authorization") String authorization, @PathVariable("commentId") Long commentId) {
        System.out.println("likeComment");
        try {
            // 增加点赞数
            Long newLikeCount = commentService.incrementCommentLike(commentId);
            redisTemplate.delete(RedisKeyConstants.COMMENTS);
            return Result.success(newLikeCount);
        } catch (Exception e) {
            return Result.error("Failed to like the comment");
        }
    }


    @PostMapping("/submitComment")
    public Result  submitComment(@RequestHeader("Authorization") String authorization, @RequestBody Map<String,String> params) {
        System.out.println("SC01");
//        Long userId=Long.parseLong(params.get("userId"));
        Long userId= BaseContext.getCurrentId();
        Long postId=Long.parseLong(params.get("postId")) ;
        String commentText=params.get("commentText");
        System.out.print("SC02");
        // 创建新的 Comment 实体
        Comment newComment = new Comment();
        newComment.setUserId(userId);
        newComment.setPostId(postId);
        newComment.setCommentText(commentText);
        newComment.setCommentLike(0L);  // 初始化点赞数为0

        // 保存到数据库
        System.out.println("SC03");
        commentService.save(newComment);
        redisTemplate.delete(RedisKeyConstants.COMMENTS);

        return Result.success();
    }


    @GetMapping("/getCommentsByPostId")
    public Result<List<CommentRespDTO>> getCommentsByPostId(@RequestHeader("Authorization") String authorization, @RequestParam Long postId) {
        List<CommentDTO> comments=commentService.getCommentsByPostId(postId);
        // commentRespDTOs;
        List<CommentRespDTO> commentRespDTOs;
        commentRespDTOs=new ArrayList<>();
        for(CommentDTO comment:comments){
            CommentRespDTO commentRespDTO=new CommentRespDTO();
            BeanUtil.copyProperties(comment,commentRespDTO);
            String key= RedisKeyConstants.LIKE_COMMENT +BaseContext.getCurrentId()+":"+commentRespDTO.getCommentId();
            // 1. 判断缓存是否有key
            Boolean isLiked = redisTemplate.hasKey(key);
            commentRespDTO.setIsLiked(isLiked);
            commentRespDTOs.add(commentRespDTO);
        }
        return Result.success(commentRespDTOs);
    }
}

