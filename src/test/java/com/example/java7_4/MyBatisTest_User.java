package com.example.java7_4;

import com.example.java7_4.entity.Comment;
import com.example.java7_4.entity.User;
import com.example.java7_4.mapper.CommentMapper;
import com.example.java7_4.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest_User {

    @Test
    public void testSelectAll() throws IOException {
//        1. 加载核心配置文件，获取SqlSessionFaactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//        2. 获取SqlSession对象，执行SQL语句
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //3. 执行sql
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        List<User> users=userMapper.selectAll();

        System.out.println(users);
        //4. 释放
        sqlSession.close();
    }
    @Test
    public void testSelectById() throws IOException {
        Long userId=1L;
//        1. 加载核心配置文件，获取SqlSessionFaactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//        2. 获取SqlSession对象，执行SQL语句
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //3. 执行sql
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User user=userMapper.selectById(userId);

        System.out.println(user);
        //4. 释放
        sqlSession.close();
    }

    @Test
    public void testUpdateByIdTest() throws IOException {
        Comment comment=new Comment();
        comment.setCommentId(1L);
        comment.setUserId(1L);
        comment.setPostId(1L);
        comment.setCommentText("2025/1/13");
        comment.setCommentLike(49L);
//        1. 加载核心配置文件，获取SqlSessionFaactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

//        2. 获取SqlSession对象，执行SQL语句
        SqlSession sqlSession=sqlSessionFactory.openSession();

        //3. 执行sql
        CommentMapper commentMapper=sqlSession.getMapper(CommentMapper.class);
        int cnt=commentMapper.updateByIdTest(comment);

        System.out.println(cnt);
        //4. 释放
        sqlSession.close();
    }
}
