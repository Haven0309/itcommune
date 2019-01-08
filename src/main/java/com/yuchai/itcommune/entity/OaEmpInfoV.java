package com.yuchai.itcommune.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Haven
 * @since 2018-12-06
 */
public class OaEmpInfoV implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double employeeId;

    private String employeeCode;

    private String employeeName;

    private String gsFullName;

    private String status;

    private String oaAccount;

    private String mobile;

    private String syncSystem;

    private String zsign01;


    public Double getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Double employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGsFullName() {
        return gsFullName;
    }

    public void setGsFullName(String gsFullName) {
        this.gsFullName = gsFullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOaAccount() {
        return oaAccount;
    }

    public void setOaAccount(String oaAccount) {
        this.oaAccount = oaAccount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSyncSystem() {
        return syncSystem;
    }

    public void setSyncSystem(String syncSystem) {
        this.syncSystem = syncSystem;
    }

    public String getZsign01() {
        return zsign01;
    }

    public void setZsign01(String zsign01) {
        this.zsign01 = zsign01;
    }

    @Override
    public String toString() {
        return "OaEmpInfoV{" +
        "employeeId=" + employeeId +
        ", employeeCode=" + employeeCode +
        ", employeeName=" + employeeName +
        ", gsFullName=" + gsFullName +
        ", status=" + status +
        ", oaAccount=" + oaAccount +
        ", mobile=" + mobile +
        ", syncSystem=" + syncSystem +
        ", zsign01=" + zsign01 +
        "}";
    }
}
