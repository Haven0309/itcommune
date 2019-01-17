package com.yuchai.itcommune.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author Haven
 * @since 2019-01-17
 */
public class ProjectUserMoney implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目描述
     */
    private String projectDesc;

    /**
     * 团队id
     */
    private Integer teamId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 用户ID
     */
    private String userCode;

    /**
     * 用户名
     */
    private String userName;

    private Integer salary;

    /**
     * 团队描述
     */
    private String teamDesc;

    /**
     * 队长
     */
    private String teamLeader;

    /**
     * 是否已经退出
     */
    private String quit;

    private LocalDateTime createdDate;

    /**
     * 要求完成时间
     */
    private LocalDateTime expirationDate;

    /**
     * 创建时间
     */
    private LocalDateTime pCreatedDate;

    /**
     * 部门
     */
    private String department;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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

    public String getQuit() {
        return quit;
    }

    public void setQuit(String quit) {
        this.quit = quit;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getpCreatedDate() {
        return pCreatedDate;
    }

    public void setpCreatedDate(LocalDateTime pCreatedDate) {
        this.pCreatedDate = pCreatedDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "ProjectUserMoney{" +
        "projectId=" + projectId +
        ", projectName=" + projectName +
        ", projectDesc=" + projectDesc +
        ", teamId=" + teamId +
        ", teamName=" + teamName +
        ", userCode=" + userCode +
        ", userName=" + userName +
        ", salary=" + salary +
        ", teamDesc=" + teamDesc +
        ", teamLeader=" + teamLeader +
        ", quit=" + quit +
        ", createdDate=" + createdDate +
        ", expirationDate=" + expirationDate +
        ", pCreatedDate=" + pCreatedDate +
        ", department=" + department +
        "}";
    }
}
