package com.example.java7_4.service.impl;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.java7_4.entity.Comment;
import com.example.java7_4.entity.CommentDTO;
import com.example.java7_4.exception.FailCommentException;
import com.example.java7_4.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService  {
    @Autowired
    private CommentMapper commentMapper;

    public List<Comment> getComments() {
        return commentMapper.selectAll();
    }
    public List<CommentDTO> getCommentsWithUsername() {
        return commentMapper.selectCommentsWithUsername();
    }

    public Long incrementCommentLike(Long commentId) {
//         查询当前的comment
        System.out.println("incrementCommentLike1");
        Comment comment = commentMapper.selectById(commentId);
        if (comment != null) {
            // 增加点赞数
            comment.setCommentLike(comment.getCommentLike() + 1);
            System.out.println("incrementCommentLike2");
            int cnt=commentMapper.updateById(comment);
            System.out.println("incrementCommentLike3");

            return comment.getCommentLike();
        }
        return -1L;//失败
//        throw new RuntimeException("Post not found");
    }



    public void save(Comment newComment) {
        int cnt=commentMapper.insert(newComment);
        if(cnt==0){
            throw new FailCommentException("评论失败");
        }
    }
}