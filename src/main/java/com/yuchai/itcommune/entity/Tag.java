package com.yuchai.itcommune.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 'commune.salary_top' is not BASE TABLE
 * </p>
 *
 * @author Haven
 * @since 2018-12-06
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户
     */
    private String userCode;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 描述
     */
    private String tagDesc;


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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    @Override
    public String toString() {
        return "Tag{" +
        "id=" + id +
        ", userCode=" + userCode +
        ", tagName=" + tagName +
        ", tagDesc=" + tagDesc +
        "}";
    }
}
