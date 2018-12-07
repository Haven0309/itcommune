package com.yuchai.itcommune.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Haven
 * @create 2018-11-26 8:49
 */
public class ProjectSalaryVO {

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
     * 需求背景
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
     * 要求完成时间
     */
    private LocalDateTime expirationDate;

    /**
     * 我的金额
     */
    private Integer salary;

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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ProjectSalaryVO{" +
                "id=" + id +
                ", projectType='" + projectType + '\'' +
                ", projectName='" + projectName + '\'' +
                ", expectedEarnings='" + expectedEarnings + '\'' +
                ", demandPurpose='" + demandPurpose + '\'' +
                ", demand='" + demand + '\'' +
                ", projectBackgroud='" + projectBackgroud + '\'' +
                ", projectDesc='" + projectDesc + '\'' +
                ", money=" + money +
                ", status='" + status + '\'' +
                ", publisherId='" + publisherId + '\'' +
                ", createdDate=" + createdDate +
                ", expirationDate=" + expirationDate +
                ", salary=" + salary +
                '}';
    }
}
