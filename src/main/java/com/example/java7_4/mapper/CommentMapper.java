package com.example.java7_4.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.java7_4.annotation.AutoFill;
import com.example.java7_4.entity.Comment;
import com.example.java7_4.entity.CommentDTO;
import com.example.java7_4.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CommentMapper {
    @Select("SELECT c.comment_id,c.user_id,c.post_id,c.comment_text,c.comment_like,u.user_name FROM tb_comment c,tb_user u WHERE c.user_id=u.user_id;")
    List<CommentDTO> selectCommentsWithUsername();

    
    @Insert("INSERT INTO tb_comment (user_id, post_id, comment_text, comment_like,create_time,update_time,create_user,update_user) VALUES (#{userId}, #{postId}, #{commentText}, #{commentLike},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    int insert(Comment comment);

    @Select("SELECT * FROM tb_comment where comment_id=#{commentId}")
    Comment selectById(Long commentId);
    @Select("SELECT * FROM tb_comment")
    List<Comment> selectAll();

    @AutoFill(value = OperationType.UPDATE)
    int updateById(Comment comment);

    @AutoFill(value = OperationType.UPDATE)
    int updateByIdTest(Comment comment);

    @Select("SELECT c.comment_id,c.user_id,c.post_id,c.comment_text,c.comment_like,u.user_name FROM tb_comment c,tb_user u WHERE c.user_id=u.user_id AND c.post_id=#{postId};")
    List<CommentDTO> getCommentsByPostId(Long postId);
}