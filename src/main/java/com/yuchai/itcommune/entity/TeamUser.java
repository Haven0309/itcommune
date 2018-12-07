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
public class TeamUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 团队id
     */
    private Integer teamId;

    /**
     * 用户ID
     */
    private String userCode;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 是否已经退出
     */
    private String quit;

    private Integer salary;

    private String evaluation;

    private String createdBy;

    private LocalDateTime createdDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public String getQuit() {
        return quit;
    }

    public void setQuit(String quit) {
        this.quit = quit;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "TeamUser{" +
        "id=" + id +
        ", teamId=" + teamId +
        ", userCode=" + userCode +
        ", userName=" + userName +
        ", quit=" + quit +
        ", salary=" + salary +
        ", evaluation=" + evaluation +
        ", createdBy=" + createdBy +
        ", createdDate=" + createdDate +
        "}";
    }
}
