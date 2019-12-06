package com.guli.edu.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.guli.common.vo.R;
import com.guli.edu.handler.ConstantPropertiesUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/eduservice/oss")
@CrossOrigin
public class FileUploadController {

    //上传讲师头像的方法
    @PostMapping("upload")
    //@RequestParam("file")和表单中的name值相同
    public R uploadTeacherImg(@RequestParam("file") MultipartFile file) throws IOException {//获取上传的文件

        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtil.ENDPOINT;
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtil.KEYID;
        String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;
        String bucketName = ConstantPropertiesUtil.BUCKETNAME;
        //1.获取上传的文件
        //2.获取文件名称，上传文件输入流
        String originalFilename = file.getOriginalFilename();
        InputStream in = file.getInputStream();


        //3.存储到OOS
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        ossClient.putObject(bucketName, originalFilename, in);

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回OSS的存储路径
        //https://youee.oss-cn-beijing.aliyuncs.com/timg%20%287%29.jpg
        String path = "https://" + bucketName + "." + endpoint + "/" + originalFilename;
        return R.ok().data("imgurl", path);
    }
}
