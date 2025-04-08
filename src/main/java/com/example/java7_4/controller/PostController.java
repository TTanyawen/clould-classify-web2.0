package com.example.java7_4.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.entity.*;
import com.example.java7_4.entity.dao.GetPagedSearchedPostsWithOrderReqDao;
import com.example.java7_4.result.PageResult;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.impl.CommentService;
import com.example.java7_4.service.impl.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/post")
@Tag(name="用户接口文档")
@Slf4j
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;



    @PostMapping("/collectPost/{postId}")
    public Result<Long> collectPost(@RequestHeader("Authorization") String authorization, @PathVariable("postId") Long postId) {
        try {
            Long newCollectCount = postService.incrementPostCollect(postId);// 增加/取消点赞
            redisTemplate.delete(RedisKeyConstants.POSTS);//因为数据库点赞数更新了，删除Redis里头的posts缓存
            return Result.success(newCollectCount);
        } catch (Exception e) {
            return Result.error("Failed to like the post");
        }
    }

    @RequestMapping("/getPagedSearchedPosts")
    @Operation(summary = "getPagedSearchedPosts")
    public Result<Map<String,Object>> getPagedSearchedPosts(@RequestHeader("Authorization") String authorization,@RequestBody Map<String,Object> searchRequest) {



        System.out.println("getSearchedPosts");
        String searchText=(String)searchRequest.get("searchText");
        Integer currentPage=(Integer)searchRequest.get("currentPage");
        Integer pageSize=(Integer)searchRequest.get("pageSize");
        PageResult pageResult=postService.getPagedSearchedPosts(searchText,currentPage,pageSize);

        List<PostRespDTO> postRespDTOS=new ArrayList<>();
        for(Object obj:pageResult.getRecords()){
            PostDTO post=(PostDTO)obj;
            PostRespDTO postRespDTO=new PostRespDTO();
            postRespDTO.setPostId(post.getPostId());
            postRespDTO.setUserId(post.getUserId());
            postRespDTO.setUserName(post.getUserName());
            postRespDTO.setPostText(post.getPostText());
            postRespDTO.setPostLike(post.getPostLike());
            postRespDTO.setPostCollect(post.getPostCollect());
            postRespDTO.setUserProfilePath(post.getUserProfilePath());
            postRespDTO.setCreateTime(post.getCreateTime()==null?"":post.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


            String key= RedisKeyConstants.LIKE_POST +BaseContext.getCurrentId()+":"+post.getPostId();
            // 1. 判断缓存是否有key
            Boolean isLiked = redisTemplate.hasKey(key);
            postRespDTO.setIsLiked(isLiked);

            String key2= RedisKeyConstants.COLLECT_POST +BaseContext.getCurrentId()+":"+post.getPostId();
            Boolean isCollected = redisTemplate.hasKey(key2);
            postRespDTO.setIsCollected(isCollected);

            //解析postImgPath
            String[] paths = post.getPostImgPath().split("@_@");
            List<String> pathList = new ArrayList<>();
            for (String path : paths) {
                if (!path.isEmpty()) {
                    pathList.add(path);
                }
            }

            postRespDTO.setPostImgPaths(pathList);
            postRespDTOS.add(postRespDTO);
        }

        pageResult.setRecords(postRespDTOS);
        // commentRespDTOs;
        List<CommentRespDTO> commentRespDTOs;

        commentRespDTOs=new ArrayList<>();
        for(Comment comment:commentService.getComments()){
            CommentRespDTO commentRespDTO=new CommentRespDTO();
            BeanUtil.copyProperties(comment,commentRespDTO);
            String key= RedisKeyConstants.LIKE_COMMENT +BaseContext.getCurrentId()+":"+commentRespDTO.getCommentId();
            // 1. 判断缓存是否有key
            Boolean isLiked = redisTemplate.hasKey(key);
            commentRespDTO.setIsLiked(isLiked);
            commentRespDTOs.add(commentRespDTO);
        }
        Map<String,Object> response=new HashMap<>();
        response.put("posts",pageResult);
        response.put("comments",commentRespDTOs);
        return Result.success(response);
    }

    @RequestMapping("/getPagedSearchedPostsWithOrder")
    @Operation(summary = "getPagedSearchedPostsWithOrder")
    public Result<Map<String,Object>> getPagedSearchedPostsWithOrder(@RequestHeader("Authorization") String authorization, @RequestBody GetPagedSearchedPostsWithOrderReqDao req) {

        System.out.println("getPagedSearchedPostsWithOrder");

        String searchText=req.getSearchText();
        Integer currentPage=req.getCurrentPage();
        Integer pageSize=req.getPageSize();
        String sortType=req.getSortType();
        String searchType=req.getSearchType();

//        PageResult pageResult2=postService.getPagedSearchedPosts(searchText,currentPage,pageSize);
        PageResult pageResult=postService.getPagedSearchedPostsWithOrder(searchText,sortType,searchType,currentPage,pageSize);

        List<PostRespDTO> postRespDTOS=new ArrayList<>();
        for(Object obj:pageResult.getRecords()){
            PostDTO post=(PostDTO)obj;
            PostRespDTO postRespDTO=new PostRespDTO();
            postRespDTO.setPostId(post.getPostId());
            postRespDTO.setUserId(post.getUserId());
            postRespDTO.setUserName(post.getUserName());
            postRespDTO.setPostText(post.getPostText());
            postRespDTO.setPostLike(post.getPostLike());
            postRespDTO.setPostCollect(post.getPostCollect());
            postRespDTO.setUserProfilePath(post.getUserProfilePath());
            postRespDTO.setCreateTime(post.getCreateTime()==null?"":post.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));


            String key= RedisKeyConstants.LIKE_POST +BaseContext.getCurrentId()+":"+post.getPostId();
            // 1. 判断缓存是否有key
            Boolean isLiked = redisTemplate.hasKey(key);
            postRespDTO.setIsLiked(isLiked);

            String key2= RedisKeyConstants.COLLECT_POST +BaseContext.getCurrentId()+":"+post.getPostId();
            Boolean isCollected = redisTemplate.hasKey(key2);
            postRespDTO.setIsCollected(isCollected);

            //解析postImgPath
            String[] paths = post.getPostImgPath().split("@_@");
            List<String> pathList = new ArrayList<>();
            for (String path : paths) {
                if (!path.isEmpty()) {
                    pathList.add(path);
                }
            }

            postRespDTO.setPostImgPaths(pathList);
            postRespDTOS.add(postRespDTO);
        }

        pageResult.setRecords(postRespDTOS);
        // commentRespDTOs;
        List<CommentRespDTO> commentRespDTOs;

        commentRespDTOs=new ArrayList<>();
        for(Comment comment:commentService.getComments()){
            CommentRespDTO commentRespDTO=new CommentRespDTO();
            BeanUtil.copyProperties(comment,commentRespDTO);
            String key= RedisKeyConstants.LIKE_COMMENT +BaseContext.getCurrentId()+":"+commentRespDTO.getCommentId();
            // 1. 判断缓存是否有key
            Boolean isLiked = redisTemplate.hasKey(key);
            commentRespDTO.setIsLiked(isLiked);
            commentRespDTOs.add(commentRespDTO);
        }
        Map<String,Object> response=new HashMap<>();
        response.put("posts",pageResult);
        response.put("comments",commentRespDTOs);
        return Result.success(response);
    }

    @RequestMapping("/getSearchedPosts")
    @Operation(summary = "getSearchedPosts")
    @CrossOrigin(origins = "*")
    public Result<Map<String,Object>> getSearchedPosts(@RequestHeader("Authorization") String authorization,@RequestBody Map<String,String> searchRequest) {
        System.out.println("getSearchedPosts");
        List<PostDTO> searchedPosts=postService.getSearchedPosts(searchRequest.get("searchText"));

        List<PostRespDTO> postRespDTOS=new ArrayList<>();
        for(PostDTO post:searchedPosts){
            PostRespDTO postRespDTO=new PostRespDTO();
            postRespDTO.setPostId(post.getPostId());
            postRespDTO.setUserId(post.getUserId());
            postRespDTO.setUserName(post.getUserName());
            postRespDTO.setPostText(post.getPostText());
            postRespDTO.setPostLike(post.getPostLike());
            postRespDTO.setPostCollect(post.getPostCollect());
            postRespDTO.setUserProfilePath(post.getUserProfilePath());
            postRespDTO.setCreateTime(post.getCreateTime()==null?"":post.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            String key= RedisKeyConstants.LIKE_POST +BaseContext.getCurrentId()+":"+post.getPostId();
            // 1. 判断缓存是否有key
            Boolean isLiked = redisTemplate.hasKey(key);
            postRespDTO.setIsLiked(isLiked);

            String key2= RedisKeyConstants.COLLECT_POST +BaseContext.getCurrentId()+":"+post.getPostId();
            Boolean isCollected = redisTemplate.hasKey(key2);
            postRespDTO.setIsCollected(isCollected);

            //解析postImgPath
            String[] paths = post.getPostImgPath().split("@_@");
            List<String> pathList = new ArrayList<>();
            for (String path : paths) {
                if (!path.isEmpty()) {
                    pathList.add(path);
                }
            }
            postRespDTO.setPostImgPaths(pathList);
            postRespDTOS.add(postRespDTO);
        }

        // commentRespDTOs;
        List<CommentRespDTO> commentRespDTOs;
        commentRespDTOs=new ArrayList<>();
        for(Comment comment:commentService.getComments()){
            CommentRespDTO commentRespDTO=new CommentRespDTO();
            BeanUtil.copyProperties(comment,commentRespDTO);
            String key= RedisKeyConstants.LIKE_COMMENT +BaseContext.getCurrentId()+":"+commentRespDTO.getCommentId();
            // 1. 判断缓存是否有key
            Boolean isLiked = redisTemplate.hasKey(key);
            commentRespDTO.setIsLiked(isLiked);
            commentRespDTOs.add(commentRespDTO);
        }
        Map<String,Object> response=new HashMap<>();
        response.put("posts",postRespDTOS);
        response.put("comments",commentRespDTOs);
        return Result.success(response);
    }





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

    @GetMapping("/getPostDetail")
    public Result<PostRespDTO> getPostDetail(@RequestHeader("Authorization") String authorization, @RequestParam Long postId) {

        PostDTO post=postService.getPostByPostId(postId);
        PostRespDTO postRespDTO=new PostRespDTO();


        postRespDTO.setPostId(post.getPostId());
        postRespDTO.setUserId(post.getUserId());
        postRespDTO.setUserName(post.getUserName());
        postRespDTO.setPostText(post.getPostText());
        postRespDTO.setPostLike(post.getPostLike());
        postRespDTO.setPostCollect(post.getPostCollect());
        postRespDTO.setUserProfilePath(post.getUserProfilePath());
        postRespDTO.setCreateTime(post.getCreateTime()==null?"":post.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String key= RedisKeyConstants.LIKE_POST +BaseContext.getCurrentId()+":"+post.getPostId();
        // 1. 判断缓存是否有key
        Boolean isLiked = redisTemplate.hasKey(key);
        postRespDTO.setIsLiked(isLiked);

        //解析postImgPath
        String[] paths = post.getPostImgPath().split("@_@");
        List<String> pathList = new ArrayList<>();
        for (String path : paths) {
            if (!path.isEmpty()) {
                pathList.add(path);
            }
        }
        postRespDTO.setPostImgPaths(pathList);
        return Result.success(postRespDTO);

    }

    @PostMapping("/sendPost")
    public Result<Boolean> sendPost(@RequestHeader("Authorization") String authorization
            , @RequestParam("text") String text
            , @RequestParam("image") MultipartFile file) {

        Long userId= BaseContext.getCurrentId();
        String imgPath="/posts/post"+ UUID.randomUUID()+".jpg";
        Post post=new Post();
        post.setUserId(userId);
        post.setPostImgPath(imgPath);
        post.setPostLike(0L);
        post.setPostCollect(0L);
        post.setPostText(text);

        //保存图片
        // 获取项目根目录
        String rootDirectory = System.getProperty("user.dir");

        // 定义项目外部的上传路径
        String uploadDirectory = rootDirectory + File.separator + "static" + File.separator + "images";
        // 创建文件名，使用用户ID作为前缀


        String filePath =  uploadDirectory+imgPath;
        log.info("logTag::handleFileUpload:filePath={}",filePath);



        try {
            // 创建目录（如果不存在）
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 将文件保存到指定路径
            File dest = new File(filePath);
            file.transferTo(dest);

        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传图片失败");
        }


        int cnt=postService.save(post);
        if(cnt==1){
            redisTemplate.delete(RedisKeyConstants.POSTS);//删除Redis里头的posts缓存
            return Result.success(Boolean.TRUE);
        }else{
            return Result.error("发送帖子失败");
        }

    }


    @PostMapping("/sendPostWithMutiImages")
    public Result<Boolean> sendPostWithMutiImages(@RequestHeader("Authorization") String authorization
            , @RequestParam("text") String text
            , @RequestParam("images") List<MultipartFile> images) {

        Long userId= BaseContext.getCurrentId();

        Post post=new Post();
        post.setUserId(userId);
        post.setPostLike(0L);
        post.setPostCollect(0L);

        post.setPostText(text);

        //保存图片
        // 获取项目根目录
        String rootDirectory = System.getProperty("user.dir");

        // 定义项目外部的上传路径
        String uploadDirectory = rootDirectory + File.separator + "static" + File.separator + "images";
        // 创建文件名，使用用户ID作为前缀

        List<String> postImgPathList=new ArrayList<String>();
        for(MultipartFile file:images){
            try {
                // 创建目录（如果不存在）
                File directory = new File(uploadDirectory);
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                String imgPath="/posts/post"+ UUID.randomUUID()+".jpg";
                String filePath =  uploadDirectory+imgPath;
                // 将文件保存到指定路径
                File dest = new File(filePath);
                file.transferTo(dest);

                postImgPathList.add(imgPath);
                log.info("logTag::sendPostWithMutiImages:filePath={}",filePath);

            } catch (IOException e) {
                e.printStackTrace();
                return Result.error("上传图片失败");
            }
        }


        post.setPostImgPath(String.join("@_@",postImgPathList));

        int cnt=postService.save(post);
        if(cnt==1){
            redisTemplate.delete(RedisKeyConstants.POSTS);//删除Redis里头的posts缓存
            return Result.success(Boolean.TRUE);
        }else{
            return Result.error("发送帖子失败");
        }

    }

}

