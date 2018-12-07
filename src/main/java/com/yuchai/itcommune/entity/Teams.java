package com.yuchai.itcommune.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 'commune.salary_top' is not BASE TABLE
 * </p>
 *
 * @author Haven
 * @since 2018-12-06
 */
public class Teams implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 团队描述
     */
    private String teamDesc;

    /**
     * 队长
     */
    private String teamLeader;

    private LocalDateTime createTime;

    private String createBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "Teams{" +
        "id=" + id +
        ", projectId=" + projectId +
        ", teamName=" + teamName +
        ", teamDesc=" + teamDesc +
        ", teamLeader=" + teamLeader +
        ", createTime=" + createTime +
        ", createBy=" + createBy +
        "}";
    }
}
