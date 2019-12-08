package com.guli.edu.service.impl;

import com.guli.edu.entity.CourseDescription;
import com.guli.edu.mapper.CourseDescriptionMapper;
import com.guli.edu.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

    //根据课程id删除课程描述
    @Override
    public void deleteDescriptionByCourseId(String id) {
        baseMapper.deleteById(id);
    }
}
