package com.guli.edu.service;

import com.guli.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.dto.EduChapterDto;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-08
 */
public interface ChapterService extends IService<Chapter> {

    //根据课程id删除章节
    void deleteChapterByCourseId(String id);

    //根据课程id查询章节和小节
    List<EduChapterDto> getChapterVideoListCourseId(String courseId);

    //如果章节下边有小节，就不删除
    boolean removeChapterId(String chapterId);
}
