package com.yuchai.itcommune.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Haven
 * @since 2018-11-27
 */
public class ActHiActinst implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID_", type = IdType.AUTO)
    private String id;
    @TableField("REV_")
    private Integer rev;
    @TableField("PROC_DEF_ID_")
    private String procDefId;
    @TableField("PROC_INST_ID_")
    private String procInstId;
    @TableField("EXECUTION_ID_")
    private String executionId;
    @TableField("ACT_ID_")
    private String actId;
    @TableField("TASK_ID_")
    private String taskId;
    @TableField("CALL_PROC_INST_ID_")
    private String callProcInstId;
    @TableField("ACT_NAME_")
    private String actName;
    @TableField("ACT_TYPE_")
    private String actType;
    @TableField("ASSIGNEE_")
    private String assignee;
    @TableField("START_TIME_")
    private LocalDateTime startTime;
    @TableField("END_TIME_")
    private LocalDateTime endTime;
    @TableField("DURATION_")
    private Long duration;
    @TableField("DELETE_REASON_")
    private String deleteReason;
    @TableField("TENANT_ID_")
    private String tenantId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRev() {
        return rev;
    }

    public void setRev(Integer rev) {
        this.rev = rev;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCallProcInstId() {
        return callProcInstId;
    }

    public void setCallProcInstId(String callProcInstId) {
        this.callProcInstId = callProcInstId;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String toString() {
        return "ActHiActinst{" +
        "id=" + id +
        ", rev=" + rev +
        ", procDefId=" + procDefId +
        ", procInstId=" + procInstId +
        ", executionId=" + executionId +
        ", actId=" + actId +
        ", taskId=" + taskId +
        ", callProcInstId=" + callProcInstId +
        ", actName=" + actName +
        ", actType=" + actType +
        ", assignee=" + assignee +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", duration=" + duration +
        ", deleteReason=" + deleteReason +
        ", tenantId=" + tenantId +
        "}";
    }
}
