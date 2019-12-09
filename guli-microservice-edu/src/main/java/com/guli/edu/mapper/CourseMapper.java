package com.guli.edu.mapper;

import com.guli.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.edu.entity.dto.CourseInfoDto;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
public interface CourseMapper extends BaseMapper<Course> {

    //根据课程id查询课程详细信息
    CourseInfoDto getCourseInfoAll(String courseId);

}
