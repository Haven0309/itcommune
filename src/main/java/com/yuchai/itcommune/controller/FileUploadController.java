package com.yuchai.itcommune.controller;

import com.yuchai.itcommune.entity.ProjectUpload;
import com.yuchai.itcommune.service.ProjectUploadService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Haven
 * @create 2018-11-28 14:00
 */
@Api(tags = "1.6", description = "上传附件", value = "上传附件管理")
@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Autowired
    private ProjectUploadService projectUploadService;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;


    @ApiOperation("处理文件上传")
    @PostMapping(value="/upload")
    public Result upload(@RequestParam("file") MultipartFile[] file, HttpServletRequest request) {
        if (null != file && file.length > 0) {
            List<ProjectUpload> list = new ArrayList<>();
            //遍历并保存文件
            for (MultipartFile files : file) {
                String contentType = files.getContentType();   //图片文件类型
                String fileOldName = files.getOriginalFilename();  //图片名字
                // 获取文件的后缀名,比如图片的jpeg,png
                String suffixName = fileOldName.substring(fileOldName.lastIndexOf("."));

                //文件存放路径
                //String filePath = "C:\\Users\\Haven\\upload\\";

                // 文件上传后的名称
                String fileName = UUID.randomUUID() + suffixName;
                //存放路径目录，检查目录是否存在
                String myFilePath = filePath + "upload//";
                File targetFile = new File(myFilePath);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                String filePathAndName = myFilePath + fileName;
                File dest = new File(filePathAndName);
                try {
                    files.transferTo(dest);
                    //保存附件信息到数据库
                    ProjectUpload projectUpload = saveFileToMysql(fileOldName, fileName);
                    list.add(projectUpload);
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                }
            }
            return ResultUtil.genSuccessResult(list);
        }
        return ResultUtil.genFailResult("附件不能为空");

    }



    private ProjectUpload saveFileToMysql(String fileOldName, String fileName) {
        ProjectUpload projectUpload = new ProjectUpload();
//        projectUpload.setProjectId(projectId);
        projectUpload.setFileName(fileName);
        projectUpload.setFileDir("/upload/"+fileName);
        projectUpload.setFileOldName(fileOldName);
//            projectUpload.setCreatedBy("");
        projectUpload.setCreatedTime(LocalDateTime.now());
        projectUploadService.saveOrUpdate(projectUpload);
        return projectUpload;
    }
}

