package com.guli.edu.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EduChapterDto {
    private String id;
    private String title;

    //一个章节里面有很多小节
    private List<EduVideoDto> children = new ArrayList<>();
}
