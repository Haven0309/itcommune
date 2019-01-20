package com.yuchai.itcommune.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.*;
import com.yuchai.itcommune.service.*;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import com.yuchai.itcommune.vo.ProjectVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Haven
 * @create 2018-11-25 14:32
 */
@Api(tags = "1.7", description = "统计报表", value = "统计报表")
@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportDataController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamsService teamsService;
    @Autowired
    private UserService userService;
    @Autowired
    private SalaryTopService salaryTopService;
    @Autowired
    private DeptMoneyTopService deptMoneyTopService;
    @Autowired
    private ProjectUserVService projectUserVService;
    @Autowired
    private ProjectUserMoneyService projectUserMoneyService;



    @ApiOperation("首页统计")
    @GetMapping("/homeTotal")
    public Result total(){
        List<Project> projects = projectService.list();
        int totalMoney = 0;
        for (Project project:projects) {
            totalMoney=totalMoney+project.getMoney();
        }
        int projectSize = projects.size();
        int teamSize = teamsService.list(null).size();
        int UserSize = userService.list(null).size();
        Map<String,Object> map = new HashMap();
        map.put("projectSize",projectSize);
        map.put("teamSize",teamSize);
        map.put("totalMoney",totalMoney);
        map.put("UserSize",UserSize);
        return ResultUtil.genSuccessResult(map);
    }

    @ApiOperation("收入排行")
    @GetMapping("/salaryTop")
    public Result salaryTop(){
        List<SalaryTop> tops = salaryTopService.list(new QueryWrapper<SalaryTop>(null));
        List<TeamUser> teamUsers = new ArrayList<>();
        for (SalaryTop salaryTop:tops) {
            TeamUser teamUser = new TeamUser();
            BeanUtils.copyProperties(salaryTop,teamUser);
            teamUsers.add(teamUser);
        }
//        List<TeamUser> teamUsers = teamUserReportService.salaryTop();
        for (TeamUser teamUser : teamUsers) {
            User user = userService.getOne(new QueryWrapper<User>().eq("user_code", teamUser.getUserCode()));
            if (user == null) {
                return ResultUtil.genSuccessResult(teamUsers);
            }
            teamUser.setUserName(user.getUserName());
        }
        return ResultUtil.genSuccessResult(teamUsers);
    }

    @ApiOperation("部门支出排行")
    @GetMapping("/deptMoneyTop")
    public Result deptMoneyTop(){
        return ResultUtil.genSuccessResult(deptMoneyTopService.list(null));
    }

    @ApiOperation("我的部门支出")
    @GetMapping("/deptMoney")
    public Result deptMoney(String userCode){
        String department = userService.getOne(new QueryWrapper<User>().eq("user_code", userCode)).getDepartment();
        if (department == null) {
            return ResultUtil.genFailResult("部门信息不正确");
        }
        List<ProjectUserV> vList = projectUserVService.list(new QueryWrapper<ProjectUserV>().eq("department", department).eq("status","完成"));
        return ResultUtil.genSuccessResult(vList);
    }
    @ApiOperation("我的部门支出")
    @GetMapping("/deptMoney/search")
    public Result deptMoneySearch(@RequestParam String userCode,@RequestParam String startDate,@RequestParam String endDate){
        String department = userService.getOne(new QueryWrapper<User>().eq("user_code", userCode)).getDepartment();
        if (department == null) {
            return ResultUtil.genFailResult("部门信息不正确");
        }
        List<ProjectUserV> vList = projectUserVService.list(new QueryWrapper<ProjectUserV>().eq("department", department).eq("status","完成").between("expiration_date",startDate,endDate));
        return ResultUtil.genSuccessResult(vList);
    }

    @ApiOperation("项目-部门支出情况")
    @GetMapping("/projectDeptMoney")
    public Result projectDeptMoney(){
        List<ProjectUserV> list = projectUserVService.list(new QueryWrapper<ProjectUserV>().eq("status","完成").orderByDesc("department"));
        return ResultUtil.genSuccessResult(list);
    }
    @ApiOperation("项目-部门支出情况")
    @GetMapping("/projectDeptMoney/search")
    public Result projectDeptMoneySearch(@RequestParam(required = false) String dept,@RequestParam String startDate,@RequestParam String endDate){
        List<ProjectUserV> list;
        if (dept == null) {
            list = projectUserVService.list(new QueryWrapper<ProjectUserV>().eq("status","完成").between("expiration_date",startDate,endDate).orderByDesc("department"));
            return ResultUtil.genSuccessResult(list);
        }
        list = projectUserVService.list(new QueryWrapper<ProjectUserV>().eq("status","完成").like("department",dept).between("expiration_date",startDate,endDate).orderByDesc("department"));
        return ResultUtil.genSuccessResult(list);
    }

    @ApiOperation("项目-人员收入情况")
    @GetMapping("/projectUserMoney")
    public Result projectUserMoney(){
        List<ProjectUserMoney> list = projectUserMoneyService.list(new QueryWrapper<ProjectUserMoney>().ne("quit","2").ne("salary","0").isNotNull("salary"));
        return ResultUtil.genSuccessResult(list);
    }
    @ApiOperation("项目-人员收入情况")
    @PostMapping("/projectUserMoney/search/{startDate}/{endDate}")
    public Result projectUserMoneySearch(@RequestBody Map<String,Object> map,@PathVariable String startDate,@PathVariable String endDate){
        List<ProjectUserMoney> list;
        if (startDate == null && endDate == null) {
            list = projectUserMoneyService.list(new QueryWrapper<ProjectUserMoney>().allEq(map,false));
        }else {
            list = projectUserMoneyService.list(new QueryWrapper<ProjectUserMoney>().allEq(map,false).between("p_created_date",startDate,endDate));
        }
//        List<ProjectUserMoney> list = projectUserMoneyService.list(new QueryWrapper<ProjectUserMoney>().ne("quit","2").ne("salary","0").isNotNull("salary").between("expiration_date",startDate,endDate));
        return ResultUtil.genSuccessResult(list);
    }
}
