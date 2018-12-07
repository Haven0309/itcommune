package com.yuchai.itcommune.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author Haven
 * @since 2018-12-06
 */
public class DeptMoneyTop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门
     */
    private String department;

    private BigDecimal money;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "DeptMoneyTop{" +
        "department=" + department +
        ", money=" + money +
        "}";
    }
}
