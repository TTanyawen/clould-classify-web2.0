package com.example.java7_4.controller;

import com.example.java7_4.constant.RedisKeyConstants;
import com.example.java7_4.entity.CloudCard;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.impl.CloudCardService;
import com.example.java7_4.service.impl.CloudTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/card")
@RestController
@Tag(name="卡牌接口文档")
@Slf4j
@CrossOrigin(origins = "*")
public class CloudCardController {


    @Autowired
    private CloudCardService cloudCardService;

    @RequestMapping("/getCloudCardByCloudName")
    @Operation(summary = "getCloudCardByCloudName")
    public Result<CloudCard> getCloudCardByCloudName(@RequestHeader("Authorization") String authorization, @RequestParam("cloudName") String cloudName) {
        CloudCard cloudCard=cloudCardService.getCloudCardByCloudName(cloudName);
        return Result.success(cloudCard);
    }
}
