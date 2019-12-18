package com.online.edu.vidservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface VidService {
    String uploadVideoAlyun(MultipartFile file);
}
