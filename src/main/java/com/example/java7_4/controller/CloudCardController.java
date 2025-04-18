package com.example.java7_4.controller;

import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.entity.*;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.UserService;
import com.example.java7_4.service.impl.CloudCardService;
import com.example.java7_4.service.impl.CloudTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/card")
@RestController
@Tag(name="卡牌接口文档")
@Slf4j
@CrossOrigin(origins = "*")
public class CloudCardController {


    @Autowired
    private CloudCardService cloudCardService;

    @Autowired
    private UserService userService;

    @RequestMapping("/getAllCloudCards")
    @Operation(summary = "getAllCloudCards")
    public Result <List<CloudCard>> getAllCloudCards(@RequestHeader("Authorization") String authorization) {
        List<CloudCard> cloudCards = cloudCardService.getAllCloudCards();
        return Result.success(cloudCards);
    }

    @RequestMapping("/getUserCloudCollectionInfo")
    @Operation(summary = "getUserCloudCollectionInfo")
    public Result <List<UserCloudCollectionInfoResp>> getUserCloudCollectionInfo(@RequestHeader("Authorization") String authorization) {
        Long userId = BaseContext.getCurrentId();
        List<CloudCard> cloudCards = cloudCardService.getAllCloudCards();
        List<UserCloudCollectionInfoResp> userCloudCollectionInfoResps=new ArrayList<>();
        List<UserCardCollect> userCardCollects = cloudCardService.getUserCardCollectsByUserId(userId);
        // 创建一个 Map 用于存储结果
        Map<Long, UserCardCollect> userCardCollectMap = new HashMap<>();

        // 遍历列表，将 UserCardCollect 对象按照 typeId 存储到 Map 中
        for (UserCardCollect userCardCollect : userCardCollects) {
            userCardCollectMap.put(userCardCollect.getTypeId(), userCardCollect);
        }
        for(CloudCard cloudCard:cloudCards){
            UserCloudCollectionInfoResp userCloudCollectionInfoResp=new UserCloudCollectionInfoResp();
            userCloudCollectionInfoResp.setTypeId(cloudCard.getTypeId());
            userCloudCollectionInfoResp.setTypeName(cloudCard.getTypeName());
            userCloudCollectionInfoResp.setTypeNameEn(cloudCard.getTypeNameEn());
            userCloudCollectionInfoResp.setTypeInfo(cloudCard.getTypeInfo());
            userCloudCollectionInfoResp.setTypePoint(cloudCard.getTypePoint());

            if(userCardCollectMap.containsKey(cloudCard.getTypeId())){
                userCloudCollectionInfoResp.setCollectNum(userCardCollectMap.get(cloudCard.getTypeId()).getCollectNum());
                userCloudCollectionInfoResp.setIsCollected(true);
            }else{
                userCloudCollectionInfoResp.setCollectNum(0L);
                userCloudCollectionInfoResp.setIsCollected(false);
            }
            userCloudCollectionInfoResps.add(userCloudCollectionInfoResp);
        }

        return Result.success(userCloudCollectionInfoResps);
    }


    @RequestMapping("/getCloudCardByCloudName")
    @Operation(summary = "getCloudCardByCloudName")
    public Result<CloudCard> getCloudCardByCloudName(@RequestHeader("Authorization") String authorization, @RequestParam("cloudName") String cloudName) {
        CloudCard cloudCard = cloudCardService.getCloudCardByCloudName(cloudName);
        return Result.success(cloudCard);
    }

    @GetMapping("/getUserCardCollectsByUserId")
    @Operation(summary = "getUserCardCollectsByUserId")
    public Result<List<UserCardCollect>> getUserCardCollectUserId(@RequestHeader("Authorization") String authorization, @RequestParam("userId") Long userId) {
        List<UserCardCollect> userCardCollects = cloudCardService.getUserCardCollectsByUserId(userId);
        return Result.success(userCardCollects);
    }

    @GetMapping("/getUserCardCollectDetailsByUserId")
    @Operation(summary = "getUserCardCollectDetailsByUserId")
    public Result<List<UserCardCollectDetail>> getUserCardCollectDetailsByUserId(@RequestHeader("Authorization") String authorization, @RequestParam("userId") Long userId) {
        List<UserCardCollectDetail> userCardCollectDetails = cloudCardService.getUserCardCollectDetailsByUserId(userId);
        return Result.success(userCardCollectDetails);
    }

    @GetMapping("/getUserCardCollectDetailsByUserIdAndTypeId")
    @Operation(summary = "getUserCardCollectDetailsByUserIdAndTypeId")
    public Result<List<UserCardCollectDetail>> getUserCardCollectDetailsByUserIdAndTypeId(@RequestHeader("Authorization") String authorization, @RequestParam("userId") Long userId,@RequestParam("typeId") Long typeId) {
        userId=BaseContext.getCurrentId();
        List<UserCardCollectDetail> userCardCollectDetails = cloudCardService.getUserCardCollectDetailsByUserIdAndTypeId(userId,typeId);
        return Result.success(userCardCollectDetails);
    }

    /**
     * 收集卡牌
     */
    @RequestMapping("/collectCard")
    @Operation(summary = "collectCard")
    public Result<Boolean> collectCard(@RequestHeader("Authorization") String authorization, @RequestParam("cloudName") String cloudName, @RequestParam("userId") Long userId,@RequestParam("classifyImg") String classifyImg) {
        log.info("cloudName:{},userId:{}",cloudName,userId);
        if(userId==0){
            log.info("userId==0");
            userId= BaseContext.getCurrentId();
            log.info("userId:{}",userId);
        }
        //查卡牌详情
        CloudCard cloudCard = cloudCardService.getCloudCardByCloudName(cloudName);

        //给用户加分
        User user = userService.getUserById(userId);
        user.setUserPoints(user.getUserPoints() + cloudCard.getTypePoint());
        userService.updateById(user);

        //查user_card_collect表
        //如果用户没有这张卡牌则在user_card_collect加一条记录
        UserCardCollect userCardCollect = cloudCardService.getUserCardCollectsByUserIdAndTypeId(userId, cloudCard.getTypeId());
        LocalDateTime currentTime = LocalDateTime.now();
        if (null == userCardCollect) {
            userCardCollect = new UserCardCollect();
            userCardCollect.setUserId(userId);
            userCardCollect.setTypeId(cloudCard.getTypeId());
            userCardCollect.setFirstCollectTime(currentTime);
            userCardCollect.setCollectNum(1L);
            cloudCardService.saveUserCardCollect(userCardCollect);
        } else {
            userCardCollect.setCollectNum(userCardCollect.getCollectNum() + 1);
            cloudCardService.updateUserCardCollectByUserIdAndTypeId(userCardCollect);
        }

        //在user_card_collect_detail
        UserCardCollectDetail userCardCollectDetail = new UserCardCollectDetail();
        userCardCollectDetail.setUserId(userId);
        userCardCollectDetail.setTypeId(cloudCard.getTypeId());
        userCardCollectDetail.setCollectTime(currentTime);
        userCardCollectDetail.setClassifyImg(classifyImg);
        log.info("userCardCollectDetail:{}",userCardCollectDetail);
        cloudCardService.saveUserCardCollectDetail(userCardCollectDetail);

        return Result.success(Boolean.TRUE);
    }

    /**
     * 用户是否收集过此卡牌
     */
    @RequestMapping("/isUserCollectCard")
    @Operation(summary = "isUserCollectCard")
    public Result<Boolean> isUserCollectCard(@RequestHeader("Authorization") String authorization, @RequestParam("cloudName") String cloudName, @RequestParam("userId") Long userId) {
        if(userId==0){
            userId= BaseContext.getCurrentId();
        }        //查卡牌详情
        CloudCard cloudCard = cloudCardService.getCloudCardByCloudName(cloudName);

        UserCardCollect userCardCollect=cloudCardService.getUserCardCollectsByUserIdAndTypeId(userId,cloudCard.getTypeId());

        if(null==userCardCollect){
            return Result.success(Boolean.FALSE);//未收集过
        }else{
            return Result.success(Boolean.TRUE);//收集过
        }
    }

    /**
     * 按照时间类型返回用户收集的卡牌情况
     * 时间类型: all,thisDay,thisWeek,thisMonth
     * @param authorization
     * @return
     */
    @GetMapping("/getUserCardCollectDetailsByUserIdWithTimeScale")
    @Operation(summary = "getUserCardCollectDetailsByUserIdWithTimeScale")
    public Result<List<UserCardCollectDetail>> getUserCardCollectDetailsByUserIdWithTimeScale(@RequestHeader("Authorization") String authorization, @RequestParam("timeType") String timeType) {
        Long userId=BaseContext.getCurrentId();
        List<UserCardCollectDetail> userCardCollectDetails = cloudCardService.getUserCardCollectDetailsByUserIdWithTimeScale(userId,timeType);
        return Result.success(userCardCollectDetails);
    }

}