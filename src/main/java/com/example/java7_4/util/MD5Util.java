package com.example.java7_4.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    // 方法：生成MD5哈希值
    public static String md5(String input) {
        try {
            // 创建MessageDigest实例，用于MD5加密
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 更新MessageDigest的内容，传入字节数组
            md.update(input.getBytes());

            // 执行哈希计算，返回字节数组
            byte[] digest = md.digest();

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                // 将每个字节转换为两位的十六进制字符串
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // 如果十六进制字符串长度为1，前面补0
                }
                hexString.append(hex);
            }

            // 返回MD5哈希值的十六进制字符串
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 如果系统没有MD5算法时，打印异常
            e.printStackTrace();
            return null;
        }
    }

//    // 测试MD5Util类
//    public static void main(String[] args) {
//        String input = "password123";  // 测试输入字符串
//        String md5Hash = md5(input);  // 获取MD5哈希值
//        System.out.println("MD5哈希值（'password123'）: " + md5Hash);
//    }
}
