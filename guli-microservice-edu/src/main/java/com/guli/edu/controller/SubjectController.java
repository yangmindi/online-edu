package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.dto.OneSubjectDto;
import com.guli.edu.service.SubjectService;
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
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService service;

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
    public R getAllSubjectList() {
        List<OneSubjectDto> list = service.getSubjectList();
        return R.ok().data("OneSubjectDto",list);
    }

    //通过excel文件获取文件内容
    @PostMapping("import")
    public R importExcelSubject(@RequestParam("file") MultipartFile file){
        List<String> strings = service.importSubject(file);

        if(strings.size() == 0){
            return R.ok();
        }else {
            return R.error().message("部分数据导入失败").data("msgList",strings);
        }
    }
}

