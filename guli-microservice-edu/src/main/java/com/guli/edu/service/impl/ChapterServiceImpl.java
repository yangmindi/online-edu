package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.entity.Chapter;
import com.guli.edu.entity.Video;
import com.guli.edu.entity.dto.EduChapterDto;
import com.guli.edu.entity.dto.EduVideoDto;
import com.guli.edu.mapper.ChapterMapper;
import com.guli.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private VideoService videoService;

    //根据课程id删除章节
    @Override
    public void deleteChapterByCourseId(String id) {
        QueryWrapper<Chapter> wapper = new QueryWrapper<>();
        wapper.eq("course_id",id);
        baseMapper.delete(wapper);
    }

    @Override
    public List<EduChapterDto> getChapterVideoListCourseId(String courseId) {

        //1.根据课程id查询章节
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<Chapter> eduChapters = baseMapper.selectList(wrapper);

        //2.根据课程id查小节部分
        QueryWrapper<Video> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<Video> eduVideos = videoService.list(wrapperVideo);

        //用于存储章节和小节的数据
        List<EduChapterDto> chapterDtoList = new ArrayList<>();

        //3.遍历课程的所有章节，复制值到DTO对象
        for (int i = 0; i < eduChapters.size(); i++) {
            //获取每个章节
            Chapter chapter = eduChapters.get(i);
            //复制值到dto对象
            EduChapterDto eduChapterDto = new EduChapterDto();
            BeanUtils.copyProperties(chapter,eduChapterDto);


            //集合，用于存储所有小节数据
            List<EduVideoDto> eduVideoDtoList = new ArrayList<>();

            //构建小节数据
            //4.遍历小节
            for (int m = 0; m < eduVideos.size(); m++) {
                //获取每个小节
                Video video = eduVideos.get(m);
                //判断小节的chapterid和章节id是否一样
                if(video.getChapterId().equals(chapter.getId())){
                    //转换dto对象
                    EduVideoDto eduVideoDto = new EduVideoDto();
                    BeanUtils.copyProperties(video,eduVideoDto);
                    //dto对象放到集合中去
                    eduVideoDtoList.add(eduVideoDto);

                }
            }
            //把小节最终放到每个章节里面
            eduChapterDto.setChildren(eduVideoDtoList);


            //dto对象放到list集合里面
            chapterDtoList.add(eduChapterDto);
        }

        //返回集合
        return chapterDtoList;
    }
}
