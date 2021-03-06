package com.yuchai.itcommune.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.*;
import com.yuchai.itcommune.service.EvaluationService;
import com.yuchai.itcommune.service.ProjectService;
import com.yuchai.itcommune.service.TeamUserService;
import com.yuchai.itcommune.service.TeamsService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import com.yuchai.itcommune.vo.ProjectSalaryVO;
import com.yuchai.itcommune.vo.TeamsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
@Api(tags = "1.4", description = "团队用户管理", value = "团队用户信息管理")
@CrossOrigin
@RestController
@RequestMapping("/teamUser")
public class TeamUserController {
    @Autowired
    private TeamUserService teamUserService;
    @Autowired
    private TeamsService teamsService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private EvaluationService evaluationService;

    /**
     * 加入团队
     * @param teamUser
     * @return
     */
    @ApiOperation("加入团队")
    @PostMapping("/saveOrUpdate")
    public Result saveOrUpdate(@RequestBody TeamUser teamUser){
        //TODO 判断是否已经在团队里面
        String userCode = teamUser.getUserCode();
        Integer teamId = teamUser.getTeamId();
        if(userCode.isEmpty()){
            return ResultUtil.genFailResult("用户code为空，无法加入");
        }

        if (teamUser.getId() == null) {
            List<TeamUser> list = teamUserService.list(new QueryWrapper<TeamUser>().eq("team_id", teamId).eq("user_code", userCode));
            if (list.size() == 0) {
                boolean b = teamUserService.saveOrUpdate(teamUser);
                return ResultUtil.genSuccessResult(b);
            }
        }else {
            boolean b = teamUserService.saveOrUpdate(teamUser);
            return ResultUtil.genSuccessResult(b);
        }

        return ResultUtil.genFailResult("你已经加入了该团队，无法再次加入");
    }
    /**
     * 更新团队成员信息-分钱
     * @param teamsVO
     * @return
     */
    @ApiOperation("更新团队成员信息-分钱")
    @PostMapping("/usersUpdate")
    public Result usersUpdate(@RequestBody TeamsVO teamsVO){

        for (TeamUser teamUser:teamsVO.getTeamUsers()) {
            teamUserService.saveOrUpdate(teamUser);

        }
//        for (Evaluation evaluation:teamsVO.getEvaluations()) {
//            evaluationService.saveOrUpdate(evaluation);
//        }

        return ResultUtil.genSuccessResult("更新成功");
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/get/{userCode}")
    public List<TeamUser> getTeamUser(@PathVariable String userCode){
        return teamUserService.list(new QueryWrapper<TeamUser>().eq("user_code", userCode));
    }
    /**
     * 获取一个用户的结算数据
     * @param id
     * @return
     */

    @ApiOperation("获取一个用户的结算数据")
    @ApiImplicitParam(name="id",value = "用户工号",required = true)
    @GetMapping("/salary/user/{id}")
    public Result salaryAll(@PathVariable String id){
        List<TeamUser> teamUserList = teamUserService.list(new QueryWrapper<TeamUser>().eq("user_code", id));
        List<ProjectSalaryVO> projectSalaryVOs = new ArrayList<>();
        for (TeamUser teamUser : teamUserList){
            Teams team = teamsService.getById(teamUser.getTeamId());
            Project project = projectService.getById(team.getProjectId());
            ProjectSalaryVO projectSalaryVO = new ProjectSalaryVO();
            BeanUtils.copyProperties(project,projectSalaryVO);
            projectSalaryVO.setSalary(teamUser.getSalary());
            projectSalaryVOs.add(projectSalaryVO);
        }

        return ResultUtil.genSuccessResult(projectSalaryVOs);
    }

}

