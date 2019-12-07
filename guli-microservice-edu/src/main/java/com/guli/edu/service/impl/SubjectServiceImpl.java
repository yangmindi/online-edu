package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Subject;
import com.guli.edu.handler.GuliException;
import com.guli.edu.mapper.SubjectMapper;
import com.guli.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
