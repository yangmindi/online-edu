package com.guli.edu.service;

import com.guli.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.dto.CourseInfoDto;
import com.guli.edu.entity.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
public interface CourseService extends IService<Course> {

    String insertCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getIdCourse(String id);

    Boolean updateCourse(CourseInfoForm courseInfoForm);

    boolean removeCourseId(String id);

    CourseInfoDto getCourseInfoAll(String courseId);
}
