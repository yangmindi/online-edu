package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.form.CourseInfoForm;
import com.guli.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService eduCourseService;

    //1.添加课程信息
    @PostMapping
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
        return R.ok().data("courseId",courseId);
    }
}

