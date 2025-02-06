package com.example.java7_4.controller;

import com.example.java7_4.entity.User;
import com.example.java7_4.result.PageResult;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name="用户接口文档")
public class MultiPageController {
    @Autowired
    UserService userService;
    @GetMapping("/getUsers")
    public Result<PageResult> getUsers(@RequestParam int page, @RequestParam int pageSize) {
        PageResult pageResult = userService.getUsersPage_pageHelper(page, pageSize);
        System.out.println("本页总共数据条数："+pageResult.getTotal());
        return Result.success(pageResult);
    }
}
