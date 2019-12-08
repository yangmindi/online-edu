package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Chapter;
import com.guli.edu.mapper.ChapterMapper;
import com.guli.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-12-08
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    //根据课程id删除章节
    @Override
    public void deleteChapterByCourseId(String id) {
        QueryWrapper<Chapter> wapper = new QueryWrapper<>();
        wapper.eq("course_id",id);
        baseMapper.delete(wapper);
    }
}
