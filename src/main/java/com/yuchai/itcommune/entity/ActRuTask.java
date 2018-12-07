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
 * @since 2018-11-28
 */
public class ActRuTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID_", type = IdType.AUTO)
    private String id;
    @TableField("REV_")
    private Integer rev;
    @TableField("EXECUTION_ID_")
    private String executionId;
    @TableField("PROC_INST_ID_")
    private String procInstId;
    @TableField("PROC_DEF_ID_")
    private String procDefId;
    @TableField("TASK_DEF_ID_")
    private String taskDefId;
    @TableField("SCOPE_ID_")
    private String scopeId;
    @TableField("SUB_SCOPE_ID_")
    private String subScopeId;
    @TableField("SCOPE_TYPE_")
    private String scopeType;
    @TableField("SCOPE_DEFINITION_ID_")
    private String scopeDefinitionId;
    @TableField("NAME_")
    private String name;
    @TableField("PARENT_TASK_ID_")
    private String parentTaskId;
    @TableField("DESCRIPTION_")
    private String description;
    @TableField("TASK_DEF_KEY_")
    private String taskDefKey;
    @TableField("OWNER_")
    private String owner;
    @TableField("ASSIGNEE_")
    private String assignee;
    @TableField("DELEGATION_")
    private String delegation;
    @TableField("PRIORITY_")
    private Integer priority;
    @TableField("CREATE_TIME_")
    private LocalDateTime createTime;
    @TableField("DUE_DATE_")
    private LocalDateTime dueDate;
    @TableField("CATEGORY_")
    private String category;
    @TableField("SUSPENSION_STATE_")
    private Integer suspensionState;
    @TableField("TENANT_ID_")
    private String tenantId;
    @TableField("FORM_KEY_")
    private String formKey;
    @TableField("CLAIM_TIME_")
    private LocalDateTime claimTime;
    @TableField("IS_COUNT_ENABLED_")
    private Integer isCountEnabled;
    @TableField("VAR_COUNT_")
    private Integer varCount;
    @TableField("ID_LINK_COUNT_")
    private Integer idLinkCount;
    @TableField("SUB_TASK_COUNT_")
    private Integer subTaskCount;


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

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getTaskDefId() {
        return taskDefId;
    }

    public void setTaskDefId(String taskDefId) {
        this.taskDefId = taskDefId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getSubScopeId() {
        return subScopeId;
    }

    public void setSubScopeId(String subScopeId) {
        this.subScopeId = subScopeId;
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public String getScopeDefinitionId() {
        return scopeDefinitionId;
    }

    public void setScopeDefinitionId(String scopeDefinitionId) {
        this.scopeDefinitionId = scopeDefinitionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDelegation() {
        return delegation;
    }

    public void setDelegation(String delegation) {
        this.delegation = delegation;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getSuspensionState() {
        return suspensionState;
    }

    public void setSuspensionState(Integer suspensionState) {
        this.suspensionState = suspensionState;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public LocalDateTime getClaimTime() {
        return claimTime;
    }

    public void setClaimTime(LocalDateTime claimTime) {
        this.claimTime = claimTime;
    }

    public Integer getIsCountEnabled() {
        return isCountEnabled;
    }

    public void setIsCountEnabled(Integer isCountEnabled) {
        this.isCountEnabled = isCountEnabled;
    }

    public Integer getVarCount() {
        return varCount;
    }

    public void setVarCount(Integer varCount) {
        this.varCount = varCount;
    }

    public Integer getIdLinkCount() {
        return idLinkCount;
    }

    public void setIdLinkCount(Integer idLinkCount) {
        this.idLinkCount = idLinkCount;
    }

    public Integer getSubTaskCount() {
        return subTaskCount;
    }

    public void setSubTaskCount(Integer subTaskCount) {
        this.subTaskCount = subTaskCount;
    }

    @Override
    public String toString() {
        return "ActRuTask{" +
        "id=" + id +
        ", rev=" + rev +
        ", executionId=" + executionId +
        ", procInstId=" + procInstId +
        ", procDefId=" + procDefId +
        ", taskDefId=" + taskDefId +
        ", scopeId=" + scopeId +
        ", subScopeId=" + subScopeId +
        ", scopeType=" + scopeType +
        ", scopeDefinitionId=" + scopeDefinitionId +
        ", name=" + name +
        ", parentTaskId=" + parentTaskId +
        ", description=" + description +
        ", taskDefKey=" + taskDefKey +
        ", owner=" + owner +
        ", assignee=" + assignee +
        ", delegation=" + delegation +
        ", priority=" + priority +
        ", createTime=" + createTime +
        ", dueDate=" + dueDate +
        ", category=" + category +
        ", suspensionState=" + suspensionState +
        ", tenantId=" + tenantId +
        ", formKey=" + formKey +
        ", claimTime=" + claimTime +
        ", isCountEnabled=" + isCountEnabled +
        ", varCount=" + varCount +
        ", idLinkCount=" + idLinkCount +
        ", subTaskCount=" + subTaskCount +
        "}";
    }
}
