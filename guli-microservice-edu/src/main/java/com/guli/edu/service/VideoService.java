package com.guli.edu.service;

import com.guli.edu.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.form.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-08
 */
public interface VideoService extends IService<Video> {

    ///根据课程id删除小节
    void deleteVideoByCourseId(String id);

    //删除小节
    boolean removeVideo(String videoId);

    //添加小结
    boolean saveVideoInfo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String videoId);

    boolean updateVideoInfoById(VideoInfoForm videoInfoForm);

}
