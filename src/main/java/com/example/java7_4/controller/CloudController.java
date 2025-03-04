package com.example.java7_4.controller;
import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.entity.*;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.impl.CloudTypeService;

import com.example.java7_4.service.impl.CommentService;
import com.example.java7_4.service.impl.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/user")
//@Controller
@RestController
@Tag(name="用户接口文档")
public class CloudController {

    @Autowired
    private CloudTypeService cloudTypeService;
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping({"/getCloudTypes"})
    @Operation(summary = "getAllCloudTypes")
    public Result<List<CloudType>> getAllCloudTypes(@RequestHeader("Authorization") String authorization) {
        System.out.println("getAllCloudTypes");
        List<CloudType> cloudTypes=cloudTypeService.getCloudTypes();
        return Result.success(cloudTypes);
    }

    @RequestMapping("/getKnowData")
    @Operation(summary = "getKnowData")
    public Result<List<CloudType>> getKnowData(@RequestHeader("Authorization") String authorization) {
        String key= RedisKeyConstants.CLOUD_TYPES;
        List<CloudType> clouds=(List<CloudType>) redisTemplate.opsForValue().get(key);
        if(clouds!=null&&clouds.size()>0){
            System.out.println("get from Redis");
            return Result.success(clouds);
        }

        clouds=cloudTypeService.getCloudTypes();
        redisTemplate.opsForValue().set(key,clouds);
        return Result.success(clouds);
    }

    @RequestMapping("/getMeData")
    @Operation(summary = "getMeData")
    public Result<List<PostDTO>> getMeData(@RequestHeader("Authorization") String authorization,HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        System.out.println("userId:"+userId);
        List<PostDTO> posts = postService.getMePosts(userId);

        return Result.success(posts);
    }


    @RequestMapping("/getForumData")
    @Operation(summary = "getForumData")
    public Result<Map<String,Object>> getForumData(@RequestHeader("Authorization") String authorization) {
        System.out.println("getForumData");
        String key1=RedisKeyConstants.POSTS;
        String key2=RedisKeyConstants.COMMENTS;
        List<PostDTO> posts=(List<PostDTO>)redisTemplate.opsForValue().get(key1);
        List<CommentDTO> comments=(List<CommentDTO>)redisTemplate.opsForValue().get(key2);
        List<PostRespDTO> postRespDTOS;
        Map<String,Object> response=new HashMap<>();
        if(posts!=null&&posts.size()>0){
            System.out.println("ExistRedis-Posts-getForumData");
        }else{
            posts = postService.getPostsWithUserAvatar();
            redisTemplate.opsForValue().set(key1,posts);
        }
        if(comments!=null&&comments.size()>0){
            System.out.println("ExistRedis-Comments-getForumData");
        }else{
            comments= commentService.getCommentsWithUsername();
            redisTemplate.opsForValue().set(key2,comments);
        }
        System.out.println("Posts-getForumData:"+posts.toString());
        System.out.println("Comments-getForumData:"+comments.toString());

        //转：posts里面的String解析一下
        postRespDTOS=new ArrayList<>();
        for(PostDTO post:posts){
            PostRespDTO postRespDTO=new PostRespDTO();
            postRespDTO.setPostId(post.getPostId());
            postRespDTO.setUserId(post.getUserId());
            postRespDTO.setUserName(post.getUserName());
            postRespDTO.setPostText(post.getPostText());
            postRespDTO.setPostLike(post.getPostLike());
            postRespDTO.setUserProfilePath(post.getUserProfilePath());

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
        response.put("posts",postRespDTOS);
        response.put("comments",comments);

        System.out.println("Response-getForumData:"+response.get("posts").toString());
        System.out.println("Response-getForumData:"+response.get("comments").toString());

        return Result.success(response);
    }
    @RequestMapping("/getDetailData")
    @Operation(summary = "getDetailData")
    public Result<CloudType> getDetail(@RequestHeader("Authorization") String authorization,@RequestParam("typeId") Long typeId) {
        String key=RedisKeyConstants.CLOUD_TYPE+typeId;
        CloudType cloudType=(CloudType)redisTemplate.opsForValue().get(key);
        if(cloudType!=null){
            System.out.println("get type from Redis");
            return Result.success(cloudType);
        }
        cloudType=cloudTypeService.getCloudTypeById(typeId);
        redisTemplate.opsForValue().set(key,cloudType);
        return Result.success(cloudType);
    }
    @RequestMapping("/classify")
    public String getClassify() {
        return "classify";
    }

}



//package com.example.java7_4.controller;
//import com.example.java7_4.entity.*;
//import com.example.java7_4.service.impl.CloudTypeService;
//
//import com.example.java7_4.service.impl.CommentService;
//import com.example.java7_4.service.impl.PostService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class CloudController {
//
//    @Autowired
//    private CloudTypeService cloudTypeService;
//    @Autowired
//    private PostService postService;
//
//    @Autowired
//    private CommentService commentService;
//
//    @RequestMapping({"/index","/"})
//    public String getIndex(Model model) {
//        List<CloudType> clouds;
//        clouds = cloudTypeService.getCloudTypes();
//        model.addAttribute("clouds", clouds);
//
//
////        //处理静态资源
////        //这里 /img/uploadFile/ 可以更改为不同层级的目录，可以跟开发时的静态目录统一
////        String path = System.getProperty("user.dir")+"/static/images/profiles/";
////        File dir = new File(path);
////// 如果不存在则创建目录
////        if(!dir.exists()){
////            dir.mkdirs();
////        }
//
//        return "index";
//    }
//    @RequestMapping("/know")
//    public String getKnow(Model model) {
//        System.out.println("getKnow");
//        List<CloudType> clouds;
//        clouds = cloudTypeService.getCloudTypes();
//        model.addAttribute("clouds", clouds);
//        return "know";
//    }
//
////    @RequestMapping("/forum")
////    public String getForum(Model model) {
//////        List<Post> posts;
//////        posts = postService.getPosts();
//////        model.addAttribute("posts", posts);
////        List<PostDTO> posts = postService.getPostsWithUserAvatar();
////
////        model.addAttribute("posts", posts);
////
//////        List<Comment> comments;
//////        comments = commentService.getComments();
//////        model.addAttribute("comments", comments);
////        List<CommentDTO> comments= commentService.getCommentsWithUsername();;
////        model.addAttribute("comments", comments);
////
////        return "forum";
////    }
//
//    @RequestMapping("/test")
//    public String test(){
//        return "hello";
//    }
//    @RequestMapping("/forum2")
//    public String getForum2( Model model) {
//        System.out.println("getForum1");
//        //验证token拦截器会做
//        List<PostDTO> posts = postService.getPostsWithUserAvatar();
//        model.addAttribute("posts", posts);
//
//        List<CommentDTO> comments= commentService.getCommentsWithUsername();;
//        model.addAttribute("comments", comments);
////        System.out.println("Authorization: " + authorization);  // 确保 Authorization 被正确传入
//
//        System.out.println("即将转入forum");
////        Map<String,String> response =new HashMap<>();
////        response.put("url","/forum");
////        return ResponseEntity.ok(response);
//        return "forum";
////        return test();
//
//    }
//
//
//    @RequestMapping("/forum")
//    public ResponseEntity<Map<String,String>> getForum(@RequestHeader("Authorization") String authorization, Model model) {
//        System.out.println("getForum1");
//        //验证token拦截器会做
//        List<PostDTO> posts = postService.getPostsWithUserAvatar();
//        model.addAttribute("posts", posts);
//
//        List<CommentDTO> comments= commentService.getCommentsWithUsername();;
//        model.addAttribute("comments", comments);
//        System.out.println("Authorization: " + authorization);  // 确保 Authorization 被正确传入
//
//        System.out.println("即将转入forum");
//        Map<String,String> response =new HashMap<>();
//        response.put("url","/forum2");
//        return ResponseEntity.ok(response);
//
//
//    }
//    @RequestMapping("/me")
//    public String getMe(Model model,HttpSession session) {
//        Long userId = (Long) session.getAttribute("userId");
//        List<PostDTO> posts = postService.getMePosts(userId);
//
//        model.addAttribute("posts", posts);
//
//        return "me";
//    }
//    @RequestMapping("/classify")
//    public String getClassify() {
//        return "classify";
//    }
//
//
//    @RequestMapping("/detail")
//    public String getDetail(@RequestParam("typeId") Long typeId, Model model) {
//        System.out.println("getDetail");
//        CloudType cloudType=cloudTypeService.getCloudTypeById(typeId);
//        model.addAttribute("cloudType", cloudType);
//        return "cloud_detail";
//    }
//
//    @RequestMapping("/protected-resource")
//    public ResponseEntity<String> getProtectedResource(@RequestHeader("Authorization") String authorization){
//        return ResponseEntity.ok(null);
//    }
//}
