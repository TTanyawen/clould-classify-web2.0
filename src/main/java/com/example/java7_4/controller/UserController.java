package com.example.java7_4.controller;

import com.example.java7_4.constant.JwtClaimsConstant;
import com.example.java7_4.context.BaseContext;
import com.example.java7_4.entity.MeEditQueryRepDTO;
import com.example.java7_4.entity.MeEditUpdateReqDTO;
import com.example.java7_4.entity.User;
import com.example.java7_4.exception.FailRegisterException;
import com.example.java7_4.properties.JwtProperties;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.MeEditService;
import com.example.java7_4.service.UserService;
import com.example.java7_4.util.JwtUtil;
import com.example.java7_4.util.MD5Util;
import com.example.java7_4.util.ValidateCode;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
@Tag(name="用户接口文档")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MeEditService meEditService;
    @Autowired
    private JwtProperties jwtProperties;

   //注册时，检查
   // 1. 用户名校验(合法性+唯一性)
    //2. 密码校验
    @RequestMapping("/checkRegister")
    public Result checkRegister(@RequestBody Map<String,String> params,HttpSession session,Model model){

        System.out.println("checkRegister1");

        String userName=params.get("userName");
        String passw1=params.get("passw1");
        String passw2=params.get("passw2");

        if(passw1==null||passw2==null||userName==null){
            throw new FailRegisterException("输入不能为空");
        }
        //两次输入的密码不一致
        if(!passw1.equals(passw2)){

            model.addAttribute("errorMessage", "两次密码不一致");
            throw new FailRegisterException("两次密码不一致");
        }

        //用户名校验(唯一性)
        User user = userService.getUserByUserName(userName);
        if(user != null){//不唯一
            model.addAttribute("errorMessage", "该用户名已存在!");
            throw new FailRegisterException("该用户名已存在");
        }

        //校验合法性
        if(!ValidateCode.UserNameIsValid(userName)){
            model.addAttribute("errorMessage", "用户名为不少于3位的数字与字母的组合！");
            throw new FailRegisterException("用户名为不少于3位的数字与字母的组合！");

        }
        if(!ValidateCode.PasswordIsValid(passw1)){
            model.addAttribute("errorMessage", "密码为不少于6位的数字与字母的组合！");
            throw new FailRegisterException("密码为不少于6位的数字与字母的组合");

        }


        //向数据库插入数据
        User user1 = new User();
        user1.setUserName(userName);
        user1.setUserPassword(new MD5Util().md5(passw1));
        int count=userService.save(user1);

//        //注册后自动登录
//        User newUser=userService.login(userName,passw1);
//        session.setAttribute("userLogin", user1);
//        session.setAttribute("userName", userName);


        return Result.success();
    }

//    @RequestMapping("/index/toLogin")
//    public String toLogin() {
//        return "login";
//    }



//    @RequestMapping("/index/doLogin")
//    public String doLogin(@RequestParam String userName, @RequestParam String userPassword,Model model) {
//
//        User user = userService.login(userName, userPassword);
//        System.out.println("doLogin1");
//        if (user != null) {
////            session.setAttribute("userLogin", user);
////            session.setAttribute("userName", userName);
//
//            //为用户生成jwt令牌
//            Map<String,Object> claims=new HashMap<>();
//
//            claims.put(JwtClaimsConstant.USER_ID,user.getUserId());
//            String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
//            System.out.println("token:"+token);
//
//            System.out.println("doLogin2");
//
//            //把token返回给前端
//            model.addAttribute("token",token);
//            return "redirect:/index";
//        } else {
//            model.addAttribute("errorMessage", "用户名或密码错误，请重新输入。");
//            return "login";  // 返回到登录页面，并显示错误信息
//        }
//
//    }

//    @RequestMapping("/logOut")
//    public String logOut(HttpSession session){
//        session.removeAttribute("userLogin");
//        return "redirect:/index";
//    }


    @RequestMapping("/doLogin")
    public Result<String> doLogin(@RequestBody Map<String,String> params, HttpSession session) {
        String userName=params.get("userName");
        String userPassword=params.get("userPassword");

        System.out.println("userName:"+userName+",userPassword:"+userPassword);

        User user = userService.login(userName, new MD5Util().md5(userPassword));
        session.setAttribute("userLogin", user);
        session.setAttribute("userName", userName);
        session.setAttribute("userId", user.getUserId());
        //为用户生成jwt令牌
        Map<String,Object> claims=new HashMap<>();

        claims.put(JwtClaimsConstant.USER_ID,user.getUserId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);
        System.out.println("token:"+token);
        System.out.println("doLogin2");

        String response=token;

        return Result.success(response);

    }


    @RequestMapping("/edit/query")
    public Result<MeEditQueryRepDTO> editQuery() {
        Long userId=BaseContext.getCurrentId();
        MeEditQueryRepDTO meEditQueryRepDTO =meEditService.getMeInfoById(userId);
        return Result.success(meEditQueryRepDTO);
    }


    @RequestMapping("/edit/update")
    public Result<Boolean> editUpdate(@RequestBody MeEditUpdateReqDTO meEditUpdateReqDTO,HttpSession session) {
        Long userId=BaseContext.getCurrentId();
        meEditUpdateReqDTO.setUserId(userId);

        log.info("logTag::editUpdate::userId:{}",userId);


        String userName=meEditUpdateReqDTO.getUserName();

        //校验合法性
        if(!ValidateCode.UserNameIsValid(userName)){
            log.info("logTag::用户名为不少于3位的数字与字母的组合！");
            return Result.error("用户名为不少于3位的数字与字母的组合！");
        }
        if(!ValidateCode.PasswordIsValid(meEditUpdateReqDTO.getUserPassword())){
            log.info("logTag::密码为不少于6位的数字与字母的组合");
            return Result.error("密码为不少于6位的数字与字母的组合");
        }
        //校验用户名唯一性
        User user=userService.getUserByUserName(userName);
        if(user!=null&&user.getUserId()!=userId){
            log.info("logTag::此用户名已存在，请修改");
            return Result.error("此用户名已存在，请修改");
        }

        //向数据库插入数据
        //加密
        meEditUpdateReqDTO.setUserPassword(new MD5Util().md5(meEditUpdateReqDTO.getUserPassword()));
        int count =meEditService.updateMeInfoById(meEditUpdateReqDTO);
        if(count==0){
            return Result.error("插入失败");
        }
        session.setAttribute("userId", userId);
        return Result.success(Boolean.TRUE);
    }
}
