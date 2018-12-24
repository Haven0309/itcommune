package com.yuchai.itcommune.controller;


import com.yuchai.itcommune.service.ProjectUploadService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Haven
 * @since 2018-12-20
 */
@Api(tags = "1.6", description = "上传附件", value = "上传附件管理")
@RestController
@RequestMapping("/projectUpload")
public class ProjectUploadController {
    @Autowired
    private ProjectUploadService projectUploadService;

    @ApiOperation("删除附件")
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        boolean b = projectUploadService.removeById(id);
        if(!b){
            return ResultUtil.genFailResult("删除失败");
        }
        return ResultUtil.genSuccessResult("删除成功");
    }
}

