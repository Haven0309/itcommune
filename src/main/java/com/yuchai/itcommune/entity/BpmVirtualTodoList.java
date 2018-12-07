package com.yuchai.itcommune.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Haven
 * @since 2018-12-06
 */
public class BpmVirtualTodoList implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建人
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 审批人
     */
    private String assignee;

    /**
     * 审批时间
     */
    private LocalDateTime assigneddate;

    /**
     * 环节名
     */
    private String activityLabel;

    /**
     * URL地址
     */
    private String formurl;

    /**
     * instanceId  用于区别
     */
    @TableId(value = "INSTANCE_ID")
    private String instanceId;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public LocalDateTime getAssigneddate() {
        return assigneddate;
    }

    public void setAssigneddate(LocalDateTime assigneddate) {
        this.assigneddate = assigneddate;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }

    public String getFormurl() {
        return formurl;
    }

    public void setFormurl(String formurl) {
        this.formurl = formurl;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "BpmVirtualTodoList{" +
        "title=" + title +
        ", creatorName=" + creatorName +
        ", createTime=" + createTime +
        ", assignee=" + assignee +
        ", assigneddate=" + assigneddate +
        ", activityLabel=" + activityLabel +
        ", formurl=" + formurl +
        ", instanceId=" + instanceId +
        "}";
    }
}
