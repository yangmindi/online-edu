package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.dto.CourseInfoDto;
import com.guli.edu.entity.form.CourseInfoForm;
import com.guli.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

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

    //根据课程id查询课程详细信息
    @GetMapping("getAllCourseInfo/{courseId}")
    public R getAllCourseInfo(@PathVariable String courseId){
        CourseInfoDto courseInfoDto = eduCourseService.getCourseInfoAll(courseId);
        return R.ok().data("courseInfo",courseInfoDto);
    }

    //删除课程的方法
    @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(@PathVariable String id){
        boolean flag = eduCourseService.removeCourseId(id);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //课程链表
    //TODO
    @GetMapping("listCourse")
    public R  getCourseList(){
        List<Course> list = eduCourseService.list(null);
        return R.ok().data("items",list);
    }


    //修改课程方法
    @PostMapping("updateCourseInfo/{id}")
    public R updateCourseInfo(@PathVariable String id,@RequestBody CourseInfoForm courseInfoForm){
        Boolean flag = eduCourseService.updateCourse(courseInfoForm);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id查询课程信息
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id){
        CourseInfoForm courseInfoForm = eduCourseService.getIdCourse(id);
        return R.ok().data("courseInfoForm",courseInfoForm);
    }

    //1.添加课程信息
    @PostMapping
    public R addCourseInfo(@RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
        return R.ok().data("courseId",courseId);
    }
}

