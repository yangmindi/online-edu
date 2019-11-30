package com.guli.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-11-29
 */
public interface TeacherService extends IService<Teacher> {

    //条件查询带分页
    void pageListCondition(Page<Teacher> pageTeacher, TeacherQuery teacherQuery);
}
