package com.yuchai.itcommune.vo;

/**
 * @author Haven
 * @create 2018-11-25 15:08
 */
public class UserSalaryVO {
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

    /**
     * 总收入
     */
    private Integer totalSalary;

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

    public Integer getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Integer totalSalary) {
        this.totalSalary = totalSalary;
    }

    @Override
    public String toString() {
        return "UserSalaryVO{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", quit='" + quit + '\'' +
                ", salary=" + salary +
                ", totalSalary=" + totalSalary +
                '}';
    }
}
