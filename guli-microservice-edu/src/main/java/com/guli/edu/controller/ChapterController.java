package com.guli.edu.controller;


import com.guli.common.vo.R;
import com.guli.edu.entity.Chapter;
import com.guli.edu.entity.dto.EduChapterDto;
import com.guli.edu.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //3.修改章节的方法
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody Chapter chapter){
        boolean b = chapterService.updateById(chapter);
        if(b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //3.根据章节id进行查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        Chapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("eduChapter",eduChapter);

    }


    //2.添加章节方法
    @PostMapping("addChapter")
    public R addChapter(@RequestBody Chapter eduChapter){
        boolean save = chapterService.save(eduChapter);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //1.根据课程id查询章节和小节
    @GetMapping("getChapterVideoList/{courseId}")
    public R getChapterVideoListCourseId(@PathVariable String courseId){
        List<EduChapterDto> list = chapterService.getChapterVideoListCourseId(courseId);

        return R.ok().data("items",list);
    }

}

