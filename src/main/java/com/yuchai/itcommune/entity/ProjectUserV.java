package com.yuchai.itcommune.entity;

import java.math.BigDecimal;
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
 * @since 2019-01-15
 */
public class ProjectUserV implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 发布人
     */
    private String publisherId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 显示名
     */
    private String displayName;

    /**
     * 公司
     */
    private String company;

    /**
     * 部门
     */
    private String department;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目描述
     */
    private String projectDesc;

    /**
     * 项目状态
     */
    private String status;

    /**
     * 奖金
     */
    private Integer money;

    /**
     * 预期收益
     */
    private String expectedEarnings;

    private BigDecimal itCommune;

    /**
     * 要求完成时间
     */
    private LocalDateTime expirationDate;

    /**
     * 创建时间
     */
    private LocalDateTime createdDate;


    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getExpectedEarnings() {
        return expectedEarnings;
    }

    public void setExpectedEarnings(String expectedEarnings) {
        this.expectedEarnings = expectedEarnings;
    }

    public BigDecimal getItCommune() {
        return itCommune;
    }

    public void setItCommune(BigDecimal itCommune) {
        this.itCommune = itCommune;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ProjectUserV{" +
        "publisherId=" + publisherId +
        ", userName=" + userName +
        ", displayName=" + displayName +
        ", company=" + company +
        ", department=" + department +
        ", projectName=" + projectName +
        ", projectDesc=" + projectDesc +
        ", status=" + status +
        ", money=" + money +
        ", expectedEarnings=" + expectedEarnings +
        ", itCommune=" + itCommune +
        ", expirationDate=" + expirationDate +
        ", createdDate=" + createdDate +
        "}";
    }
}
