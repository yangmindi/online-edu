package com.guli.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.common.vo.R;
import com.guli.edu.entity.Teacher;
import com.guli.edu.entity.query.TeacherQuery;
import com.guli.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/admin/edu/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //模拟登陆
    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //获取用户信息
    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    //7.根据id修改的方法
    @ApiOperation(value = "根据ID修改讲师")
    @PostMapping("updateTeacher/{id}")
    public R updateTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher){

        teacher.setId(id);
        boolean b = teacherService.updateById(teacher);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //6.根据id查询讲师
    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("getTeacherInfo/{id}")
    public R getTeacherInfo(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("eduTeacher", teacher);
    }

    //5.添加讲师的方法
    @ApiOperation(value = "新增讲师")
    @PostMapping("addTeacher")
    public R addTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //4.多条件组合查询带分页
    @PostMapping("moreConditionPageList/{page}/{limit}")
    public R getMoreConditionPageList(@PathVariable Long page,
                                      @PathVariable Long limit,
                                      @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<Teacher> pageTeacher = new Page<>(page, limit);

        //调用service的方法实现条件查询带分页
        teacherService.pageListCondition(pageTeacher, teacherQuery);
        List<Teacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();

        return R.ok().data("total", total).data("items", records);
    }

    //3.分页查询讲师列表的方法
    @GetMapping("pageList/{page}/{limit}")
    public R pageList(@PathVariable Long page,
                      @PathVariable Long limit) {
        Page<Teacher> pageParm = new Page<>(page, limit);
        teacherService.page(pageParm, null);
        List<Teacher> records = pageParm.getRecords();
        long total = pageParm.getTotal();

        return R.ok().data("total", total).data("items", records);
    }

    //1.查询所有的讲师功能
    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R getAllTeacherList() {
        //调用service的方法
        List<Teacher> list = teacherService.list(null);

        return R.ok().data("items", list);
    }


    //2 逻辑删除讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R deleteTeacherById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
}

