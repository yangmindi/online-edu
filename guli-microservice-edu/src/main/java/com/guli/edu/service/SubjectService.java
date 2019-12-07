package com.guli.edu.service;

import com.guli.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.dto.OneSubjectDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
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
    List<String> importSubject(MultipartFile file);

    //返回所有分类
    List<OneSubjectDto> getSubjectList();

    boolean deleteSubjectById(String id);
}
