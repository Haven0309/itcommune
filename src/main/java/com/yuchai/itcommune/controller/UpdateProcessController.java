package com.yuchai.itcommune.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuchai.itcommune.entity.*;
import com.yuchai.itcommune.entity.Process;
import com.yuchai.itcommune.service.*;
import com.yuchai.itcommune.util.Result;
import com.yuchai.itcommune.util.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.DelegationState;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Haven
 * @create 2019-01-17 17:06
 */
public class UpdateProcessController {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ProcessService processService;
    @Autowired
    private BpmVirtualTodoListController bpmVirtualTodoListController;
    @Autowired
    private ProcessHistoryService processHistoryService;
    @Autowired
    private OaEmpInfoVService oaEmpInfoVService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ActHiActinstService actHiActinstService;
    @Autowired
    private ActHiProcinstService actHiProcinstService;

    @Value("${itcommune.form-url}")
    private String formUrl;

    /**
     *
     * Map{}
     */
    @ApiOperation("添加流程")
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
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("itcommuneupdateProcess", map);
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

//        //更新流程实例ID到项目
//        Project project = new Project();
//        project.setId(new Integer(projectId));
//        project.setInstanceId(instanceId);
//        project.setCurrentStep("申请部门正职审批");
//        boolean b = projectService.saveOrUpdate(project);
//        if (!b) {
//            map.put("code","400");
//            map.put("message","更新流程实例失败！");
//            ResultUtil.genSuccessResult(map);
//        }
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
        process.setCurrentNodeId("p2");
        process.setNextNodeId("p3");
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
        history.setStepId("p1");
        history.setCreatedTime(LocalDateTime.now());
        processHistoryService.saveOrUpdate(history);

//        map.put("taskId",taskId);
        map.put("instanceId",instanceId);
        map.put("code","200");
        map.put("message","SUCCESS");
        return ResultUtil.genSuccessResult(map);
    }

    private String getUser(String userCode){
        if (userCode.equals("admin")) {
            return "流程管理员";
        }
        OaEmpInfoV user = oaEmpInfoVService.getOne(new QueryWrapper<OaEmpInfoV>().eq("EMPLOYEE_CODE", userCode));
        return user.getEmployeeName();
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
//        //更新项目信息
//        updateProject(instanceId, currentFlowElement);
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
//        bpmVirtualTodo.setCreateTime(LocalDateTime.now());
//        bpmVirtualTodo.setCreatorName(this.getUser(createdBy).getUserName());
        bpmVirtualTodo.setAssignee(assigneeCode);
        bpmVirtualTodo.setAssigneddate(LocalDateTime.now());
        if (nextFlowElement != null){
            bpmVirtualTodo.setActivityLabel(nextFlowElement.getName());
        }
//        bpmVirtualTodo.setTitle(title);
        bpmVirtualTodo.setInstanceId(instanceId);
//        bpmVirtualTodo.setFormurl(formUrl+instanceId);
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
//        //更新项目信息
//        updateProject(instanceId,currentFlowElement);
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
}
