package com.yuchai.itcommune.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.Evaluation;
import com.yuchai.itcommune.entity.TeamUser;
import com.yuchai.itcommune.entity.Teams;
import com.yuchai.itcommune.entity.User;
import com.yuchai.itcommune.service.EvaluationService;
import com.yuchai.itcommune.service.TeamUserService;
import com.yuchai.itcommune.service.TeamsService;
import com.yuchai.itcommune.service.UserService;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import com.yuchai.itcommune.vo.TeamsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Haven
 * @since 2018-09-14
 */
@Api(tags = "1.4", description = "团队管理", value = "项目团队信息管理")
@CrossOrigin
@RestController
@RequestMapping("/teams")
public class TeamsController {
    @Autowired
    private TeamsService teamsService;
    @Autowired
    private TeamUserService teamUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private EvaluationService evaluationService;
    /**
     * 创建更新团队
     * @param teams
     * @return
     */
    @ApiOperation("创建更新团队")
    @PostMapping("/addOrUpdate")
    public Result saveOrUpdate(@RequestBody Teams teams) {
        String name = teams.getTeamName();
        int size = teamsService.list(new QueryWrapper<Teams>().eq("team_name", name)).size();
        if (size == 0) {
            boolean b = teamsService.saveOrUpdate(teams);
            TeamUser teamUser = new TeamUser();
            teamUser.setUserCode(teams.getCreateBy());
            teamUser.setUserName(this.getUser(teams.getCreateBy()).getUserName());
            teamUser.setTeamId(teams.getId());
            teamUser.setQuit("1");
            teamUserService.saveOrUpdate(teamUser);
            if (b) {
                return ResultUtil.genSuccessResult(teams);
            }
            return ResultUtil.genFailResult("插入失败");
        }

        return ResultUtil.genFailResult("团队名已存在");
    }


    /**
     * 获取用户团队和项目信息
     * @param userCode
     * @return
     */
    @ApiOperation("获取用户团队和项目信息")
    @ApiImplicitParam(name="userCode",value = "用户工号",required = true)
    @GetMapping("/user/{userCode}")
    public List<TeamsVO> getProjects(@PathVariable String userCode) {
        List<TeamUser> teamUsers = teamUserService.list(new QueryWrapper<TeamUser>().eq("user_code", userCode));
        List<TeamsVO> teamsVOS = new ArrayList<>();
        for (TeamUser teamUser : teamUsers) {
            TeamsVO teamsVO = new TeamsVO();
            BeanUtils.copyProperties(teamsService.getById(teamUser.getTeamId()),teamsVO);
            teamsVOS.add(teamsVO);
        }
        return teamsVOS;
    }

    /**
     * 获取我的团队
     * @param userCode
     * @return
     */
    @ApiOperation("获取我的团队")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userCode",value = "用户工号",required = true),
            @ApiImplicitParam(name="projectId",value = "项目ID",required = true)
    })
    @GetMapping("/myTeam")
    public Result getMyTeam(@RequestParam String userCode, @RequestParam Integer projectId) {
        List<TeamUser> teamUsers = teamUserService.list(new QueryWrapper<TeamUser>().eq("user_code", userCode));
        for (TeamUser teamUser : teamUsers) {
            TeamsVO teamsVO = new TeamsVO();
            Teams teams = teamsService.getById(teamUser.getTeamId());

            if(teams.getProjectId().equals(projectId)){
                BeanUtils.copyProperties(teams,teamsVO);
                List<TeamUser> users = teamUserService.list(new QueryWrapper<TeamUser>().eq("team_id", teams.getId()));
//                List<UserVO> userVOS = new ArrayList<>();
//                for (TeamUser tu:users) {
//                    UserVO userVO = new UserVO();
//                    BeanUtils.copyProperties(userService.getOne(new QueryWrapper<User>().eq("user_code",tu.getUserCode())),userVO);
//                    userVOS.add(userVO);
//                }
                teamsVO.setTeamUsers(users);
                List<Evaluation> evaluations = evaluationService.list(new QueryWrapper<Evaluation>().eq("team_id", teams.getId()));
                teamsVO.setEvaluations(evaluations);
                return ResultUtil.genSuccessResult(teamsVO);
            }

        }
        return ResultUtil.genFailResult("无团队数据");
    }

    /**
     * 根据项目搜索团队
     */
    @ApiOperation("根据项目搜索团队")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value = "用户名",required = true),
            @ApiImplicitParam(name="projectId",value = "项目ID",required = true)
    })
    @GetMapping("/get/{projectId}")
    public Result getTeams(@RequestParam String name, @PathVariable Integer projectId){
        return ResultUtil.genSuccessResult(teamsService.list(new QueryWrapper<Teams>().like("team_name", name).eq("project_id", projectId)));
    }
//    /**
//     * 根据项目搜索我的团队
//     */
//    @GetMapping("/myTeam/{projectId}")
//    public Result getTeams(@RequestParam String name,@PathVariable Integer projectId){
//        return ResultUtil.genSuccessResult(teamsService.list(new QueryWrapper<Teams>().eq("team_name", name).eq("project_id", projectId)));
//    }
    private User getUser(String userCode){
        User user = userService.getOne(new QueryWrapper<User>().eq("user_code", userCode));
        return user;
    }

}

