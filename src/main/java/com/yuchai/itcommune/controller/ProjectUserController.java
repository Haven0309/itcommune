package com.yuchai.itcommune.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.Project;
import com.yuchai.itcommune.entity.ProjectUser;
import com.yuchai.itcommune.service.ProjectService;
import com.yuchai.itcommune.service.ProjectUserService;
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
 * @since 2018-09-14
 */
@Api(tags = "1.3", description = "用户项目管理", value = "用户的项目信息管理")
@CrossOrigin
@RestController
@RequestMapping("/projectUser")
public class ProjectUserController {
    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private ProjectService projectService;

    /**
     * 获取一个用户的结算数据
     * @param id
     * @return
     */
    @ApiOperation("获取一个用户的结算数据")
    @ApiImplicitParam(name="id",value = "用户工号",required = true)
    @GetMapping("/salary/user/{id}")
    public Result salaryAll(@PathVariable String id){
        List<ProjectUser> projectUsers = projectUserService.list(new QueryWrapper<ProjectUser>().eq("user_code", id));
        List<Project> projects = new ArrayList<>();
        for (ProjectUser projectUser : projectUsers){
            Project project = projectService.getById(projectUser.getProjectId());
            projects.add(project);
        }

        return ResultUtil.genSuccessResult(projects);
    }

    /**
     * 获取一个项目的结算数据
     * @param id
     * @return
     */
    @ApiOperation("获取一个项目的结算数据")
    @ApiImplicitParam(name="id",value = "项目id",required = true)
    @GetMapping("/salary/project/{id}")
    public Result salaryByprojectId(@PathVariable Integer id){
        List<ProjectUser> projects = projectUserService.list(new QueryWrapper<ProjectUser>().eq("project_id", id));
        return ResultUtil.genSuccessResult(projects);
    }

    /**
     * 创建用户结算数据
     *
     */
    @ApiOperation("创建更新用户结算数据")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody List<ProjectUser> projectUser){
        for (ProjectUser pu:projectUser) {
            boolean b = projectUserService.saveOrUpdate(pu);
            if (!b) {
                return ResultUtil.genFailResult("创建或更新失败");
            }
        }
        return ResultUtil.genSuccessResult("创建或更新成功");
    }
}

