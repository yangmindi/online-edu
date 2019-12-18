package com.online.edu.vidservice.service.impl;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.online.edu.vidservice.service.VidService;
import com.online.edu.vidservice.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VidServiceImpl implements VidService {

    @Override
    public String uploadVideoAlyun(MultipartFile file) {
        try {
            //获取上传文件名称
            //视频名称.mp4
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0,fileName.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET,
                    title, fileName, file.getInputStream());
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        }catch(Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String str = "视频名称1111.mp4";
        String name = str.substring(0,str.lastIndexOf("."));
        System.out.println(name);
    }
}

