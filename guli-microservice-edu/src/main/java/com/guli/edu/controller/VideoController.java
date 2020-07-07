package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.Video;
import com.guli.edu.entity.form.VideoInfoForm;
import com.guli.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description="课时管理")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService eduVideoService;

    //删除
    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{videoId}")
    public R deleteVideoId(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String videoId){
        boolean result = eduVideoService.removeVideo(videoId);
        if(result){
            return R.ok();
        }else{
            return R.error().message("删除失败");
        }
    }

    //修改查询
    @ApiOperation(value = "更新课时")
    @PostMapping("updateVideo/{videoId}")
    public R updateVideo(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String videoId,
            @ApiParam(name = "VideoInfoForm", value = "课时基本信息", required = true)
            @RequestBody VideoInfoForm videoInfoForm){
        boolean b = eduVideoService.updateVideoInfoById(videoInfoForm);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id查询
    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("{videoId}")
    public R getVideoId(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String videoId){
        VideoInfoForm videoInfoForm = eduVideoService.getVideoInfoFormById(videoId);
        return R.ok().data("eduVideo",videoInfoForm);
    }

    //添加小节
    @ApiOperation(value = "新增课时")
    @PostMapping("addVideo")
    public R addVideo(
            @ApiParam(name = "videoForm", value = "课时对象", required = true)
            @RequestBody VideoInfoForm videoInfoForm){
        boolean save = eduVideoService.saveVideoInfo(videoInfoForm);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

