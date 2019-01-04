package com.yuchai.itcommune.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.BpmVirtualTodoList;
import com.yuchai.itcommune.service.BpmVirtualTodoListService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Haven
 * @since 2018-12-06
 */
@Api(tags = "1.8", description = "玉柴云虚拟待办", value = "玉柴云虚拟待办")
@CrossOrigin
@RestController
@RequestMapping("/bpmVirtualTodoList")
public class BpmVirtualTodoListController {
    @Autowired
    private BpmVirtualTodoListService bpmVirtualTodoListService;

    @ApiOperation("创建更新玉柴云虚拟待办")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody BpmVirtualTodoList bpmVirtualTodoList){
        bpmVirtualTodoList.setCreateTime(LocalDateTime.now());
        bpmVirtualTodoList.setAssigneddate(LocalDateTime.now());
        List<BpmVirtualTodoList> list = bpmVirtualTodoListService.list(new QueryWrapper<BpmVirtualTodoList>().eq("INSTANCE_ID", bpmVirtualTodoList.getInstanceId()).eq("ASSIGNEE", bpmVirtualTodoList.getAssignee()));
        if(list.size()>=1){
            return ResultUtil.genFailResult("该待办已经存在");
        }
        boolean b = bpmVirtualTodoListService.saveOrUpdate(bpmVirtualTodoList);
        if (!b) {
            return ResultUtil.genFailResult("添加BPM待办失败");
        }
        return ResultUtil.genSuccessResult(bpmVirtualTodoList);
    }

    @ApiOperation("删除玉柴云虚拟待办")
    @GetMapping("/delete")
    public Result delete(@RequestParam String instanceId){
        boolean b = bpmVirtualTodoListService.remove(new QueryWrapper<BpmVirtualTodoList>().eq("INSTANCE_ID",instanceId));
        if (!b) {
            return ResultUtil.genFailResult("删除失败");
        }
        return ResultUtil.genSuccessResult("删除成功");
    }
}

