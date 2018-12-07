package com.yuchai.itcommune.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class ProcessHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String instanceId;

    private String stepName;

    private String stepId;

    private String assigneeName;

    private String assigneeCode;

    private String approvalType;

    private String approvalOpinion;

    private LocalDateTime createdTime;


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

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
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

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "ProcessHistory{" +
        "id=" + id +
        ", instanceId=" + instanceId +
        ", stepName=" + stepName +
        ", stepId=" + stepId +
        ", assigneeName=" + assigneeName +
        ", assigneeCode=" + assigneeCode +
        ", approvalType=" + approvalType +
        ", approvalOpinion=" + approvalOpinion +
        ", createdTime=" + createdTime +
        "}";
    }
}
