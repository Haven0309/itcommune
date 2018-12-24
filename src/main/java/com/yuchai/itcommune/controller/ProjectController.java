package com.yuchai.itcommune.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuchai.itcommune.entity.Project;
import com.yuchai.itcommune.entity.TeamUser;
import com.yuchai.itcommune.entity.Teams;
import com.yuchai.itcommune.service.ProjectService;
import com.yuchai.itcommune.service.TeamUserService;
import com.yuchai.itcommune.service.TeamsService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import com.yuchai.itcommune.vo.ProjectVO;
import com.yuchai.itcommune.vo.TeamsVO;
import com.yuchai.itcommune.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Haven
 * @since 2018-09-14
 */
@Api(tags = "1.3", description = "项目管理", value = "项目信息管理")
@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamsService teamsService;
    @Autowired
    private TeamUserService teamUserService;

    /**
     * 创建项目
     *
     * @param project
     * @return
     */
    @ApiOperation("创建项目")
    @PostMapping("/addOrUpdate")
    public Result saveOrUpdate(@RequestBody Project project) {
//        Project project = new Project();
//        BeanUtils.copyProperties(projectVO,project);
        project.setCreatedDate(LocalDateTime.now());
        project.setProjectType("S");
        projectService.saveOrUpdate(project);
//        BeanUtils.copyProperties(project,projectVO);
        return ResultUtil.genSuccessResult(project);
    }

//    /**
//     * 更新项目
//     *
//     * @param project
//     * @return
//     */
//    @PostMapping("/update")
//    public boolean update(@RequestBody Project project) {
//        return projectService.updateById(project);
//
//    }

    /**
     * 获取单个项目信息
     *
     * @param id
     * @return
     */
    @ApiOperation("获取单个项目信息")
    @ApiImplicitParam(name="id",value = "项目主键id",required = true)
    @GetMapping("/get/{id}")
    public ProjectVO get(@PathVariable Integer id) {
        ProjectVO projectVO = new ProjectVO();
        BeanUtils.copyProperties(projectService.getById(id),projectVO);
        List<Teams> teams = teamsService.list(new QueryWrapper<Teams>().eq("project_id", id));
        List<TeamsVO> teamsVOS = new ArrayList<>();
        foreachTeams(teams, teamsVOS);
        projectVO.setTeams(teamsVOS);
        return projectVO;
    }

    /**
     * 分页查询全部项目
     *
     * @param pageInt  页码
     * @param pageSize 数量
     * @return
     */
    @ApiOperation("分页查询全部项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageInt",value = "第几页",required = true),
            @ApiImplicitParam(name="pageSize",value = "每页数量",required = true)
    })
    @PostMapping("/list/{pageInt}/{pageSize}")
    public Result list(@PathVariable Integer pageInt,
                       @PathVariable Integer pageSize, @RequestBody(required = false) Map<String,String> map) {
        Page<Project> page = new Page<>(pageInt, pageSize);
        IPage<Project> projectIPage = null;
        if (map != null) {
            String projectName = map.get("projectName");
            String currentStep = map.get("currentStep");
            String createdDateStart = map.get("createdDateStart");
            String createdDateEnd = map.get("createdDateEnd");
            if (projectName == null) {
                projectName = "";
            }
            String[] status = new String[2];
            if (currentStep == null) {
                status[0] = "进行中";
                status[1] = "完成";
            }else {
                status[0]=currentStep;
            }
            if (createdDateStart == null) {
                createdDateStart = "2018-01-01";
                createdDateEnd = "3018-01-01";
            }
            projectIPage = projectService.page(page, new QueryWrapper<Project>()
                    .in("status", (Object[]) status)
                    .like("project_name",projectName)
                    .between("expiration_date",createdDateStart,createdDateEnd));
        }else {
            projectIPage = projectService.page(page, new QueryWrapper<Project>().in("status","进行中","完成"));
        }


        List<Project> projects = projectIPage.getRecords();
        List<ProjectVO> projectVOList = new ArrayList<>();
        for (Project project : projects) {
            ProjectVO projectVO = new ProjectVO();
            BeanUtils.copyProperties(project,projectVO);
            List<Teams> teams = teamsService.list(new QueryWrapper<Teams>().eq("project_id", project.getId()));
            List<TeamsVO> teamsVOS = new ArrayList<>();
            foreachTeams(teams, teamsVOS);
            projectVO.setTeams(teamsVOS);
            projectVOList.add(projectVO);
        }

        return ResultUtil.genSuccessResult(projectVOList);
    }

    private void foreachTeams(List<Teams> teams, List<TeamsVO> teamsVOS) {
        for (Teams team : teams) {
            TeamsVO teamsVO = new TeamsVO();
            BeanUtils.copyProperties(team,teamsVO);

            List<UserVO> userVOS = new ArrayList<>();
            List<TeamUser> teamUsers = teamUserService.list(new QueryWrapper<TeamUser>().eq("team_id", team.getId()));
//            for (TeamUser teamUser : teamUsers) {
//                User user = userService.getOne(new QueryWrapper<User>().eq("user_code", teamUser.getUserCode()));
//                UserVO userVO = new UserVO();
//                BeanUtils.copyProperties(user,userVO);
//                userVOS.add(userVO);
//            }
            teamsVO.setTeamUsers(teamUsers);
            teamsVOS.add(teamsVO);
        }
    }


    /**
     * 通过流程实例id获取项目信息
     * @param instanceId
     * @return
     */
    @ApiOperation("通过流程实例id获取项目信息")
    @ApiImplicitParam(name="instanceId",value = "流程实例id",required = true)
    @GetMapping("/get/process")
    public ProjectVO getListByInstanceId(@RequestParam String instanceId){
        Project project = projectService.getOne(new QueryWrapper<Project>().eq("instance_id", instanceId));
        if (project == null) {
            return new ProjectVO();
        }
        ProjectVO projectVO = this.get(project.getId());
        return projectVO;
    }

    /**
     * 获取用户项目信息
     * @param userCode
     * @return
     */
    @ApiOperation("获取用户项目信息")
    @ApiImplicitParam(name="userCode",value = "用户工号",required = true)
    @GetMapping("/user/{userCode}")
    public Result getProjects(@PathVariable String userCode) {
        List<TeamUser> teamUsers = teamUserService.list(new QueryWrapper<TeamUser>().eq("user_code", userCode));
        List<ProjectVO> projectsVO = new ArrayList<>();
        if (teamUsers != null) {
            for (TeamUser teamUser : teamUsers) {
                ProjectVO projectVO = this.get(teamsService.getById(teamUser.getTeamId()).getProjectId());
                if (projectVO == null) {
                    return ResultUtil.genSuccessResult("无数据");
                }
                projectsVO.add(projectVO);
            }
            return ResultUtil.genSuccessResult(projectsVO);
        }
        return ResultUtil.genSuccessResult("无数据");

    }
}

