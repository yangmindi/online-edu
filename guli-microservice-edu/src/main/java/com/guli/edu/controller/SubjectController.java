package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
public class SubjectController {
    @Autowired
    private SubjectService service;
    //通过excel文件获取文件内容
    @PostMapping("import")
    public R importExcelSubject(@RequestParam("file") MultipartFile file){
        List<String> strings = service.importSubject(file);
        return R.ok().data("msgList",strings);
    }
}

