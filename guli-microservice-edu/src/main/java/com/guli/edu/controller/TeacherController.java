package com.guli.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.entity.query.TeacherQuery;
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

    //4.多条件组合查询带分页
    @PostMapping("moreCondition/{page}/{limit}")
    public R getMoreConditionPageList(@PathVariable Long page,
                                      @PathVariable Long limit,
                                      @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<Teacher> pageTeacher = new Page<>(page,limit);

        //调用service的方法实现条件查询带分页
        teacherService.pageListCondition(pageTeacher,teacherQuery);
        List<Teacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();

        return R.ok().data("total",total).data("items",records);
    }

    //3.分页查询讲师列表的方法
    @GetMapping("{page}/{limit}")
    public R pageList(@PathVariable Long page,
                      @PathVariable Long limit){
        Page<Teacher> pageParm = new Page<>(page,limit);
        teacherService.page(pageParm,null);
        List<Teacher> records = pageParm.getRecords();
        long total = pageParm.getTotal();

        return R.ok().data("total",total).data("items",records);
    }

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

