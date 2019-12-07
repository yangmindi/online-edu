package com.guli.edu.service.impl;

import com.guli.edu.entity.Course;
import com.guli.edu.entity.CourseDescription;
import com.guli.edu.entity.form.CourseInfoForm;
import com.guli.edu.handler.GuliException;
import com.guli.edu.mapper.CourseMapper;
import com.guli.edu.service.CourseDescriptionService;
import com.guli.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.bcel.internal.generic.ConstantPushInstruction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Override
    public String insertCourseInfo(CourseInfoForm courseInfoForm) {
        //1、课程的基本信息
        //courseInfoForm复制到Course中
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm,course);
        int result = baseMapper.insert(course);
        //判断如果添加成功，添加描述
        if(result <= 0){//失败
            //抛出异常
            throw new GuliException(20001,"添加课程失败");
        }
        //2 课程描述添加到描述表
        CourseDescription courseDescription = new CourseDescription();


        //获取描述信息
        String description = courseInfoForm.getDescription();
        courseDescription.setDescription(description);
        courseDescription.setId(course.getId());

        boolean save = courseDescriptionService.save(courseDescription);

        if(save){
            return course.getId();
        }else {
            return null;
        }
    }
}
