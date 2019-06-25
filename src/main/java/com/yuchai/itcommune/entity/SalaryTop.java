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
 * @since 2019-06-25
 */
public class SalaryTop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userCode;

    private Integer salary;

    private LocalDateTime createdDate;

    /**
     * 用户名
     */
    private String userName;


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "SalaryTop{" +
        "userCode=" + userCode +
        ", salary=" + salary +
        ", createdDate=" + createdDate +
        ", userName=" + userName +
        "}";
    }
}
