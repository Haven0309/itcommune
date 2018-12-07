package com.yuchai.itcommune.vo;


import com.yuchai.itcommune.entity.Evaluation;
import com.yuchai.itcommune.entity.TeamUser;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Haven
 * @since 2018-09-14
 */
public class TeamsVO {


    private Integer id;

    /**
     * 项目ID
     */
    private Integer projectId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 团队描述
     */
    private String teamDesc;

    /**
     * 队长
     */
    private String teamLeader;

    private LocalDate createTime;

    private String createBy;

    private List<TeamUser> TeamUsers;

    private List<Evaluation> evaluations;

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public List<TeamUser> getTeamUsers() {
        return TeamUsers;
    }

    public void setTeamUsers(List<TeamUser> teamUsers) {
        TeamUsers = teamUsers;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamDesc() {
        return teamDesc;
    }

    public void setTeamDesc(String teamDesc) {
        this.teamDesc = teamDesc;
    }

    public String getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader) {
        this.teamLeader = teamLeader;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "TeamsVO{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", teamName='" + teamName + '\'' +
                ", teamDesc='" + teamDesc + '\'' +
                ", teamLeader='" + teamLeader + '\'' +
                ", createTime=" + createTime +
                ", createBy='" + createBy + '\'' +
                ", TeamUsers=" + TeamUsers +
                '}';
    }
}
