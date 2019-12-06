package com.guli.edu.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//服务器启动是读取配置文件中的内容
@Component//实例化
public class ConstantPropertiesUtil implements InitializingBean {

    //服务器启动的时候调用afterPropertiesSet读取配置文件(application.properties)内容
    //服务器启动时候，ConstantPropertiesUtil初始化，调用里面afterPropertiesSet读取配置文件内容
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyid;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    //定义常量，为了能够使用
    public static String ENDPOINT;
    public static String KEYID;
    public static String KEYSECRET;
    public static String BUCKETNAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        ENDPOINT = endpoint;
        KEYID = keyid;
        KEYSECRET = keySecret;
        BUCKETNAME = bucketName;
    }
}
