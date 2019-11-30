package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin //跨域
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //1.查询所有的讲师功能
    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R getAllTeacherList(){
        //调用service的方法
        List<Teacher> list = teacherService.list(null);

        return R.ok().data("items",list);
    }

    @DeleteMapping("{id}")
    public boolean removeById(@PathVariable String id){
        boolean b = teacherService.removeById(id);
        return b;
    }
}

