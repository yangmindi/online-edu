package com.guli.edu.service;

import com.guli.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
