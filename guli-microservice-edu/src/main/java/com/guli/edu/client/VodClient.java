package com.guli.edu.client;

import com.guli.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)//调用的是guli-vod模块的服务，对应的名称是Eureka中显示的名字
@Component
public interface VodClient {
    //删除课时的同时删除云端视频
    @DeleteMapping(value = "/admin/vod/video/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);

    //删除课程的同时删除云端视频
    @DeleteMapping(value = "/admin/vod/video/delete-batch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}