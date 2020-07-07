package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.Subject;
import com.guli.edu.entity.dto.OneSubjectDto;
import com.guli.edu.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
@Api(description="课程分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService service;

    //添加二级分类
    @ApiOperation(value = "新增二级分类")
    @PostMapping("addTwoLevel")
    public R addTwoLevel(
            @ApiParam(name = "subject", value = "课程分类对象", required = true)
            @RequestBody Subject subject){
        boolean flag = service.saveTwoLevel(subject);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //添加一级分类
    @ApiOperation(value = "新增一级分类")
    @PostMapping("addOneLevel")
    public R addOneLevel(
            @ApiParam(name = "subject", value = "课程分类对象", required = true)
            @RequestBody Subject subject){
        boolean flag = service.saveOneLevel(subject);
        if(flag){
            return R.ok();
        }else {
            return R.error();
        }
    }


    //3 删除一级分类
    @DeleteMapping("{id}")
    public R deleteSubjectId(@PathVariable String id) {
        boolean flag = service.deleteSubjectById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //2 返回所有分类数据，返回要求的json数据格式
    @GetMapping
    @ApiOperation(value = "得到所有分类数据")
    public R getAllSubjectList() {
        List<OneSubjectDto> list = service.getSubjectList();
        return R.ok().data("OneSubjectDto",list);
    }

    //通过excel文件获取文件内容
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public R importExcelSubject(
            @ApiParam(name = "file", value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file){
        List<String> strings = service.importSubject(file);

        if(strings.size() == 0){
            return R.ok();
        }else {
            return R.error().message("部分数据导入失败").data("msgList",strings);
        }
    }
}

