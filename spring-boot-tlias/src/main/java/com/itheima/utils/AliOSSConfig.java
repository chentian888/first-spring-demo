package com.itheima.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="aliyun.oss")
public class AliOSSConfig {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    // STS临时访问密钥AccessKey ID和AccessKey Secret。
    String accessKeyId = "LTAI5tHEWKHn5hSWB9wsrPLw";
    String accessKeySecret = "w4IIYmOKILme6v9aQtchbIunPMUySP";
    // 填写Bucket名称，例如examplebucket。
    String bucketName = "nbavipstar";
}
