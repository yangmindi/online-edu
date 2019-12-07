package com.guli.edu.service;

import com.guli.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
public interface SubjectService extends IService<Subject>{

    //读取excel内容
    void importSubject(MultipartFile file);
}
