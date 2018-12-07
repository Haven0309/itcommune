package com.yuchai.itcommune.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 'commune.dept_money_top' is not BASE TABLE
 * </p>
 *
 * @author Haven
 * @since 2018-12-06
 */
public class Process implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    @TableField(exist = false)
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    @Override
    public String toString() {
        return "Process{" +
        "id=" + id +
        ", instanceId=" + instanceId +
        ", processTitle=" + processTitle +
        ", currentNodeId=" + currentNodeId +
        ", currentNode=" + currentNode +
        ", assigneeName=" + assigneeName +
        ", assigneeCode=" + assigneeCode +
        ", projectId=" + projectId +
        ", formUrl=" + formUrl +
        ", createdBy=" + createdBy +
        ", createdTime=" + createdTime +
        "}";
    }
}
