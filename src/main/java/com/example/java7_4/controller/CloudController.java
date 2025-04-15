package com.example.java7_4.controller;
import cn.hutool.core.bean.BeanUtil;
import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.entity.*;
import com.example.java7_4.entity.Enum.TagEnum;
import com.example.java7_4.entity.dao.GetMeDataRespDto;
import com.example.java7_4.entity.dao.GetPagedForumDataWithConditionReqDto;
import com.example.java7_4.result.PageResult;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.impl.CloudTypeService;

import com.example.java7_4.service.impl.CommentService;
import com.example.java7_4.service.impl.PostService;
import com.example.java7_4.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/data")
//@Controller
@RestController
@Tag(name="数据接口文档")
@Slf4j
@CrossOrigin(origins = "*")
public class CloudController {

    @Autowired
    private CloudTypeService cloudTypeService;
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/getPagedForumDataWithCondition")
    @Operation(summary = "getPagedForumDataWithCondition")
    public Result<Map<String,Object>> getPagedForumDataWithCondition(@RequestHeader("Authorization") String authorization, @RequestBody GetPagedForumDataWithConditionReqDto req) {
        int currentPage=req.getCurrentPage();//第几页
        int pageSize=req.getPageSize();//每一页几条
        String sortType=req.getSortType();//like_desc/collect_desc/time_asc/time_desc/
//        PageResult pageResult2=postService.getPostsWithUserAvatar(currentPage,pageSize);
        PageResult pageResult=postService.getPostsWithUserAvatarWithOrder(currentPage,pageSize,sortType);
        List<CommentRespDTO> commentRespDTOs;

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


            //解析postTags
            String[] tags = post.getPostTags().split("@_@");
            List<String> tagLists = new ArrayList<>();
            for (String tag : tags) {
                if (!tag.isEmpty()) {
                    tagLists.add(TagEnum.getByCode(tag).getValue());
                }
            }
            postRespDTO.setPostTags(tagLists);



            postRespDTOS.add(postRespDTO);
        }

        pageResult.setRecords(postRespDTOS);


        Map<String,Object> response=new HashMap<>();

        List<CommentDTO> comments=commentService.getCommentsWithUsername();
        // commentRespDTOs;
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

        response.put("posts",pageResult);
        response.put("comments",commentRespDTOs);
        return Result.success(response);
    }

    @RequestMapping("/getPagedForumData")
    @Operation(summary = "getPagedForumData")
    public Result<Map<String,Object>> getPagedForumData(@RequestHeader("Authorization") String authorization, @RequestBody Map<String,Integer> requestBody) {
        int currentPage=requestBody.get("currentPage");//第几页
        int pageSize=requestBody.get("pageSize");//每一页几条

        PageResult pageResult=postService.getPostsWithUserAvatar(currentPage,pageSize);

        List<CommentRespDTO> commentRespDTOs;

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


        Map<String,Object> response=new HashMap<>();

        List<CommentDTO> comments=commentService.getCommentsWithUsername();
        // commentRespDTOs;
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

        response.put("posts",pageResult);
        response.put("comments",commentRespDTOs);
        return Result.success(response);
    }

    @RequestMapping({"/getCloudTypes"})
    @Operation(summary = "getAllCloudTypes")
    @CrossOrigin(origins = "*")
    public Result<List<CloudType>> getAllCloudTypes(@RequestHeader("Authorization") String authorization) {
        System.out.println("getAllCloudTypes");
        List<CloudType> cloudTypes=cloudTypeService.getCloudTypes();
        return Result.success(cloudTypes);
    }

    @RequestMapping("/getKnowData")
    @Operation(summary = "getKnowData")
    @CrossOrigin(origins = "*")
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
    public Result<GetMeDataRespDto> getMeData(@RequestHeader("Authorization") String authorization) {
        Long userId=BaseContext.getCurrentId();
        User user=userServiceImpl.getUserById(userId);
        List<PostDTO> posts = postService.getMePosts(userId);
        log.info("userid:{}",BaseContext.getCurrentId());
        log.info("posts num:{}",posts.size());

        List<PostRespDTO> postRespDTOS=new ArrayList<>();
        for(PostDTO post:posts){
            PostRespDTO postRespDTO=new PostRespDTO();
            postRespDTO.setPostId(post.getPostId());
            postRespDTO.setUserId(post.getUserId());
            postRespDTO.setUserName(post.getUserName());
            postRespDTO.setPostText(post.getPostText());
            postRespDTO.setPostLike(post.getPostLike());
            postRespDTO.setPostCollect(post.getPostCollect());
            postRespDTO.setCreateTime(post.getCreateTime()==null?"":post.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
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
        log.info("postRespDTOS num:{}",postRespDTOS.size());

        GetMeDataRespDto getMeDataRespDto=new GetMeDataRespDto();
        getMeDataRespDto.setPostRespDTOs(postRespDTOS);
        getMeDataRespDto.setUserId(user.getUserId());
        getMeDataRespDto.setUserName(user.getUserName());
        getMeDataRespDto.setUserProfilePath(user.getUserProfilePath());
        getMeDataRespDto.setUserPoints(user.getUserPoints());
        return Result.success(getMeDataRespDto);
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
        List<CommentRespDTO> commentRespDTOs;
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
            postRespDTO.setPostCollect(post.getPostCollect());
            postRespDTO.setUserProfilePath(post.getUserProfilePath());
            postRespDTO.setCreateTime(post.getCreateTime()==null?"":post.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            String key= RedisKeyConstants.LIKE_POST +BaseContext.getCurrentId()+":"+post.getPostId();
            // 1. 判断缓存是否有key
            Boolean isLiked = redisTemplate.hasKey(key);
            postRespDTO.setIsLiked(isLiked);

            String key_collect= RedisKeyConstants.COLLECT_POST +BaseContext.getCurrentId()+":"+post.getPostId();
            Boolean isCollected = redisTemplate.hasKey(key_collect);
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

        response.put("posts",postRespDTOS);
        response.put("comments",commentRespDTOs);

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
