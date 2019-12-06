package com.guli.edu.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

//服务器启动是读取配置文件中的内容
@Component//实例化
public class ConstantPropertiesUtil implements InitializingBean {

    //
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
