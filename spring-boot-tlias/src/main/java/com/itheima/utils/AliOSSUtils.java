package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Component
public class AliOSSUtils {

    @Autowired
    private AliOSSConfig aliOSSConfig;

    public String upload(MultipartFile multipartFile) throws Exception {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = aliOSSConfig.getEndpoint();
        // 填写Bucket名称，例如examplebucket。
        String bucketName = aliOSSConfig.getBucketName();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(aliOSSConfig.getEndpoint(), aliOSSConfig.getAccessKeyId(), aliOSSConfig.getAccessKeySecret());
        InputStream inputStream = multipartFile.getInputStream();
        String fileName = multipartFile.getOriginalFilename();
        Integer index = fileName.lastIndexOf(".");
        String newFileName = UUID.randomUUID().toString() + fileName.substring(index);
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(aliOSSConfig.getBucketName(), newFileName, inputStream);
        // 设置该属性可以返回response。如果不设置，则返回的response为空。
        putObjectRequest.setProcess("true");
        // 创建PutObject请求。
        PutObjectResult result = ossClient.putObject(putObjectRequest);
        String url = endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + newFileName;
        // 如果上传成功，则返回200。
        System.out.println(result.getResponse().getStatusCode());
        ossClient.shutdown();
        return url;
    }
}