package com.example.java7_4.controller;

import com.example.java7_4.entity.*;
import com.example.java7_4.service.impl.CloudTypeService;

import com.example.java7_4.service.impl.CommentService;
import com.example.java7_4.service.impl.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//拿页面的
@Controller
public class getPageController {

    @Autowired
    private CloudTypeService cloudTypeService;
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @RequestMapping({"/index","/"})
    public String getIndex() {
        return "index";
    }
    @RequestMapping("/know")
    public String getKnow() {
        return "know";
    }

    @RequestMapping("/forum")
    public String getForum() {
        return "forum";
    }



    @RequestMapping("/me")
    public String getMe() {
        return "me";
    }
    @RequestMapping("/classify")
    public String getClassify() {
        return "classify";
    }


    @RequestMapping("/detail")
    public String getDetail(@RequestParam("typeId") Long typeId,HttpSession session) {
        session.setAttribute("typeId",typeId);
        return "cloud_detail";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/test")
    public String test(){
        return "hello";
    }

    @RequestMapping("/logOut")
    public String logOut(HttpSession session){
        session.removeAttribute("userLogin");
        return "redirect:/index";
    }
    @RequestMapping("/users")
    public String getUsers(){
        return "users";
    }

    @RequestMapping("/edit")
    public String getEdit(){
        return "edit";
    }

    @RequestMapping("/sendPost")
    public String getSendPost(){
        return "sendPost";
    }

    @RequestMapping("/testBigImg")
    public String getTestBigImg(){
        return "testBigImg";
    }
}
