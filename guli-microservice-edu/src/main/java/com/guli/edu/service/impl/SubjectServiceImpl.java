package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Subject;
import com.guli.edu.entity.dto.OneSubjectDto;
import com.guli.edu.entity.dto.TwoSubjectDto;
import com.guli.edu.handler.GuliException;
import com.guli.edu.mapper.SubjectMapper;
import com.guli.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-12-07
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    //读取Excel内容，添加到分类表中
    @Override
    public List<String> importSubject(MultipartFile file) {
        try {
            //1.读取文件输入流
            InputStream inputStream = file.getInputStream();
            //2.创建workbook
            Workbook workbook = new HSSFWorkbook(inputStream);
            //3.根据workbook得到sheet
            Sheet sheet = workbook.getSheetAt(0);


            //如果行为空，提示错误信息
            List<String> msg = new ArrayList<>();
            //4.sheet-row
            //sheet.getRow(0);循环遍历
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    String str = "表格数据为空，请输入数据";
                    // TODO
                    msg.add(str);
                    continue;
                }
                //从第二行开始获取数据
                //5.row--cell
                Cell cellOne = row.getCell(0);
                //判断是否为空
                if (cellOne == null) {
                    String str = "第"+i+"行数据为空";
                    // TODO
                    msg.add(str);
                    continue;
                }
                //不为空,第一列中的值
                String cellOneValue = cellOne.getStringCellValue();

                //添加一级分类
                //excel有很多重复的一级分类
                //在添加之前先判断，添加的是否存在，存在就不添加
                Subject subjectExcist = this.existOneSubject(cellOneValue);
                //存储一级分类id
                String id_parentId = null;
                if(subjectExcist == null){//不存在相同的一级分类
                    //添加
                    Subject subject = new Subject();
                    subject.setTitle(cellOneValue);
                    subject.setParentId("0");
                    subject.setSort(0);
                    baseMapper.insert(subject);

                    id_parentId = subject.getId();
                }else {
                    //存在就不添加
                    //把一级分类的id赋值给id_parentId
                    id_parentId = subjectExcist.getId();
                }

                //5.第二列
                Cell cellTwo = row.getCell(1);
                if (cellTwo == null) {
                    String str = "第"+i+"行数据为空";
                    // TODO
                    msg.add(str);
                    continue;
                }
                String cellTwoValue = cellTwo.getStringCellValue();

                //添加二级分类
                //判断是否存在二级分类，存在就添加
                Subject twoSubjectExist = this.existTwoSubject(cellTwoValue, id_parentId);
                if (twoSubjectExist == null) {

                    Subject twosubject = new Subject();
                    twosubject.setTitle(cellTwoValue);
                    twosubject.setParentId(id_parentId);
                    twosubject.setSort(0);
                    baseMapper.insert(twosubject);
                }
            }
            return msg;
        } catch (Exception e) {
            throw new GuliException(20001,"导入失败出现异常");
        }
    }


    //删除分类
    @Override
    public boolean deleteSubjectById(String id) {
        //判断一级分类下面有二级分类
        //根据parent_id查询
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        //判断如果有二级分类
        if (count>0) {
            return false;
        } else {//没有二级分类
            //进行删除
            int result = baseMapper.deleteById(id);
            return result>0;
        }
    }

    @Override
    public List<OneSubjectDto> getSubjectList() {
        //1 查询所有一级分类
        QueryWrapper<Subject> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("parent_id","0");
        List<Subject> allOneSubjects = baseMapper.selectList(wrapper1);

        //2 查询所有二级分类
        QueryWrapper<Subject> wrapper2 = new QueryWrapper<>();
        wrapper2.ne("parent_id","0");
        List<Subject> allTwoSubjects = baseMapper.selectList(wrapper2);

        //创建list集合，用于存储所有一级分类
        List<OneSubjectDto> oneSubjectDtolist = new ArrayList<>();
        //3 首先构建一级分类
        //遍历所有的一级分类，得到每个EduSubject对象，把每个EduSubject对象转换OneSubjectDto
        for (int i = 0; i < allOneSubjects.size(); i++) {
            //获取每个EduSubject对象
            Subject eduOneSubject = allOneSubjects.get(i);
            //创建OneSubjectDto对象
            OneSubjectDto oneSubjectDto = new OneSubjectDto();
            //把每个EduSubject对象转换OneSubjectDto
            BeanUtils.copyProperties(eduOneSubject,oneSubjectDto);
            //把dto对象放到list集合
            oneSubjectDtolist.add(oneSubjectDto);

            //获取一级分类所有二级分类，List<TwoSubjectDto>
            //把所有的二级分类添加到每个一级分类对象中oneSubjectDto.setChildren(list);
            //创建list集合，用于存储二级分类
            List<TwoSubjectDto>  twoSubjectDtoList = new ArrayList<>();
            //遍历所有的二级分类，得到每个二级分类
            for (int m = 0; m < allTwoSubjects.size(); m++) {
                //得到每个二级分类
                Subject eduTwoSubject = allTwoSubjects.get(m);
                //判断一级分类id和二级分类parentid是否一样
                if(eduTwoSubject.getParentId().equals(eduOneSubject.getId())) {
                    //二级分类转换TwoSubjectDto
                    TwoSubjectDto twoSubjectDto = new TwoSubjectDto();
                    //内省  反射
                    BeanUtils.copyProperties(eduTwoSubject,twoSubjectDto);
                    //放到list集合
                    twoSubjectDtoList.add(twoSubjectDto);
                }
            }
            //把二级分类放到每个一级分类中
            oneSubjectDto.setChildren(twoSubjectDtoList);
        }
        return oneSubjectDtolist;
    }

    //判断数据库表是否存在二级分类
    private Subject existTwoSubject(String name,String parentId){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parentId);

        Subject subject = baseMapper.selectOne(wrapper);
        return subject;
    }

    //判断数据库表是否存在一级分类
    private Subject existOneSubject(String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        //拼接条件
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        //调用方法
        Subject subject = baseMapper.selectOne(wrapper);
        return subject;
    }
}
