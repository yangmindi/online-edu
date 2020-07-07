package com.online.edu.vidservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {
    String uploadVideoAlyun(MultipartFile file);
    void removeVideoList(List<String> videoIdList);
    void removeVideo(String videoId);
}
