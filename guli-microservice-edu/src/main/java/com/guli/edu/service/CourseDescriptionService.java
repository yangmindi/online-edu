package com.guli.edu.service;

import com.guli.edu.entity.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
public interface CourseDescriptionService extends IService<CourseDescription> {

    //根据课程id删除描述
    void deleteDescriptionByCourseId(String id);
}
