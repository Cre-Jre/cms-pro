package com.cms.portal.controller.admin;

import com.cms.contex.foundation.Result;
import com.cms.contex.utils.UtilsProperties;
import com.cms.contex.utils.UtilsServletContext;
import com.cms.contex.utils.UtilsString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.sound.sampled.Line;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UtilsProperties utilsProperties;

    /**
     *  tomcat虚拟路径 这个需要我自己配置
     */
    private String tomcatDir="/temp";

    @PostMapping("file.do")
    public Result<String> upload(@RequestParam("file") CommonsMultipartFile file){
        String originalFilename = file.getOriginalFilename();
        // originalFilename=abc.jpg
        String suffix = StringUtils.substring(originalFilename, StringUtils.lastIndexOf(originalFilename, "."));
        String uploadPath = utilsProperties.getPropertiesValue("upload.dir");
        String tmpDir = StringUtils.isNotEmpty(uploadPath) ? uploadPath : System.getProperty("java.io.tmpdir");
        String fileName;
        if(!UtilsServletContext.dirExist(tmpDir)){
            return Result.failed("文件上传目录不存在");
        }
        try {
            fileName= UtilsString.uuid() +suffix;
            File filePath = new File(tmpDir, fileName);
            file.transferTo(filePath);
        } catch (IOException e) {
            log.error("文件上传失败=[{}]",e.getMessage());
            return Result.failed("上传文件失败,请重新上传");
        }
        return Result.success(200,tomcatDir+"/"+fileName,tomcatDir+"/"+fileName);
    }
}
