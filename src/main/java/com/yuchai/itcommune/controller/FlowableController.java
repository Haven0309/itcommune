package com.yuchai.itcommune.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.*;
import com.yuchai.itcommune.entity.Process;
import com.yuchai.itcommune.service.*;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import com.yuchai.itcommune.util.ToolUtil;
import com.yuchai.itcommune.vo.ProcessVO;
import com.yuchai.itcommune.vo.ProjectVO;
import com.yuchai.itcommune.vo.TeamsVO;
import com.yuchai.itcommune.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.DelegationState;
import org.flowable.task.api.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Haven
 * 2018/6/8 8:14
 */
@Api(tags = "1.0", description = "流程管理", value = "Flowable流程管理")
@CrossOrigin
@Controller
@RequestMapping(value = "/process")
public class FlowableController {
    private static final Logger logger = LoggerFactory.getLogger(FlowableController.class);
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Qualifier("processEngine")
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProcessService processService;
    @Autowired
    private TeamsService teamsService;
    @Autowired
    private TeamUserService teamUserService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectUserService projectUserService;
    @Autowired
    private ProcessHistoryService processHistoryService;
    @Autowired
    private ActHiActinstService actHiActinstService;
    @Autowired
    private ActHiProcinstService actHiProcinstService;
    @Autowired
    private ProjectUploadService projectUploadService;
    @Autowired
    private BpmVirtualTodoListController bpmVirtualTodoListController;
    @Autowired
    private OaEmpInfoVService oaEmpInfoVService;

    @Value("${itcommune.form-url}")
    private String formUrl;


    /**
     * 添加itcommunity流程
     * Map{}
     */
    @ApiOperation("添加itcommunity流程")
    @PostMapping(value = "/itcommunity/add")
    @ResponseBody
    public Result addProcess(@RequestBody Map<String, Object> map) {
        String createdBy = "";
        createdBy = map.get("createdBy").toString();
        String title = map.get("title").toString();
        String assigneeCode = map.get("assigneeCode").toString();
        map.replace("assigneeCode",createdBy);
        String projectId = map.get("projectId").toString();
        String formurl = map.get("formurl").toString();
        //设置流程创建人
        Authentication.setAuthenticatedUserId(createdBy);
        //启动流程
        if (assigneeCode == null) {
            map.put("code","400");
            map.put("message","assigneeCode不能为空");
            ResultUtil.genSuccessResult(map);
        }
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("itcommunity", map);
        String instanceId = processInstance.getProcessInstanceId();
//        String taskId = processInstance.getId();
        //跳过第一个环节
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("outcome", "y");
        if (assigneeCode == null) {
            return ResultUtil.genFailResult("assigneeCode为空");
        } else {
            map1.put("assigneeCode", assigneeCode);
        }
        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        String taskId = task.getId();
        taskService.complete(taskId, map1);

        //更新流程实例ID到项目
        Project project = new Project();
        project.setId(new Integer(projectId));
        project.setInstanceId(instanceId);
        project.setCurrentStep("申请部门正职审批");
        boolean b = projectService.saveOrUpdate(project);
        if (!b) {
            map.put("code","400");
            map.put("message","更新流程实例失败！");
            ResultUtil.genSuccessResult(map);
        }
        //更新到待办表
        Process process = new Process();
        process.setCreatedBy(this.getUser(createdBy));
        process.setAssigneeCode(assigneeCode);
        process.setAssigneeName(this.getUser(assigneeCode));
        process.setCreatedTime(LocalDateTime.now());
        process.setFormUrl("www.baidu.com");
        process.setInstanceId(instanceId);
        process.setProcessTitle("【IT公社】"+title);
        process.setProjectId(Integer.valueOf(projectId));
        process.setCurrentNode("申请部门正职审批");
        process.setCurrentNodeId("t2");
        process.setNextNodeId("t3");
        process.setNextNode("IT公社秘书长审核");
        process.setTaskId(taskId);
        processService.saveOrUpdate(process);

        //在玉柴云虚拟待办表中添加数据
        BpmVirtualTodoList bpmVirtualTodo = new BpmVirtualTodoList();
        bpmVirtualTodo.setCreateTime(LocalDateTime.now());
        bpmVirtualTodo.setCreatorName(this.getUser(createdBy));
        bpmVirtualTodo.setAssignee(assigneeCode);
        bpmVirtualTodo.setAssigneddate(LocalDateTime.now());
        bpmVirtualTodo.setActivityLabel("申请部门正职审批");
        bpmVirtualTodo.setTitle("【IT公社】"+title);
        bpmVirtualTodo.setInstanceId(instanceId);
        bpmVirtualTodo.setFormurl(formUrl+instanceId);
        bpmVirtualTodoListController.saveOrUpdate(bpmVirtualTodo);

        //更新流程历史
        ProcessHistory history = new ProcessHistory();
        history.setApprovalOpinion("同意");
        history.setApprovalType("提交");
        history.setAssigneeCode(createdBy);
        history.setAssigneeName(this.getUser(createdBy));
        history.setInstanceId(instanceId);
        history.setStepName("发布人填写");
        history.setStepId("t1");
        history.setCreatedTime(LocalDateTime.now());
        processHistoryService.saveOrUpdate(history);

//        map.put("taskId",taskId);
        map.put("instanceId",instanceId);
        map.put("code","200");
        map.put("message","SUCCESS");
        return ResultUtil.genSuccessResult(map);
    }

    @ApiOperation("通过instance获取流程及项目信息")
    @GetMapping("/detail/{instanceId}")
    @ResponseBody
    public Result getProcessDetail(@PathVariable String instanceId){
        //获取当前环节信息
        FlowElement currentFlowElement = getCurrentFlowElement(instanceId);
        FlowElement nextFlowElement = getNextFlowElement(instanceId);
//        String currentNode = "";
//        if (currentFlowElement == null) {
//            currentNode = "完成";
//        }else {
//            currentNode = currentFlowElement.getName();
//        }

        //获取流程信息
        Process process = processService.getOne(new QueryWrapper<Process>().eq("instance_id", instanceId));
//        process.setCurrentNode(currentNode);
//        if (nextFlowElement != null) {
//            process.setNextNodeId(nextFlowElement.getId());
//            process.setNextNode(nextFlowElement.getName());
//        }

        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        if (task != null) {
//            process.setAssigneeCode(task.getAssignee());
            process.setTaskId(task.getId());
        }

        //获取项目信息
        Project project = projectService.getOne(new QueryWrapper<Project>().eq("instance_id", instanceId));
//        project.setCurrentStep(currentNode);
        ProjectVO projectVO = this.get(project.getId());
        ProcessVO processVO = new ProcessVO();
        BeanUtils.copyProperties(process,processVO);
        processVO.setProject(projectVO);

        //获取附件信息
        List<ProjectUpload> projectUploads = projectUploadService.list(new QueryWrapper<ProjectUpload>().eq("project_id", project.getId()));
        processVO.setProjectUploads(projectUploads);

        //获取流程历史记录
        List<ProcessHistory> histories = processHistoryService.list(new QueryWrapper<ProcessHistory>().eq("instance_id", instanceId).orderByDesc("created_time"));
        processVO.setHistories(histories);
        return ResultUtil.genSuccessResult(processVO);

    }

    private FlowElement getCurrentFlowElement(@PathVariable String instanceId) {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        //流程走完的返回
        if (pi == null) {
            return null;
        }
        //获取流程环节信息
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        org.flowable.bpmn.model.Process process = bpmnModel.getProcesses().get(0);

        //获取全部环节信息
        Map<String, FlowElement> elementMap = process.getFlowElementMap();

        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        //获取当前环节
        String id = runtimeService.getActiveActivityIds(task.getExecutionId()).get(0);
        FlowElement currentFlowElement = process.getFlowElement(id);
        return currentFlowElement;
    }

    private FlowElement getNextFlowElement(@PathVariable String instanceId) {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        //流程走完的返回
        if (pi == null) {
            return null;
        }
        //获取流程环节信息
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        org.flowable.bpmn.model.Process process = bpmnModel.getProcesses().get(0);

        //获取全部环节信息
        Map<String, FlowElement> elementMap = process.getFlowElementMap();

        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        //获取当前环节
        String id = runtimeService.getActiveActivityIds(task.getExecutionId()).get(0);
        //获取下一个环节
        FlowElement nextFlowElement = process.getFlowElement("t"+(Integer.valueOf(id.substring(1))+1));
        return nextFlowElement;
    }

    private ProjectVO get( Integer id) {
        ProjectVO projectVO = new ProjectVO();
        BeanUtils.copyProperties(projectService.getById(id),projectVO);

        List<Teams> teams = teamsService.list(new QueryWrapper<Teams>().eq("project_id", id));
        List<TeamsVO> teamsVOS = new ArrayList<>();
        foreachTeams(teams, teamsVOS);

        projectVO.setTeams(teamsVOS);
        List<ProjectUser> projectUsers = projectUserService.list(new QueryWrapper<ProjectUser>().eq("project_id", id));
        projectVO.setProjectUsers(projectUsers);
        return projectVO;
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
     * 获取审批管理列表
     */
    @ApiOperation("获取审批管理列表")
    @ApiImplicitParam(name="userCode",value = "用户工号",required = true)

    @RequestMapping(value = "/list/{userCode}",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@PathVariable String userCode) {
        if (userCode == null) {
            return null;
        } else {
            List<Task> tasks = taskService.createTaskQuery().taskAssignee(userCode).orderByTaskCreateTime().desc().list();
            List<ProcessVO> list = new ArrayList<>();
            for (Task task : tasks) {
                ProcessVO processVO = new ProcessVO();
//                processVO.setAssignee(task.getAssignee());
//                processVO.setTaskId(task.getId());
//                processVO.setCreateTime(task.getCreateTime());
//                processVO.setDescription(task.getDescription());
//                processVO.setName(task.getName());
//                processVO.setProcessInstanceId(task.getProcessInstanceId());
//                processVO.setProcessDefinitionId(task.getProcessDefinitionId());
//                processVO.setProcessVariables(task.getProcessVariables());
//                processVO.setTaskLocalVariables(task.getTaskLocalVariables());
//                processVO.setMyVariable(taskService.getVariable(task.getId(), "data").toString());
                list.add(processVO);
            }
            return ResultUtil.genSuccessResult(list);
        }
    }

    /**
     * 批准
     *
     * @param instanceId 任务ID
     */
    @ApiOperation("批准")
    @ApiImplicitParams({
            @ApiImplicitParam(name="assigneeCode",value = "下个审批人工号",required = true),
            @ApiImplicitParam(name="taskId",value = "taskID",required = true),
            @ApiImplicitParam(name="approvalOpinion",value = "审批意见",required = true),
            @ApiImplicitParam(name="instanceId",value = "流程实例id",required = true)
    })
    @RequestMapping(value = "/approve")
    @ResponseBody
    public Result approve(String instanceId,String taskId, String assigneeCode,String currentCode,String approvalOpinion) {
//        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();


        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        FlowElement oldFlowElement = getCurrentFlowElement(instanceId);
//        String name = task.getName();
        if (task == null) {
            return ResultUtil.genFailResult("流程不存在");
//            throw new RuntimeException("流程不存在");
        }
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "y");
        if (assigneeCode == null) {
            return ResultUtil.genFailResult("assigneeCode为空");
        } else {
            map.put("assigneeCode", assigneeCode);
        }

        DelegationState delegationState = task.getDelegationState();
        //判断是否被委托过delegateTask
        if (delegationState != null && delegationState.toString().equals("PENDING")) {
            taskService.resolveTask(task.getId(), map);
        }
        taskService.complete(task.getId(), map);

        FlowElement currentFlowElement = getCurrentFlowElement(instanceId);
        FlowElement nextFlowElement = getNextFlowElement(instanceId);
        //更新玉柴云虚拟待办表中待办数据
        updateBPMTodo(instanceId, assigneeCode, nextFlowElement);
        //更新流程信息
        updateProcess(instanceId, assigneeCode, currentFlowElement, nextFlowElement);
        //更新项目信息
        updateProject(instanceId, currentFlowElement);
        //更新流程历史
        updateProcessHistory(instanceId, currentCode, approvalOpinion, oldFlowElement);
        return ResultUtil.genSuccessResult(map);
    }

    private void updateProcessHistory(String instanceId, String currentCode, String approvalOpinion, FlowElement oldFlowElement) {
        ProcessHistory history = new ProcessHistory();
        history.setApprovalOpinion(approvalOpinion);
        history.setApprovalType("提交");
        history.setAssigneeCode(currentCode);
        history.setAssigneeName(this.getUser(currentCode));
        history.setInstanceId(instanceId);
        history.setStepName(oldFlowElement.getName());
        history.setStepId(oldFlowElement.getId());
        history.setCreatedTime(LocalDateTime.now());
        processHistoryService.saveOrUpdate(history);
    }

    private void updateBPMTodo(String instanceId, String assigneeCode, FlowElement nextFlowElement) {
        BpmVirtualTodoList bpmVirtualTodo = new BpmVirtualTodoList();
        bpmVirtualTodo.setAssignee(assigneeCode);
        bpmVirtualTodo.setAssigneddate(LocalDateTime.now());
        if (nextFlowElement != null){
            bpmVirtualTodo.setActivityLabel(nextFlowElement.getName());
        }
        bpmVirtualTodo.setInstanceId(instanceId);
        bpmVirtualTodoListController.saveOrUpdate(bpmVirtualTodo);
    }

    private void updateProcess(String instanceId, String assigneeCode, FlowElement currentFlowElement, FlowElement nextFlowElement) {
        Process process = processService.getOne(new QueryWrapper<Process>().eq("instance_id",instanceId));
        process.setAssigneeCode(assigneeCode);
        process.setAssigneeName(getUser(assigneeCode));
        if (currentFlowElement == null) {
            process.setCurrentNode("结束");
            process.setCurrentNodeId("t10");
        }else {
            process.setCurrentNode(currentFlowElement.getName());
            process.setCurrentNodeId(currentFlowElement.getId());
        }
        if (nextFlowElement != null){
            process.setNextNode(nextFlowElement.getName());
            process.setNextNodeId(nextFlowElement.getId());
        }
        processService.saveOrUpdate(process);
    }

    private void updateProject(String instanceId, FlowElement currentFlowElement) {
        Project project = projectService.getOne(new QueryWrapper<Project>().eq("instance_id", instanceId));
        if (currentFlowElement != null) {
            String currentStep = currentFlowElement.getId();
            project.setCurrentStep(currentFlowElement.getName());
            //更新状态
            if (currentStep.equals("t5")) {
                project.setStatus("进行中");
            }else if(currentStep.equals("t10")){
                project.setStatus("完成");
            }
        }else {
            project.setCurrentStep("结束");
            project.setStatus("完成");
        }
        projectService.saveOrUpdate(project);
    }

    /**
     * 转办
     *
     * @param taskId 任务ID
     */
    @ApiOperation("转办")
    @ApiImplicitParams({
            @ApiImplicitParam(name="assigneeCode",value = "下个审批人工号",required = true),
            @ApiImplicitParam(name="taskId",value = "流程taskID",required = true)
    })
    @RequestMapping(value = "/delegate")
    @ResponseBody
    public Result delegateTask(String taskId, String assigneeCode) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return ResultUtil.genFailResult("流程不存在");
        }
//        taskService.setAssignee(taskId, assigneeCode);CommandContextUtil

        taskService.delegateTask(taskId, assigneeCode);
        return ResultUtil.genSuccessResult("转办成功");
    }


    /**
     * 拒绝
     */
    @ApiOperation("拒绝")
    @ApiImplicitParam(name="taskId",value = "流程taskID",required = true)
    @ResponseBody
    @RequestMapping(value = "/reject")
    public Result reject(String taskId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "n");
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return ResultUtil.genFailResult("流程不存在");
        }
        taskService.complete(taskId, map);
        return ResultUtil.genSuccessResult(map);
    }

    /**
     * 跳转
     */
    @ApiOperation("跳转-退回")
    @ApiImplicitParam(name="instanceId",value = "流程instanceId",required = true)
    @RequestMapping(value = "/changeActivity")
    @ResponseBody
    public Result changeActivity(String instanceId,String approvalOpinion){
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        org.flowable.bpmn.model.Process processEn = bpmnModel.getProcesses().get(0);

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        String id = runtimeService.getActiveActivityIds(task.getExecutionId()).get(0);
        FlowElement currentFlowElement = processEn.getFlowElement(id);
        String currentActivityId = currentFlowElement.getId();
        int nextActivityId = Integer.parseInt(currentActivityId.substring(1))-1;
        //执行退回操作
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(instanceId).moveActivityIdTo(currentActivityId,"t"+nextActivityId)
                .changeState();

        //设置退回人
        ActHiActinst one = actHiActinstService.getOne(new QueryWrapper<ActHiActinst>().eq("PROC_INST_ID_", instanceId).eq("ACT_ID_", "t"+nextActivityId));
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        String ass = one.getAssignee();
        if(ass == null){
            ass = actHiProcinstService.getOne(new QueryWrapper<ActHiProcinst>().eq("PROC_INST_ID_", instanceId)).getStartUserId();
            taskService.delegateTask(task.getId(), ass);
        }else{
            taskService.delegateTask(task.getId(), ass);
        }
        currentFlowElement = getCurrentFlowElement(instanceId);
        //更新项目信息
        updateProject(instanceId,currentFlowElement);
        //更新流程信息
        FlowElement nextFlowElement = getNextFlowElement(instanceId);
        updateProcess(instanceId, ass, currentFlowElement, nextFlowElement);

        //更新玉柴云虚拟待办表中待办数据
        updateBPMTodo(instanceId, ass, nextFlowElement);


        //更新流程历史
        ActHiActinst two = actHiActinstService.getOne(new QueryWrapper<ActHiActinst>().eq("PROC_INST_ID_", instanceId).eq("ACT_ID_", currentActivityId));
        ProcessHistory history = new ProcessHistory();
        history.setApprovalOpinion(approvalOpinion);
        history.setApprovalType("退回");
        history.setAssigneeCode(two.getAssignee());
        history.setAssigneeName(getUser(two.getAssignee()));
        history.setInstanceId(instanceId);
        history.setStepName(currentFlowElement.getName());
        history.setStepId(currentFlowElement.getId());
        history.setCreatedTime(LocalDateTime.now());
        processHistoryService.saveOrUpdate(history);

        return ResultUtil.genSuccessResult();
    }
    /**
     * 跳转流程
     */
    @ApiOperation("跳转流程到起草")
    @ApiImplicitParam(name="instanceId",value = "流程instanceID",required = true)
    @ResponseBody
    @RequestMapping(value = "moveActivity")
    public Result moveActivity(String instanceId) {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        String currentActivityId = runtimeService.getActiveActivityIds(task.getExecutionId()).get(0);

        //执行退回操作
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(instanceId).moveActivityIdTo(currentActivityId,"t1")
                .changeState();

        //设置退回人
        ActHiActinst one = actHiActinstService.getOne(new QueryWrapper<ActHiActinst>().eq("PROC_INST_ID_", instanceId).eq("ACT_ID_", "t1"));
        task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        String ass = one.getAssignee();
        if(ass == null){
            ass = actHiProcinstService.getOne(new QueryWrapper<ActHiProcinst>().eq("PROC_INST_ID_", instanceId)).getStartUserId();
            taskService.delegateTask(task.getId(), ass);
        }else{
            taskService.delegateTask(task.getId(), ass);
        }
        //更新流程信息
        Process process = processService.getOne(new QueryWrapper<Process>().eq("instance_id",instanceId));
//        process.setAssigneeCode(assigneeCode);
        process.setCurrentNode("发布人填写");
        process.setCurrentNodeId("t1");
        process.setNextNode("申请部门正职审批");
        process.setNextNodeId("t2");
        process.setAssigneeCode(ass);
        process.setAssigneeName(this.getUser(ass));
        processService.saveOrUpdate(process);
        Project project = new Project();
        project.setId(process.getProjectId());
        project.setStatus("维护中");
        projectService.updateById(project);

        //更新玉柴云虚拟待办表中待办数据
        BpmVirtualTodoList bpmVirtualTodo = new BpmVirtualTodoList();
        bpmVirtualTodo.setAssignee(ass);
        bpmVirtualTodo.setAssigneddate(LocalDateTime.now());
        bpmVirtualTodo.setActivityLabel("发布人填写");
        bpmVirtualTodo.setInstanceId(instanceId);
        bpmVirtualTodoListController.saveOrUpdate(bpmVirtualTodo);


        //更新流程历史
        ActHiActinst two = actHiActinstService.getOne(new QueryWrapper<ActHiActinst>().eq("PROC_INST_ID_", instanceId).eq("ACT_ID_", currentActivityId));
        ProcessHistory history = new ProcessHistory();
        history.setApprovalOpinion("维护");
        history.setApprovalType("退回");
        history.setAssigneeCode(two.getAssignee());
        history.setAssigneeName(getUser(two.getAssignee()));
        history.setInstanceId(instanceId);
        history.setStepName("开放接单");
        history.setStepId("t1");
        history.setCreatedTime(LocalDateTime.now());
        processHistoryService.saveOrUpdate(history);

        return ResultUtil.genSuccessResult("操作成功");

    }

    /**
     * 终止流程
     */
    @ApiOperation("终止流程")
    @ApiImplicitParam(name="instanceId",value = "流程instanceID",required = true)
    @ResponseBody
    @RequestMapping(value = "delete")
    public Result delete(String instanceId) {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        org.flowable.bpmn.model.Process processEn = bpmnModel.getProcesses().get(0);

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();

        String id = runtimeService.getActiveActivityIds(task.getExecutionId()).get(0);
        FlowElement currentFlowElement = processEn.getFlowElement(id);
        String currentActivityId = currentFlowElement.getId();
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(instanceId).moveActivityIdTo(currentActivityId,"t10")
                .changeState();
        //更新流程信息
        Process process = processService.getOne(new QueryWrapper<Process>().eq("instance_id",instanceId));
//        process.setAssigneeCode(assigneeCode);
        process.setCurrentNode("终止");
        process.setCurrentNodeId("t10");
        processService.saveOrUpdate(process);
        Project project = new Project();
        project.setId(process.getProjectId());
        project.setStatus("终止");
        projectService.updateById(project);
        return ResultUtil.genSuccessResult("流程已经终止");
    }

    /**
     * 生成流程图
     *
     * @param instanceId 任务ID
     */
    @ApiOperation("生成流程图")
    @ApiImplicitParam(name="instanceId",value = "流程实例id",required = true)
    @RequestMapping(value = "/processDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String instanceId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instanceId).singleResult();

        //流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();

        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    private String getUser(String userCode){
        if (userCode.equals("admin")) {
            return "流程管理员";
        }
        OaEmpInfoV user = oaEmpInfoVService.getOne(new QueryWrapper<OaEmpInfoV>().eq("EMPLOYEE_CODE", userCode));
        return user.getEmployeeName();
    }
}
