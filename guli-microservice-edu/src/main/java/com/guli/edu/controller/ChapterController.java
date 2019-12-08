package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.dto.EduChapterDto;
import com.guli.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Helen
 * @since 2019-12-08
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //1.根据课程id查询章节和小节
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoListCourseId(@PathVariable String courseId){
        List<EduChapterDto> list = chapterService.getChapterVideoListCourseId(courseId);

        return R.ok().data("items",list);
    }

}

