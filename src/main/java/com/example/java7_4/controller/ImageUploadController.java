package com.example.java7_4.controller;

import com.example.java7_4.entity.User;
import com.example.java7_4.result.Result;
import com.example.java7_4.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@Tag(name="用户接口文档")
@Slf4j
@CrossOrigin(origins = "*")
public class ImageUploadController {

    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        // 获取项目根目录
        String rootDirectory = System.getProperty("user.dir");

        // 定义项目外部的上传路径
        String uploadDirectory = rootDirectory + File.separator + "static" + File.separator + "images" + File.separator + "profiles";

        // 创建文件名，使用用户ID作为前缀
        String originalFileName = file.getOriginalFilename();
        String fileName = userId + ".jpg";

        // 定义文件的完整路径
        String filePath = uploadDirectory + File.separator + fileName;

        try {
            // 创建目录（如果不存在）
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 将文件保存到指定路径
            File dest = new File(filePath);
            file.transferTo(dest);

            // 在数据库中保存相对路径 (fileName 或 filePath)
            saveFilePathToDatabase(userId, "/profiles/" + fileName);

            // 返回文件路径给客户端
            return "{\"status\": \"success\", \"path\": \"" + filePath + "\"}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"status\": \"error\"}";
        }
    }

//    @PostMapping("/upload")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userid") String userid) {
//        // 获取项目的根目录
//        String rootDirectory = System.getProperty("user.dir");
//        // 定义上传路径
//        String uploadDirectory = rootDirectory + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator + "profiles";
//
//        // 创建文件名，使用用户ID作为前缀
//        String originalFileName = file.getOriginalFilename();
//
//        String fileName = userid +  ".jpg";
//
//        String filePath = uploadDirectory + File.separator + fileName;
//
//        try {
//            // 创建目录（如果不存在）
//            File directory = new File(uploadDirectory);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // 将文件保存到指定路径
//            File dest = new File(filePath);
//            file.transferTo(dest);
//
//            // 在数据库中保存路径 (fileName 或 filePath)
//            saveFilePathToDatabase(userid,"/profiles/"+fileName);
//
//            return "{\"status\": \"success\", \"path\": \"" + filePath + "\"}";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "{\"status\": \"error\"}";
//        }
//    }



    @PostMapping("/upload2")
    public ResponseEntity<String> handleFileUpload2(@RequestParam("file") MultipartFile file) {
        System.out.println("upload2");

        // 获取项目的根目录
        String rootDirectory = System.getProperty("user.dir");
        // 定义上传路径
//        String uploadDirectory = rootDirectory + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator + "classify";
        String uploadDirectory = rootDirectory + File.separator + "static" + File.separator + "images" + File.separator + "classify";
        // 创建文件名
        String originalFileName = file.getOriginalFilename();
        int dotIndex = originalFileName.lastIndexOf('.');
        if (dotIndex > 0) {
            // 去掉扩展名
            originalFileName = originalFileName.substring(0, dotIndex);
        }
        String fileName = originalFileName + ".jpg";
        String filePath = uploadDirectory + File.separator + fileName;
        System.out.println(filePath);

        try {
            // 创建目录（如果不存在）
            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 将文件保存到指定路径
            File dest = new File(filePath);
            file.transferTo(dest);
            System.out.println("success!");

            // 返回 JSON 响应
            String jsonResponse = String.format("{\"status\": \"success\", \"path\": \"/images/classify/%s\"}", fileName);
            return ResponseEntity.ok(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
            // 返回错误 JSON 响应
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"status\": \"error\"}");
        }
    }

//    @PostMapping("/upload2")
//    public String handleFileUpload2(@RequestParam("file") MultipartFile file) {
//        System.out.println("upload2");
//        // 获取项目的根目录
//        String rootDirectory = System.getProperty("user.dir");
//        // 定义上传路径
//        String uploadDirectory = rootDirectory + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator + "classify";
//
//        // 创建文件名，使用用户ID作为前缀
//        String originalFileName = file.getOriginalFilename();
//        int dotIndex = originalFileName.lastIndexOf('.');
//        if (dotIndex > 0) {
//            // 去掉扩展名
//            originalFileName = originalFileName.substring(0, dotIndex);
//        }
//        String fileName = originalFileName +  ".jpg";
//
//        String filePath = uploadDirectory + File.separator + fileName;
//        System.out.println(filePath);
//        try {
//            // 创建目录（如果不存在）
//            File directory = new File(uploadDirectory);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//
//            // 将文件保存到指定路径
//            File dest = new File(filePath);
//            file.transferTo(dest);
//            System.out.println("success！");
//            return "{\"status\": \"success\", \"path\": \"" + filePath + "\"}";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "{\"status\": \"error\"}";
//        }
//    }


//    @PostMapping("/upload")
//    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
//        String rootDirectory = System.getProperty("user.dir");
//        String uploadDirectory = rootDirectory + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator + "profiles";
//        String fileName = file.getOriginalFilename();
//        String filePath = uploadDirectory + File.separator + fileName;
//
//
//        try {
//            // 将文件保存到指定路径
//            File dest = new File(filePath);
//            file.transferTo(dest);
//
//            // 在数据库中保存路径
//            // 使用JDBC或JPA将filePath保存到数据库中
//            saveFilePathToDatabase(filePath);
//
//            return "{\"status\": \"success\", \"path\": \"" + filePath + "\"}";
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "{\"status\": \"error\"}";
//        }
//    }

    private void saveFilePathToDatabase(Long userid,String filePath) {
        // 这里添加保存路径到数据库的逻辑
        // 更新用户的头像路径
        User user = userService.getUserById(userid); // 假设你有一个 userService 来获取用户对象
        if (user != null) {
            user.setUserProfilePath(filePath);
            userService.updateById(user); // 更新用户对象
            log.info("logTag::saveFilePathToDatabase,filePath={},修改成功",filePath);
        }
    }


    @PostMapping("/upload3")
    public Result<String> handleFileUpload3(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        // 获取项目根目录
        String rootDirectory = System.getProperty("user.dir");

        // 定义项目外部的上传路径
        String uploadDirectory = rootDirectory + File.separator + "static" + File.separator + "images" + File.separator + "profiles";
        // 创建文件名，使用用户ID作为前缀
        String originalFileName = file.getOriginalFilename();
        String fileName = userId + ".jpg";

        // 定义文件的完整路径
        String filePath = uploadDirectory + File.separator + fileName;
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

            // 在数据库中保存相对路径 (fileName 或 filePath)
            saveFilePathToDatabase(userId, "/profiles/" + fileName);

            // 返回文件路径给客户端
           return Result.success(filePath);
        } catch (IOException e) {
            e.printStackTrace();
           return Result.error("上传图片失败");
        }
    }
}
