package com.yuchai.itcommune.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Haven
 * @create 2018-11-28 14:32
 */
public class FileUtil {
    //文件上传工具类服务方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
