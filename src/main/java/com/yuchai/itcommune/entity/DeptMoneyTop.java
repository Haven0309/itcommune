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
public class DeptMoneyTop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门
     */
    private String department;

    /**
     * 奖金
     */
    private Integer money;

    /**
     * 创建时间
     */
    private LocalDateTime createdDate;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "DeptMoneyTop{" +
        "department=" + department +
        ", money=" + money +
        ", createdDate=" + createdDate +
        "}";
    }
}
