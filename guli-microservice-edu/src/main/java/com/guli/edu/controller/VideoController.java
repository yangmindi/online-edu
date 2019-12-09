package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.Video;
import com.guli.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-12-08
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService eduVideoService;

    //删除
    @DeleteMapping("{videoId}")
    public R deleteVideoId(@PathVariable String videoId){
        boolean flag = eduVideoService.removeVideo(videoId);
        return R.ok();
    }

    //修改查询
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody Video eduVideo){
        boolean b = eduVideoService.updateById(eduVideo);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id查询
    @GetMapping("{videoId}")
    public R getVideoId(@PathVariable String videoId){
        Video eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("eduVideo",eduVideo);
    }

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody Video video){
        boolean save = eduVideoService.save(video);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

