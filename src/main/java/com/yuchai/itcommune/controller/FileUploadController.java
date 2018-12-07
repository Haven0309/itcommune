package com.yuchai.itcommune.controller;

import com.yuchai.itcommune.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author Haven
 * @create 2018-11-28 14:00
 */
@Api(tags = "1.6", description = "上传附件", value = "上传附件管理")
@CrossOrigin
@Controller
@RequestMapping("/file")
public class FileUploadController {
    @ApiOperation("测试上传地址")
    @RequestMapping("/hello")
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "/upload";
    }

    //处理文件上传
    @ApiOperation("处理文件上传")
    @PostMapping(value="/upload")
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String contentType = file.getContentType();   //图片文件类型
        String fileName = file.getOriginalFilename();  //图片名字
        //文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath("upload/");
//        String filePath = "C:\\Users\\Administrator\\Desktop\\vue-manage-system-master\\static\\upload\\";

        //调用文件处理类FileUtil，处理文件，将文件写入指定位置
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        // 返回图片的存放路径
        return filePath;
    }

}

