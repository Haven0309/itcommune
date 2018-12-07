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
public class SalaryTop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private String userCode;

    private BigDecimal salary;


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "SalaryTop{" +
        "userCode=" + userCode +
        ", salary=" + salary +
        "}";
    }
}
