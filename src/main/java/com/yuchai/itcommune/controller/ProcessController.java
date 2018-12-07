package com.yuchai.itcommune.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.battcn.swagger.properties.ApiParamType;
import com.yuchai.itcommune.entity.ActRuTask;
import com.yuchai.itcommune.entity.Process;
import com.yuchai.itcommune.service.ActRuTaskService;
import com.yuchai.itcommune.service.ProcessService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Haven
 * @since 2018-11-23
 */
@Api(tags = "1.1", description = "流程信息管理", value = "待办等流程管理")
@CrossOrigin
@RestController
@RequestMapping("/processInfo")
public class ProcessController {
    @Autowired
    private ProcessService processService;
    @Autowired
    private ActRuTaskService actRuTaskService;

    @ApiOperation("保存更新流程信息到数据库")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody Process process){
        boolean b = processService.saveOrUpdate(process);
        if (!b) {
            return ResultUtil.genFailResult("数据库插入流程信息失败");
        }
        return ResultUtil.genSuccessResult();
    }

    /**
     * 获取统一待办列表
     */
    @ApiOperation("获取统一待办列表")
    @ApiImplicitParam(name="assigneeCode",value = "用户工号",paramType = ApiParamType.PATH,required = true)
    @GetMapping("/todoList/{assigneeCode}")
    public Result todoList(@PathVariable String assigneeCode){
        List<ActRuTask> actRuTasks = actRuTaskService.list(new QueryWrapper<ActRuTask>().eq("ASSIGNEE_", assigneeCode));
//        List<Process> list = processService.list(new QueryWrapper<Process>().eq("assignee_code", assigneeCode).ne("current_node","结算"));
        List<Process> processList = new ArrayList<>();
        if (actRuTasks.size() > 0) {
            for (ActRuTask task : actRuTasks) {
//                Task task = taskService.createTaskQuery().processInstanceId(process.getInstanceId()).singleResult();
//                if (task != null) {
//                    process.setTaskId(task.getId());
//                }
                Process process = processService.getOne(new QueryWrapper<Process>().eq("instance_id", task.getProcInstId()));
                if (process != null) {
                    process.setTaskId(task.getId());
                    process.setCurrentNodeId(task.getTaskDefKey());
                    process.setCurrentNode(task.getName());

                }
                processList.add(process);
            }
        }

        return ResultUtil.genSuccessResult(processList);
    }
}

