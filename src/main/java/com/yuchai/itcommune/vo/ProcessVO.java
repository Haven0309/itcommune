package com.yuchai.itcommune.vo;


import com.yuchai.itcommune.entity.ProcessHistory;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Haven
 * @create 2018-09-18 11:18
 */
public class ProcessVO {
    private Integer id;

    /**
     * 流程实例ID
     */
    private String instanceId;

    /**
     * 项目ID
     */
    private String processTitle;

    private String currentNodeId;

    private String currentNode;

    private String assigneeName;

    private String assigneeCode;

    private Integer projectId;

    private String formUrl;

    /**
     * 创建人
     */
    private String createdBy;

    private LocalDateTime createdTime;

    private String taskId;

    private ProjectVO project;

    private List<ProcessHistory> histories;

    public List<ProcessHistory> getHistories() {
        return histories;
    }

    public void setHistories(List<ProcessHistory> histories) {
        this.histories = histories;
    }

    public ProjectVO getProject() {
        return project;
    }

    public void setProject(ProjectVO project) {
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getProcessTitle() {
        return processTitle;
    }

    public void setProcessTitle(String processTitle) {
        this.processTitle = processTitle;
    }

    public String getCurrentNodeId() {
        return currentNodeId;
    }

    public void setCurrentNodeId(String currentNodeId) {
        this.currentNodeId = currentNodeId;
    }

    public String getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }

    public String getAssigneeName() {
        return assigneeName;
    }

    public void setAssigneeName(String assigneeName) {
        this.assigneeName = assigneeName;
    }

    public String getAssigneeCode() {
        return assigneeCode;
    }

    public void setAssigneeCode(String assigneeCode) {
        this.assigneeCode = assigneeCode;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getFormUrl() {
        return formUrl;
    }

    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "ProcessVO{" +
                "id=" + id +
                ", instanceId='" + instanceId + '\'' +
                ", processTitle='" + processTitle + '\'' +
                ", currentNode='" + currentNode + '\'' +
                ", assigneeName='" + assigneeName + '\'' +
                ", assigneeCode='" + assigneeCode + '\'' +
                ", projectId=" + projectId +
                ", formUrl='" + formUrl + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdTime=" + createdTime +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
