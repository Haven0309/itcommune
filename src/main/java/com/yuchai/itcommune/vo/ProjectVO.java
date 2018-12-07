package com.yuchai.itcommune.vo;


import com.yuchai.itcommune.entity.ProjectUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Haven
 * @since 2018-09-14
 */
public class ProjectVO {

    private Integer id;

    /**
     * 项目类型
     */
    private String projectType;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 预期收益
     */
    private String expectedEarnings;

    /**
     * 需求目的
     */
    private String demandPurpose;

    /**
     * 需求详情
     */
    private String demand;

    /**
     * 需求详情
     */
    private String projectBackgroud;

    /**
     * 项目概述
     */
    private String projectDesc;

    /**
     * 奖金
     */
    private Integer money;

    /**
     * 要求完成时间
     */
    private LocalDateTime expirationDate;

    /**
     * 当前步骤
     */
    private String currentStep;

    /**
     * 项目状态
     */
    private String status;

    /**
     * 发布人
     */
    private String publisherId;

    /**
     * 创建时间
     */
    private LocalDate createdDate;

    /**
     * 流程实例id
     */
    private String instanceId;

    /**
     * 团队
     */
    private List<TeamsVO> teams;

    /**
     * 项目金钱
     */
    private List<ProjectUser> projectUsers;

    public List<ProjectUser> getProjectUsers() {
        return projectUsers;
    }

    public void setProjectUsers(List<ProjectUser> projectUsers) {
        this.projectUsers = projectUsers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getExpectedEarnings() {
        return expectedEarnings;
    }

    public void setExpectedEarnings(String expectedEarnings) {
        this.expectedEarnings = expectedEarnings;
    }

    public String getDemandPurpose() {
        return demandPurpose;
    }

    public void setDemandPurpose(String demandPurpose) {
        this.demandPurpose = demandPurpose;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getProjectBackgroud() {
        return projectBackgroud;
    }

    public void setProjectBackgroud(String projectBackgroud) {
        this.projectBackgroud = projectBackgroud;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(String currentStep) {
        this.currentStep = currentStep;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public List<TeamsVO> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamsVO> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "ProjectVO{" +
                "id=" + id +
                ", projectType='" + projectType + '\'' +
                ", projectName='" + projectName + '\'' +
                ", expectedEarnings='" + expectedEarnings + '\'' +
                ", demandPurpose='" + demandPurpose + '\'' +
                ", demand='" + demand + '\'' +
                ", projectBackgroud='" + projectBackgroud + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", money=" + money +
                ", expirationDate=" + expirationDate +
                ", currentStep='" + currentStep + '\'' +
                ", status='" + status + '\'' +
                ", publisherId='" + publisherId + '\'' +
                ", createdDate=" + createdDate +
                ", instanceId='" + instanceId + '\'' +
                ", teams=" + teams +
                '}';
    }
}
