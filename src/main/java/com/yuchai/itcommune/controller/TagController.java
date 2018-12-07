package com.yuchai.itcommune.controller;


import com.yuchai.itcommune.entity.Tag;
import com.yuchai.itcommune.service.TagService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Haven
 * @since 2018-10-08
 */
@Api(tags = "1.5", description = "用户标签", value = "更新用户标签管理")
@CrossOrigin
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;
    /**
     * 更新用户标签
     * @param tag
     * @return
     */
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Tag tag){
        boolean b = tagService.saveOrUpdate(tag);
        if (!b) {
            return ResultUtil.genFailResult("更新失败");
        }
        return ResultUtil.genSuccessResult();
    }
}

