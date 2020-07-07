package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.Course;
import com.guli.edu.entity.dto.CourseInfoDto;
import com.guli.edu.entity.form.CourseInfoForm;
import com.guli.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(description="课程管理")
@RequestMapping("/eduservice/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService eduCourseService;

    //最终发布课程的方法，修改课程状态
    @ApiOperation(value = "根据id发布课程")
    @GetMapping("publishCourse/{courseId}")
    public R publishCourse(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String courseId) {
        Course eduCourse = new Course();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        boolean b = eduCourseService.updateById(eduCourse);
        if (b) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据课程id查询课程详细信息
    @ApiOperation(value = "根据课程id查询课程详细信息")
    @GetMapping("getAllCourseInfo/{courseId}")
    public R getAllCourseInfo(@PathVariable String courseId) {
        CourseInfoDto courseInfoDto = eduCourseService.getCourseInfoAll(courseId);
        return R.ok().data("courseInfo", courseInfoDto);
    }

    //删除课程的方法
    @ApiOperation(value = "根据课程id删除课程")
    @DeleteMapping("deleteCourse/{id}")
    public R deleteCourse(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        boolean flag = eduCourseService.removeCourseId(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    //课程链表
    //TODO
    @ApiOperation(value = "获取课程列表")
    @GetMapping("listCourse")
    public R getCourseList() {
        List<Course> list = eduCourseService.list(null);
        return R.ok().data("items", list);
    }


    //修改课程方法
    @ApiOperation(value = "更新课程")
    @PostMapping("updateCourseInfo/{id}")
    public R updateCourseInfo(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {
        Boolean flag = eduCourseService.updateCourse(courseInfoForm);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据id查询课程信息
    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("getCourseInfo/{id}")
    public R getCourseInfo(@PathVariable String id) {
        CourseInfoForm courseInfoForm = eduCourseService.getIdCourse(id);
        return R.ok().data("courseInfoForm", courseInfoForm);
    }

    //1.添加课程信息
    @ApiOperation(value = "新增课程")
    @PostMapping
    public R addCourseInfo(
            @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm) {
        String courseId = eduCourseService.insertCourseInfo(courseInfoForm);
        return R.ok().data("courseId", courseId);
    }
}

