package com.yuchai.itcommune.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.ProcessHistory;
import com.yuchai.itcommune.service.ProcessHistoryService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Haven
 * @since 2018-11-22
 */
@Api(tags = "1.1", description = "流程历史管理", value = "流程历史管理")
@RestController
@RequestMapping("/processHistory")
public class ProcessHistoryController {
    @Autowired
    private ProcessHistoryService processHistoryService;

    @ApiOperation("创建更新审批历史记录")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody ProcessHistory processHistory){
        boolean b = processHistoryService.saveOrUpdate(processHistory);
        if (!b) {
            return ResultUtil.genFailResult("数据库插入流程历史失败");
        }
        return ResultUtil.genSuccessResult();
    }

    @ApiOperation("获取流程历史记录")
    @ApiImplicitParam(name="instanceId",value = "流程实例ID")
    @GetMapping("/getHistory/{instanceId}")
    public Result getHistory(@PathVariable String instanceId){
        List<ProcessHistory> list = processHistoryService.list(new QueryWrapper<ProcessHistory>().eq("instance_id", instanceId));
        return ResultUtil.genSuccessResult(list);
    }
}

