package com.online.edu.vidservice.controller;

import com.guli.common.vo.R;
import com.online.edu.vidservice.service.VidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/vidservice/vod")
@CrossOrigin
public class VidController {
    @Autowired
    private VidService vidService;

    //实现上传视频到阿里云服务器的方法
    @PostMapping("upload")
    public R uploadAliyunVideo(@RequestParam("file") MultipartFile file) {
        //调用方法实现视频上传，返回上传之后视频id
        String videoId = vidService.uploadVideoAlyun(file);
        return R.ok().data("videoId",videoId);
    }
}
