package com.example.java7_4.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.entity.Comment;
import com.example.java7_4.entity.CommentDTO;
import com.example.java7_4.entity.Post;
import com.example.java7_4.exception.FailCommentException;
import com.example.java7_4.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService  {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    public List<Comment> getComments() {
        return commentMapper.selectAll();
    }
    public List<CommentDTO> getCommentsWithUsername() {
        return commentMapper.selectCommentsWithUsername();
    }

    public Long incrementCommentLike(Long commentId) {

        // 查询当前的post
        Comment comment = commentMapper.selectById(commentId);
        if (comment != null) {
            // 增加/取消点赞
            Long userId= BaseContext.getCurrentId();
            String key= RedisKeyConstants.LIKE_COMMENT +userId+":"+commentId;
            // 1. 判断缓存是否有key
            Boolean hasLiked = redisTemplate.hasKey(key);

            if (hasLiked != null && hasLiked) {
                // 1.1 有key->取消点赞
                // Decrement the like count
                comment.setCommentLike(comment.getCommentLike() - 1);
                commentMapper.updateById(comment); // Update the like count in the database
                redisTemplate.delete(key); // Remove the like from Redis
            } else {
                // 1.2 没有->增加点赞
                comment.setCommentLike(comment.getCommentLike() + 1);
                commentMapper.updateById(comment); // Update the like count in the database
                redisTemplate.opsForValue().set(key, 1); // Add the like to Redis
            }
            return comment.getCommentLike();
        }
        throw new RuntimeException("Comment not found");
////         查询当前的comment
//        Comment comment = commentMapper.selectById(commentId);
//        if (comment != null) {
//            // 增加点赞数
//            Long
//            comment.setCommentLike(comment.getCommentLike() + 1);
//            int cnt=commentMapper.updateById(comment);
//
//            return comment.getCommentLike();
//        }
//        return -1L;//失败
////        throw new RuntimeException("Post not found");
    }



    public void save(Comment newComment) {
        int cnt=commentMapper.insert(newComment);
        if(cnt==0){
            throw new FailCommentException("评论失败");
        }
    }

    public List<CommentDTO> getCommentsByPostId(Long postId) {
        return commentMapper.getCommentsByPostId(postId);
    }
}