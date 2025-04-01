package com.example.java7_4;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

// 模拟 OSS 配置类
@Data
class OssConfig {
    private String bucketName;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    public OssConfig(String bucketName, String endpoint, String accessKeyId, String accessKeySecret) {
        this.bucketName = bucketName;
        this.endpoint = endpoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }
}

@Slf4j
public class TestOss {

//    @Test
//    public void testOss() {
//        // 初始化 OSS 配置
//        OssConfig ossConfig = new OssConfig();
//
//        // 创建 OSSClient 实例
//        OSS ossClient = new OSSClientBuilder().build(
//                ossConfig.getEndpoint(),
//                ossConfig.getAccessKeyId(),
//                ossConfig.getAccessKeySecret()
//        );
//
//        try {
//            // 使用相对路径指定本地图片文件
//            String localFilePath = "src/test/img/1.jpg";
//            // 上传到 OSS 后的文件名，可以根据需求修改
//            String ossFilePath = "oss_1.jpg";
//
//            // 打开文件输入流
//            File file = new File(localFilePath);
//            InputStream inputStream = new FileInputStream(file);
//
//            // 上传文件
//            ossClient.putObject(ossConfig.getBucketName(), ossFilePath, inputStream);
//
//            log.info("文件上传成功！");
//        } catch (Exception e) {
//            log.error("文件上传失败：", e);
//        } finally {
//            // 关闭 OSSClient
//            ossClient.shutdown();
//        }
//    }



//    @Test
//    public void testOss2() {
//        // 初始化 OSS 配置
//        OssConfig ossConfig = new OssConfig(
//
//        );
//
//        // 创建 OSSClient 实例
//        OSS ossClient = new OSSClientBuilder().build(
//                ossConfig.getEndpoint(),
//                ossConfig.getAccessKeyId(),
//                ossConfig.getAccessKeySecret()
//        );
//        try {
//            // 要下载的 OSS 文件名
//            String ossFilePath = "oss_1.jpg";
//            // 本地保存路径
//            String localFilePath = "src/test/img/downloaded"+ossFilePath;
//
//            // 创建本地保存文件对象
//            File localFile = new File(localFilePath);
//            // 确保本地保存目录存在
//            localFile.getParentFile().mkdirs();
//
//            // 下载文件
//            ossClient.getObject(new GetObjectRequest(ossConfig.getBucketName(), ossFilePath), localFile);
//
//            log.info("文件下载成功，保存到：" + localFilePath);
//        } catch (Exception e) {
//            log.error("文件下载失败：", e);
//        }
//    }
}