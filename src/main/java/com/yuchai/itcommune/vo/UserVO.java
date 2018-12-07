package com.yuchai.itcommune.vo;



import com.yuchai.itcommune.entity.Tag;

import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Haven
 * @since 2018-09-14
 */
public class UserVO {


    private Integer id;

    /**
     * 用户id
     */
    private String userCode;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 显示名
     */
    private String displayName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 学历
     */
    private String education;

    /**
     * 特长
     */
    private String specialty;

    /**
     * 公司
     */
    private String company;

    /**
     * 部门
     */
    private String department;

    /**
     * 头像
     */
    private String userface;

    /**
     * 五芒星1
     */
    private Integer pentacles1;

    /**
     * 五芒星2
     */
    private Integer pentacles2;

    /**
     * 五芒星3
     */
    private Integer pentacles3;

    /**
     * 五芒星4
     */
    private Integer pentacles4;

    /**
     * 五芒星5
     */
    private Integer pentacles5;

    /**
     * 标签
     */
    private String labels;

    /**
     * 是否生效
     */
    private Integer enabled;

    /**
     * 标签
     */
    private List<Tag> tags;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
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

    public String getUserface() {
        return userface;
    }

    public void setUserface(String userface) {
        this.userface = userface;
    }

    public Integer getPentacles1() {
        return pentacles1;
    }

    public void setPentacles1(Integer pentacles1) {
        this.pentacles1 = pentacles1;
    }

    public Integer getPentacles2() {
        return pentacles2;
    }

    public void setPentacles2(Integer pentacles2) {
        this.pentacles2 = pentacles2;
    }

    public Integer getPentacles3() {
        return pentacles3;
    }

    public void setPentacles3(Integer pentacles3) {
        this.pentacles3 = pentacles3;
    }

    public Integer getPentacles4() {
        return pentacles4;
    }

    public void setPentacles4(Integer pentacles4) {
        this.pentacles4 = pentacles4;
    }

    public Integer getPentacles5() {
        return pentacles5;
    }

    public void setPentacles5(Integer pentacles5) {
        this.pentacles5 = pentacles5;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", userCode=" + userCode +
        ", userName=" + userName +
        ", displayName=" + displayName +
        ", password=" + password +
        ", phone=" + phone +
        ", mail=" + mail +
        ", education=" + education +
        ", specialty=" + specialty +
        ", company=" + company +
        ", department=" + department +
        ", userface=" + userface +
        ", pentacles1=" + pentacles1 +
        ", pentacles2=" + pentacles2 +
        ", pentacles3=" + pentacles3 +
        ", pentacles4=" + pentacles4 +
        ", pentacles5=" + pentacles5 +
        ", labels=" + labels +
        ", enabled=" + enabled +
        "}";
    }
}
